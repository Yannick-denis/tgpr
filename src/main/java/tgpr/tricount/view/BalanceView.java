package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Layouts;
import tgpr.framework.Margin;
import tgpr.framework.Spacing;
import tgpr.tricount.controller.BalanceController;
import tgpr.tricount.controller.TestController;

import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;

import tgpr.tricount.model.*;


import java.util.ArrayList;
import java.util.List;

public class BalanceView extends DialogWindow {
    private final BalanceController controller;


    public BalanceView(BalanceController controller) {
        super("Balance");
        this.controller = controller;
        setHints(List.of(Hint.CENTERED));
        var root = new Panel().setLayoutManager(new LinearLayout(Direction.VERTICAL));


        var grid = new Panel().setLayoutManager(
                new GridLayout(3).setHorizontalSpacing(0).setVerticalSpacing(0)).addTo(root);


        var balance = new Panel().setLayoutManager(
                new LinearLayout(Direction.HORIZONTAL)
        ).addTo(grid);


        compasantCentral().addTo(root);


        setComponent(root.withBorder(Borders.singleLine()));


        Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        Button btnClose = new Button("Close", this::close).addTo(buttons);
        addShortcut(btnClose, KeyStroke.fromString("<A-c>"));

    }

    private Border createCell(String i) {
        return new Panel()
                .addComponent(new Label(i))
                .withBorder(Borders.singleLine());
    }



    private Panel compasantCentral() {
        Panel panel = new Panel().setLayoutManager(new GridLayout(3).setTopMarginSize(1).setVerticalSpacing(1));
        List<User> listparticipant = controller.getTricount().getParticipants();
        for (User user : listparticipant) {
            double balance = controller.balance(user.getId());//balance();
            if (balance < 0) {
                new Label(String.valueOf(balance)).setBackgroundColor(TextColor.ANSI.RED).addTo(panel);
                new Label("|").addTo(panel);
                new Label(user.getFullName()).addTo(panel);
            } else {
                new Label(user.getFullName()).addTo(panel);
                new Label("|").addTo(panel);
                new Label(String.valueOf(balance)).setBackgroundColor(TextColor.ANSI.GREEN).addTo(panel);
            }
        }
        return panel;
    }


}
