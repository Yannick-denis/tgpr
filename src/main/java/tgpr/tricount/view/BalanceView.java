package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.framework.Margin;
import tgpr.framework.Spacing;
import tgpr.tricount.controller.BalanceController;
import tgpr.tricount.controller.TestController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;

import java.util.List;

public class BalanceView extends DialogWindow {
    private final BalanceController controller;


    public BalanceView(BalanceController controller) {
        super("Balance");
        this.controller = controller;

        var root = new Panel().setLayoutManager(new LinearLayout(Direction.VERTICAL));


        var grid = new Panel().setLayoutManager(
                new GridLayout(3).setHorizontalSpacing(0).setVerticalSpacing(0));

        var balance = new Panel().setLayoutManager(
                new LinearLayout(Direction.HORIZONTAL)
        );


        Tricount tricountcur = Tricount.getByKey(1);
        List lop = tricountcur.getOperations();
        for(Operation ope: lop){
            
        }

        Operation.getByKey(1).getRepartitions().
        User.getByKey(1).getFullName();
        createCell("|").addTo(grid)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.FILL, GridLayout.Alignment.FILL,
                        true, true, 2, 1));

        setComponent(root.withBorder(Borders.singleLine()));


        Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        Button btnClose = new Button("Close", this::close).addTo(buttons);

    }

    private Border createCell(String i) {
        return new Panel()
                .addComponent(new Label(i))
                .withBorder(Borders.singleLine());
    }


}
