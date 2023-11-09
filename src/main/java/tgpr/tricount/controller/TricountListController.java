package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.User;
import tgpr.tricount.view.TricountListView;
import tgpr.framework.Controller;

import java.util.List;
public class TricountListController extends Controller {
    private List<User> users;

    @Override
    public Window getView(){
        return new TricountListView(this);
    }
    public List<User> getUsers(){
        return User.getAll();
    }
    public void logout() {
        Security.logout();
        navigateTo(new LoginController());
    }

    public void balance() {
        navigateTo( new BalanceController());
    }
    public void chpsw() {                             //à bouger dans profileController
        navigateTo( new ChangePasswordController());  // à bouger dans profileController
    }                                                 //à bouger dans profileController

}
