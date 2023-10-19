package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.framework.Margin;
import tgpr.framework.Spacing;
import tgpr.tricount.controller.BalanceController;
import tgpr.tricount.controller.TestController;

public class BalanceView extends DialogWindow {
    private final BalanceController controller;



    public BalanceView(BalanceController controller) {
        super("Balance");
        this.controller = controller;

        var root = Panel.gridPanel(3, Margin.of(1), Spacing.of(2));
        setComponent(root);
        Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        Button btnClose = new Button("Close", this::close).addTo(buttons);
    }




}
