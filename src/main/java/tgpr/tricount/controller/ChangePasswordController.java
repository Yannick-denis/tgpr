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
            user.setHashedPassword(newpassword); // a modifier pour qu'il change l'user actuel au lieu d'en créer un   hashedPassword, User.Role.User
            user.save();
            view.close();
        }else {
            showErrors(error);
        }
    }
    public ErrorList validate(String oldpassword, String newpassword, String confirmPassword) {
        var eror = new ErrorList();
        var user = Security.getLoggedUser();
        if (!oldpassword.equals(user.getHashedPassword())){
            eror.add("wrong password ", User.Fields.ConfirmPassword);
        }
        if (!newpassword.equals(confirmPassword)){
            eror.add("password must be the same", User.Fields.ConfirmPassword);
        }
        if (newpassword== null ||newpassword.isEmpty()){
            eror.add(" new password required ",User.Fields.Password);
        }
        return eror;
    }
}
