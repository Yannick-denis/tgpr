package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.Tools;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.User;
import tgpr.tricount.view.ChangePasswordView;
import tgpr.framework.ErrorList;
import tgpr.tricount.view.TricountListView;

public class ChangePasswordController extends Controller {
    private final ChangePasswordView view = new ChangePasswordView(this);

    @Override
    public Window getView() {
        return new ChangePasswordView(this);
    }
    public void save(String oldpassword,String newpassword,String confirmPassword) {
        var user = Security.getLoggedUser();
        var error=validate(oldpassword,newpassword,confirmPassword);
        if (error.isEmpty()){
            String hashedPassword= newpassword.isBlank()? newpassword: Tools.hash(newpassword);
            user.setHashedPassword(hashedPassword); // a modifier pour qu'il change l'user actuel au lieu d'en créer un   hashedPassword, User.Role.User
            user.save();
            view.close();
        }else {
            showErrors(error);
        }
    }
    public ErrorList validate(String oldpassword, String newpassword, String confirmPassword) {
        var eror = new ErrorList();
        var user = Security.getLoggedUser();
        if (!Tools.hash(oldpassword).equals(user.getHashedPassword())){
            eror.add("password invalid", User.Fields.OldPassword);
        }

        if (!newpassword.equals(confirmPassword)){
            eror.add("password must be the same", User.Fields.ConfirmPassword);
        }
        if (Tools.hash(newpassword).equals(user.getHashedPassword())){
            eror.add("New passoword must different old password ",User.Fields.Password);
        }
        if(newpassword.length()<8){
            eror.add("minimun 8 char",User.Fields.Password);
        }
        if (!newpassword.matches(".*\\d.*")){
            eror.add("Missing a digital",User.Fields.Password);
        }
        if (!newpassword.matches(".*[A-Z].*")){
            eror.add("Missing an uppercase char",User.Fields.Password);
        }
        if (newpassword== null ||newpassword.isEmpty()){
            eror.add(" new password required ",User.Fields.Password);
        }
        return eror;
    }
}
