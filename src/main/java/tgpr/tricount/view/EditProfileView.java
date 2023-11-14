package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.EditProfileController;
import tgpr.tricount.model.User;

import java.util.List;

public class EditProfileView extends DialogWindow {
    private final EditProfileController controller;
    private final TextBox txtMail;
    private final TextBox txtFullName;
    private final TextBox txtIban;
    private Label lblMail = new Label("");
    private Label lblFullName = new Label("");
    private Label lblIban = new Label("");
    public EditProfileView(EditProfileController controller, String title) {
        super(title);
        this.controller = controller;

        setHints(List.of(Hint.CENTERED));
        setCloseWindowWithEscape(true);

        Panel root = Panel.verticalPanel();
        setComponent(root);

        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_BEGIN).addTo(root);
        panel.addComponent(new Label("Mail:"));
        txtMail = new TextBox().setTextChangeListener((txt, byUser)-> validate())
                .addTo(panel).sizeTo(20);
        panel.addComponent(new Label("Full Name:"));
        txtFullName = new TextBox().setTextChangeListener((txt, byUser)-> validate())
                .addTo(panel).sizeTo(35);
        panel.addComponent(new Label("IBAN:"));
        txtIban = new TextBox().setTextChangeListener((txt, byUser)-> validate())
                .addTo(panel).sizeTo(20);

        new EmptySpace().addTo(root);

        Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        Button save = new Button("Save").addTo(buttons);
        Button Cancel = new Button("Cancel" , this::close).addTo(buttons);

    }
    private void validate(){
        var error= controller.validate(
                txtMail.getText(),
                txtFullName.getText(),
                txtIban.getText()
        );
        lblFullName.setText(error.getFirstErrorMessage(User.Fields.FullName));
        lblMail.setText(error.getFirstErrorMessage(User.Fields.Mail));
        lblIban.setText(error.getFirstErrorMessage(User.Fields.Iban));
    }
}
