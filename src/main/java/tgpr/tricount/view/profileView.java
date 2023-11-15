package tgpr.tricount.view;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.BorderLayout;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;

import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Controller;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.ChangePasswordController;

import tgpr.framework.Controller;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.EditProfileController;
import tgpr.tricount.controller.TricountListController;

import tgpr.tricount.controller.profileController;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.User;
import tgpr.tricount.controller.EditProfileController;

import java.awt.*;
import java.awt.Container;
import java.util.List;

import static tgpr.framework.Controller.navigateTo;

public class profileView extends DialogWindow  {
    private final profileController controller;

    public profileView(profileController controller, String title) {
        super(title);
        this.controller = controller;
        User user = Security.getLoggedUser();
        Label nom = new Label(user.getFullName());


        setHints(List.of(Hint.CENTERED));
        setCloseWindowWithEscape(true);

        Panel root = Panel.verticalPanel();
        setComponent(root);

        Panel panel = new Panel().addTo(root);

        new EmptySpace().addTo(panel);
        new Label("Hey "+ user.getFullName() + "!").addTo(panel);
        new EmptySpace().addTo(panel);
        new Label("I know your email address is "+ user.getMail() + ".").addTo(panel);
        new EmptySpace().addTo(panel);
        new Label("What can I do for you?").addTo(panel);
        new EmptySpace().addTo(panel);

        Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);

        Button btnEditProfile = new Button("Edit Profile" , () -> {Controller.navigateTo(new EditProfileController());} ).addTo(buttons);
        Button btnChangePassword = new Button("Change Password").addTo(buttons);
        Button btnClose = new Button("Close" , this::close).addTo(buttons);
        addShortcut(btnClose, KeyStroke.fromString("<A-c>"));

    }
}



