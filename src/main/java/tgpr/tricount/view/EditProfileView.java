package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.EditProfileController;
import tgpr.tricount.model.Security;
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
    private  Button btnsave=new Button("");
    private User me;
    public EditProfileView(EditProfileController controller, String title) {
        super(title);
        this.controller = controller;

        setHints(List.of(Hint.CENTERED));
        setCloseWindowWithEscape(true);
        me = Security.getLoggedUser();

        Panel root = Panel.verticalPanel();
        setComponent(root);

        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_BEGIN).addTo(root);
        panel.addComponent(new Label("Mail:"));
        txtMail = new TextBox()
                .addTo(panel).sizeTo(20);
        txtMail.setText(me.getMail()==null?"":me.getMail());
        txtMail.setTextChangeListener((txt, byUser)-> validate());
        new EmptySpace().addTo(panel);
       lblMail.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        panel.addComponent(new Label("Full Name:"));
        txtFullName = new TextBox()
                .addTo(panel).sizeTo(35);
        txtFullName.setText(me.getFullName()==null?"": me.getFullName());
        txtFullName.setTextChangeListener((txt, byUser)-> validate());
        new EmptySpace().addTo(panel);
      lblFullName.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        panel.addComponent(new Label("IBAN:"));
        txtIban = new TextBox().setTextChangeListener((txt, byUser)-> validate())
                .addTo(panel).sizeTo(20);
        txtIban.setText(me.getIban()==null?"": me.getIban());
        txtIban.setTextChangeListener((txt, byUser)-> validate());
        new EmptySpace().addTo(panel);
        lblIban.addTo(panel).setForegroundColor(TextColor.ANSI.RED);

        new EmptySpace().addTo(root);

        Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);

         btnsave = new Button("Save", () -> {
             this.save();
             this.close();
         }).addTo(buttons);
         btnsave.setEnabled(false);

        addShortcut(btnsave, KeyStroke.fromString("<A-s>"));
        Button Cancel = new Button("Cancel" , this::close).addTo(buttons);
        addShortcut(Cancel, KeyStroke.fromString("<A-c>"));


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
        btnsave.setEnabled(error.isEmpty());
    }

    private void save(){
        var error= controller.validate(
                txtMail.getText(),
                txtFullName.getText(),
                txtIban.getText()
        );
        if (error.isEmpty()){
            controller.save( txtMail.getText(),
                    txtFullName.getText(),
                    txtIban.getText());
        }
    }
}
