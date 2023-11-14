package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.EditProfileController;

import java.util.List;

public class EditProfileView extends DialogWindow {
    private final EditProfileController controller;
    private final TextBox txtMail;
    private final TextBox txtFullName;
    private final TextBox txtIban;
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
        txtMail = new TextBox().addTo(panel).sizeTo(20);
        panel.addComponent(new Label("Full Name:"));
        txtFullName = new TextBox().setMask('*').addTo(panel).sizeTo(35);
        panel.addComponent(new Label("IBAN:"));
        txtIban = new TextBox().setMask('*').addTo(panel).sizeTo(20);

        new EmptySpace().addTo(root);

        Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        Button save = new Button("Save").addTo(buttons);
        Button btnSignUp = new Button("Cancel" , this::close).addTo(buttons);

    }
}
