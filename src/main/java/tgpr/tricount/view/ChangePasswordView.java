package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.BalanceController;
import tgpr.tricount.controller.ChangePasswordController;
import tgpr.tricount.controller.TestController;

import java.util.List;

public class ChangePasswordView extends DialogWindow {
    private final ChangePasswordController controller;
    private TextBox oldpassword;
    private TextBox newpassword;
    private TextBox confirmPassword;
    private  Button btnSave;

    public ChangePasswordView(ChangePasswordController controller) {
        super("Change Password");
        setHints(List.of(Hint.CENTERED));
        this.controller=controller;
        Panel root = new Panel();
        setComponent(root);
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
    }

    private Panel panelButon() {
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_CENTER);
        btnSave = new Button("SAVE",()->{
            save();
        }).setEnabled(false).addTo(panel);
        Button btnClose = new Button("close",()->{
            close();

        }).addTo(panel);
        return panel;
    }
    private void save() {
        controller.save(newpassword.getText(),comfirmPassword.getText());
    }
}
