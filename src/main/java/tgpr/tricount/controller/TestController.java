package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.tricount.model.User;
import tgpr.tricount.view.BalanceView;
import tgpr.tricount.view.TestView;
import tgpr.framework.Controller;

import tgpr.tricount.model.Security;

import java.util.List;

public class TestController extends Controller {
    private final TestView view = new TestView(this);

    @Override
    public Window getView() {
        return view;
    }
    public void logout() {
        Security.logout();
        navigateTo(new LoginController());
    }



    public List<User> getUsers() {
        return User.getAll();
    }
}
