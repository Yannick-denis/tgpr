package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.ErrorList;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.User;
import tgpr.tricount.view.EditProfileView;

public class EditProfileController extends Controller {
    private User me =Security.getLoggedUser();
    @Override
    public Window getView() {
        return new EditProfileView(this , "Edit Profile");
    }

    public ErrorList validate(String mail, String name, String iban) {
        var erorr = new ErrorList();

        if (name.length()<3){
            erorr.add("minimum 3 char",User.Fields.FullName);
        }
        if (User.getByMail(mail)!=null&& !mail.equals(me.getMail())){
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
    public void save(String mail, String txtFullNameText, String txtIbanText){
             me.setFullName(txtFullNameText);
             me.setIban(txtIbanText);
             me.setMail(mail);
              me.save();
    }
}
