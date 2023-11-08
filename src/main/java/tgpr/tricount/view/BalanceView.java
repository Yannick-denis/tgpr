package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.framework.Margin;
import tgpr.framework.Spacing;
import tgpr.tricount.controller.BalanceController;
import tgpr.tricount.controller.TestController;
import java.util.List;

public class BalanceView extends DialogWindow {
    private final BalanceController controller;


    public BalanceView(BalanceController controller) {
        super("Balance");
        this.controller = controller;

        var root = new Panel().setLayoutManager(new LinearLayout(Direction.VERTICAL));


        var balance = new Panel().setLayoutManager(
                new GridLayout(3).setHorizontalSpacing(0).setVerticalSpacing(0));


//        createCell("|").addTo(balance)
//                .setLayoutData(GridLayout.createLayoutData(
//                        GridLayout.Alignment.FILL, GridLayout.Alignment.FILL, true,2, 1)
//                );


        Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        Button btnClose = new Button("Close", this::close).addTo(buttons);
        setComponent(root.withBorder(Borders.singleLine()));
    }
    private Border createCell(String i) {
        return new Panel()
                .addComponent(new Label(i))
                .withBorder(Borders.singleLine());
    }


}
