package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.view.profileView;

public class profileController extends Controller {
    @Override
    public Window getView() {
        return new profileView(this , "View profile");
    }
}
