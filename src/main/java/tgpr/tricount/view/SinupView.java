package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.SinupControler;
import tgpr.tricount.model.User;

import java.util.List;

public class SinupView extends DialogWindow {

    private SinupControler controler;
    private TextBox name;
    private TextBox mail;
    private TextBox iban;
    private TextBox password;
    private TextBox comfirmPassword;
    private final Label errName = new Label("");
    private final Label errMail = new Label("");
    private final Label errPassword = new Label("");
    private final Label errPasswordConfirm = new Label("");
    private final Label errIban = new Label("");
    private  Button btnSignup;
    public SinupView(SinupControler controler) {
        super("signup");
        setHints(List.of(Hint.CENTERED));
        this.controler=controler;
        Panel root = new Panel();
        setComponent(root);
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        new Label("Mail :").addTo(panel);
        mail =new TextBox().setPreferredSize(new TerminalSize(20,1))
                .setTextChangeListener((txt, byUser)-> validate())
                .addTo(panel);
        panel.addEmpty();
        errMail.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        new Label("Full Name :").addTo(panel);
        name= new TextBox()
                .setPreferredSize(new TerminalSize(30,1))
                .setTextChangeListener((txt, byUser)-> validate())
                .addTo(panel);
        panel.addEmpty();
        errName.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        new Label("IBAN :").addTo(panel);
        iban =new TextBox()
                .setPreferredSize(new TerminalSize(20,1))
                .setTextChangeListener((txt, byUser)-> validate())
                .addTo(panel);
        panel.addEmpty();
        errIban.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        new Label("Password").addTo(panel);
        password =new TextBox()
                .setPreferredSize(new TerminalSize(20,1))
                .setTextChangeListener((txt, byUser)-> validate())
                .setMask('*')
                .addTo(panel);
        panel.addEmpty();
        errPassword.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        new Label("Confirm Password :").addTo(panel);
        comfirmPassword =new TextBox()
                .setPreferredSize(new TerminalSize(20,1))
                .setTextChangeListener((txt, byUser)-> validate())
                .setMask('*')
                .addTo(panel);
        panel.addEmpty();
        errPasswordConfirm.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        panelButon().addTo(root);


    }

    private Panel panelButon() {
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_CENTER);
        btnSignup = new Button("signup",()->{
            save();
        }).setEnabled(false).addTo(panel);
        Button btnClose = new Button("close",()->{
            close();

        }).addTo(panel);
        return panel;
    }

    private void save() {
        controler.save(mail.getText(),name.getText(),iban.getText(),password.getText(),comfirmPassword.getText());
    }
    private void validate(){
        var error= controler.validate(
                mail.getText(),
                name.getText(),
                iban.getText(),
                password.getText(),
                comfirmPassword.getText()
        );
        errName.setText(error.getFirstErrorMessage(User.Fields.FullName));
        errMail.setText(error.getFirstErrorMessage(User.Fields.Mail));
        errPassword.setText(error.getFirstErrorMessage(User.Fields.Password));
        errPasswordConfirm.setText(error.getFirstErrorMessage(User.Fields.ConfirmPassword));
        errIban.setText(error.getFirstErrorMessage(User.Fields.Iban));


        btnSignup.setEnabled(error.isEmpty());

    }
}
