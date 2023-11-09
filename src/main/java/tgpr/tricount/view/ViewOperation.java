package tgpr.tricount.view;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.*;
import tgpr.tricount.controller.OperationController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Repartition;

import java.util.List;


public class ViewOperation  extends DialogWindow {
    private  Repartition repartition;
    private List<Operation> list;
    private Operation operation;
    private OperationController controller;
    private final ObjectTable<Repartition> table;
    private Button up;
    private  Button down;
    private  Button edit;
    private  Button close;

    public ViewOperation(OperationController controller) {
        super("View Expense Detail");
        setHints(List.of(Hint.EXPANDED));
        this.controller = controller;
        this.operation = controller.getOperation();
        Panel root = new Panel();
        setComponent(root);
        setHints(List.of(Hint.CENTERED, Hint.FIXED_SIZE));
        setFixedSize(new TerminalSize(50, 15));

        Panel content = new Panel(new  GridLayout(2)).addTo(root);
        Panel contentPanel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing((int) 0.5)).addTo(root);

        contentPanel.addComponent(new Label("Title:"));
        contentPanel.addComponent(new Label(getOperation().getTitle()).addTo(contentPanel).addStyle(SGR.BOLD));
        contentPanel.addComponent(new Label("Amount:"));
        contentPanel.addComponent(new Label(String.valueOf(operation.getAmount())).addTo(contentPanel).addStyle(SGR.BOLD));
        contentPanel.addComponent(new Label("Date:"));
        contentPanel.addComponent(new Label (String.valueOf(operation.getOperationDate())).addTo(contentPanel).addStyle(SGR.BOLD));
        contentPanel.addComponent(new Label("Paid by:"));
        contentPanel.addComponent(new Label(operation.getInitiator().getFullName()).addTo(contentPanel).addStyle(SGR.BOLD));

        new EmptySpace().addTo(contentPanel);
        new EmptySpace().addTo(contentPanel);
        new Label("From whom:").addTo(contentPanel);
        table = new ObjectTable<>(
               new ColumnSpec<>("Participant", Repartition::getUser),
               new ColumnSpec<>("Weight",Repartition::getWeight),
                new ColumnSpec<>("Amount", Repartition::getAmount)

        ).addTo(contentPanel);
        table.add(operation.getRepartitions());



        Panel buttons = new Panel().setLayoutManager(new GridLayout(4))
                .setLayoutData(Layouts.LINEAR_FILL).addTo(root);
        new EmptySpace().addTo(buttons);
        new EmptySpace().addTo(buttons);
        new EmptySpace().addTo(buttons);
        new EmptySpace().addTo(buttons);

        up = new Button("Up").addTo(buttons);
        down = new Button("Down").addTo(buttons);
        edit = new Button("Edit", this::getEdit).addTo(buttons);
        close = new Button("Close", this::getClose).addTo(buttons);
        root.addComponent(buttons, LinearLayout.createLayoutData(LinearLayout.Alignment.End));
    }




    public Operation getOperation() {
        return operation;
    }

    public ObjectTable<Repartition> getTable() {
        return table;
    }



    public OperationController getController() {
        return controller;
    }

    public void setController(OperationController controller) {
        this.controller = controller;
    }

    public Button getClose() {
        return close;
    }

    public void setClose(Button close) {
        this.close = close;
    }

    public Button getEdit() {
        return  Controller.navigateTo(new OperationController(Operation.getByKey(2)));
    }



    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Button getDown() {
        return down;
    }

    public void setDown(Button down) {
        this.down = down;
    }

    public Button getUp() {
        return up;
    }

    public void setUp(Button up) {
        this.up = up;
    }


    public Repartition getRepartition() {
        return repartition;
    }

    public void setRepartition(Repartition repartition) {
        this.repartition = repartition;
    }


  /* private void run() {
        // requette sql qui permet de count le nombre id
        if ( getViewTricountView().getList().size() > 0) {
             getUp().setEnabled(false);
        }
    }

   */


}

