package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.ChangePasswordController;
import tgpr.tricount.model.User;

import java.util.List;

public class ChangePasswordView extends DialogWindow {
    private final ChangePasswordController controller;
    private final Label errOldPassword = new Label("");
    private final Label errPassword = new Label("");
    private final Label errPasswordConfirm = new Label("");
    private TextBox oldpassword;
    private TextBox newpassword;
    private TextBox confirmPassword;
    private Button btnSave;

    public ChangePasswordView(ChangePasswordController controller) {
        super("Change Password");
        setHints(List.of(Hint.CENTERED));
        this.controller = controller;
        Panel root = new Panel();
        setComponent(root);
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        new Label("Old Password :").addTo(panel);
        oldpassword = new TextBox()
                .setPreferredSize(new TerminalSize(20, 1))
                .setTextChangeListener((txt, byUser) -> validate())
                .setMask('*')
                .addTo(panel);
        panel.addEmpty();
        errOldPassword.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        new Label("New Password :").addTo(panel);
        newpassword = new TextBox()
                .setPreferredSize(new TerminalSize(20, 1))
                .setTextChangeListener((txt, byUser) -> validate())
                .setMask('*')
                .addTo(panel);
        panel.addEmpty();
        errPassword.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        new Label("Confirm Password :").addTo(panel);
        confirmPassword = new TextBox()
                .setPreferredSize(new TerminalSize(20, 1))
                .setTextChangeListener((txt, byUser) -> validate())
                .setMask('*')
                .addTo(panel);
        panel.addEmpty();
        errPasswordConfirm.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        panelButon().addTo(root);
    }

    private Panel panelButon() {
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_CENTER);
        btnSave = new Button("SAVE", () -> {
            save();
        }).setEnabled(false).addTo(panel);
        addShortcut(btnSave, KeyStroke.fromString("<A-s>"));
        Button btnClose = new Button("close", () -> {
            close();

        }).addTo(panel);
        addShortcut(btnClose, KeyStroke.fromString("<A-c>"));
        return panel;
    }

    private void save() {
        controller.save(oldpassword.getText(), newpassword.getText(), confirmPassword.getText());
    }

    private void validate() {
        var error = controller.validate(
                oldpassword.getText(),
                newpassword.getText(),
                confirmPassword.getText()
        );
        errPassword.setText(error.getFirstErrorMessage(User.Fields.Password));
        errPasswordConfirm.setText(error.getFirstErrorMessage(User.Fields.ConfirmPassword));
        errOldPassword.setText(error.getFirstErrorMessage(User.Fields.OldPassword));


        btnSave.setEnabled(error.isEmpty());

    }
}
