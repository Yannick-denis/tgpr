package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Margin;
import tgpr.framework.Spacing;
import tgpr.tricount.controller.BalanceController;
import tgpr.tricount.controller.TestController;

public class BalanceView extends DialogWindow {
    public BalanceView(BalanceController controller) {
        super("Balance");


        this.controller = controller;

        var root = Panel.gridPanel(3, Margin.of(1), Spacing.of(2));
        setComponent(root);
    }

    private final BalanceController controller;
}
