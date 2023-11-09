package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.Tools;
import tgpr.tricount.model.User;
import tgpr.tricount.view.ChangePasswordView;
import tgpr.framework.ErrorList;

public class ChangePasswordController extends Controller {
    private final ChangePasswordView view = new ChangePasswordView(this);

    @Override
    public Window getView() {
        return new ChangePasswordView(this);
    }
    public void save(String newpassword,String confirmPassword) {
        var error=validate(newpassword,confirmPassword);
        if (error.isEmpty()){
            String hashedPassword= newpassword.isBlank()? newpassword: Tools.hash(newpassword);
//            User user =new User(mail,hashedPassword,name, User.Role.User,iban); // a modifier pour qu'il change l'user actuel au lieu d'en créer un
//            user.save();
            //  Controller.navigateTo();
            view.close();
        }else {
            showErrors(error);
        }
        public ErrorList validate(String newpassword, String confirmPassword) {
            var eror = new ErrorList();
            if (!newpassword.equals(confirmPassword)){
                eror.add("password must be the same", User.Fields.ConfirmPassword);
            }
            if (newpassword== null ||newpassword.isEmpty()){
                eror.add(" new password required ",User.Fields.Password);
            }
        }
    }
}
