package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.view.EditProfileView;

public class EditProfileController extends Controller {
    @Override
    public Window getView() {
        return new EditProfileView(this , "Edit Profile");
    }
}
