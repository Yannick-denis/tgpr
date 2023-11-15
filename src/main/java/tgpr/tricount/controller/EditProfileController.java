package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.ErrorList;
import tgpr.framework.Params;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.User;
import tgpr.tricount.view.EditProfileView;

import static tgpr.framework.Model.execute;

public class EditProfileController extends Controller {
    @Override
    public Window getView() {
        return new EditProfileView(this , "Edit Profile");
    }
    public void EditProfile(String mail, String name, String iban){
        var error = validate(mail, name, iban);
        if (error.isEmpty())
            save(mail, name, iban);

    }
    public ErrorList validate(String mail, String name, String iban) {
        var erorr = new ErrorList();

        if (name.length()<3){
            erorr.add("minimum 3 char",User.Fields.FullName);
        }
        if (User.getByMail(mail)!=null){
            erorr.add("not available",  User.Fields.Mail);
        }

        if (!iban.matches("BE\\d{2} \\d{4} \\d{4} \\d{4}")&&!iban.matches("BE\\d{14}")){
            erorr.add("Bad format (BE99 9999 9999 9999)",User.Fields.Iban);
        }
        if (name==null|| name.isEmpty()){
            erorr.add("name required",User.Fields.FullName);
        }
        if (!mail.matches(".*[@].*")){
            erorr.add("mail invalid",User.Fields.Mail);
        }

        return erorr;
    }
    public void save(String mail, String full_name, String iban){
        int id = Security.getLoggedUserId();

        var params = new Params()
                .add("id", id)
                .add("mail", mail)
                .add("full_name", full_name)
                .add("iban", iban);
        String sql = "update users set mail=:mail," +
                     "full_name=:full_name," +
                     "iban=:iban" +
                     "where id=:id";
        execute(sql, params);
    }
}
