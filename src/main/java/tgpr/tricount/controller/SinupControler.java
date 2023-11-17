package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.ErrorList;
import tgpr.framework.Tools;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.User;
import tgpr.tricount.view.SinupView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SinupControler extends Controller {

    private SinupView view=new SinupView(this);
    @Override
    public Window getView() {
        return view;
    }

    public void save(String mail, String name, String iban, String pasword,String cofirmPassword) {
        var error=validate(mail,name,iban,pasword,cofirmPassword);
        if (error.isEmpty()){
            String hashedPassword= pasword.isBlank()? pasword: Tools.hash(pasword);
            User user =new User(mail,hashedPassword,name, User.Role.User,iban);
            user.save();
            //  Controller.navigateTo();
            view.close();
            Security.login(user);
            navigateTo(new TricountListController());
        }else {
            showErrors(error);
        }
    }

    public ErrorList validate(String mail, String name, String iban, String pasword, String comfirmPassword) {
        var erorr = new ErrorList();
        if (!pasword.equals(comfirmPassword)){
            erorr.add("password must be the same", User.Fields.ConfirmPassword);
        }
        if (pasword== null ||pasword.isEmpty()){
            erorr.add("password required ",User.Fields.Password);
        }
        if (name.length()<3){
            erorr.add("minimum 3 char",User.Fields.FullName);
        }
        if (User.getByMail(mail)!=null){
            erorr.add("not available",  User.Fields.Mail);
        }

        if (!iban.matches("BE\\d{2} \\d{4} \\d{4} \\d{4}")&&!iban.matches("BE\\d{14}")){
            erorr.add("Bad format (BE99 9999 9999 9999)",User.Fields.Iban);
        }
        if(pasword.length()<8){
            erorr.add("minimun 8 char",User.Fields.Password);
        }
        if (!pasword.matches(".*\\d.*")){
            erorr.add("Missing a digital",User.Fields.Password);
        }
        if (!pasword.matches(".*[A-Z].*")){
            erorr.add("Missing an uppercase char",User.Fields.Password);
        }
        if (name==null|| name.isEmpty()){
            erorr.add("name required",User.Fields.FullName);
        }
        if (!mail.matches(".*[@].*[.][a-z]+")){
            erorr.add("mail invalid",User.Fields.Mail);
        }

        return erorr;
    }


}
