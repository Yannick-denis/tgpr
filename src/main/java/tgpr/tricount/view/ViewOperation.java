package tgpr.tricount.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.ColumnSpec;
import tgpr.framework.Controller;
import tgpr.framework.Layouts;
import tgpr.framework.ObjectTable;
import tgpr.tricount.controller.AddExpenseController;
import tgpr.tricount.controller.EditTricountController;
import tgpr.tricount.controller.OperationController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Repartition;
import tgpr.tricount.model.Tricount;

import java.text.DecimalFormat;
import java.util.List;


public class ViewOperation  extends DialogWindow {
    private Repartition repartition;
    private Operation operation = getOperation();
    private OperationController controller;
    private  ObjectTable<Repartition> table;
    private Button up;
    private Button down;
    private Button edit;
    private Button close;
    private List<Operation> operationList;
    private int index;
  

    public ViewOperation(OperationController controllerView) {
        super("View Expense Detail");
        setHints(List.of(Hint.EXPANDED));
        this.controller = controllerView;
        this.operation = controller.getOperation();

        Panel root = new Panel();
        setComponent(root);
        setHints(List.of(Hint.CENTERED, Hint.FIXED_SIZE));
        setFixedSize(new TerminalSize(50, 15));

        Panel content = new Panel(new GridLayout(2)).addTo(root);
        Panel contentPanel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing((int) 0.5)).addTo(root);

        contentPanel.addComponent(new Label("Title:"));
        contentPanel.addComponent(new Label(getOperation().getTitle()).addTo(contentPanel).addStyle(SGR.BOLD));
        contentPanel.addComponent(new Label("Amount:"));
        contentPanel.addComponent(new Label(String.valueOf(operation.getAmount())).addTo(contentPanel).addStyle(SGR.BOLD));
        contentPanel.addComponent(new Label("Date:"));
        contentPanel.addComponent(new Label(String.valueOf(operation.getOperationDate())).addTo(contentPanel).addStyle(SGR.BOLD));
        contentPanel.addComponent(new Label("Paid by:"));
        contentPanel.addComponent(new Label(operation.getInitiator().getFullName()).addTo(contentPanel).addStyle(SGR.BOLD));
         new EmptySpace().addTo(contentPanel);
         new EmptySpace().addTo(contentPanel);

      new Label("From whom:").addTo(contentPanel);
        table = new ObjectTable<>(
            new ColumnSpec<>("Participant", Repartition::getUser),
            new ColumnSpec<>("Weight", Repartition::getWeight),
            new ColumnSpec<>("Amount", Repartition::getAmount)
        ).addTo(contentPanel);
        //updateTableData();
        table.addTo(contentPanel);
        table.add(operation.getRepartitions());



        Panel buttons = new Panel().setLayoutManager(new GridLayout(4))
                .setLayoutData(Layouts.LINEAR_FILL).addTo(root);
        new EmptySpace().addTo(buttons);
        new EmptySpace().addTo(buttons);
        new EmptySpace().addTo(buttons);
        new EmptySpace().addTo(buttons);
        int currentId = getOperation().getTricount().getOperations().indexOf(operation);
        int maxIndex = getOperation().getTricount().getOperations().size();
        up = new Button("Up", () ->{
            if(currentId == 0){
                up.setEnabled(false);
            }else {
                close();
                Controller.navigateTo(new OperationController(operation.getTricount().getOperations().get(currentId  - 1 ), operationList));
            }
            }).addTo(buttons);
        down = new Button("Down", () ->{
            if(currentId == maxIndex - 1){
                down.setEnabled(false);

            }else {
                Controller.navigateTo(new OperationController(operation.getTricount().getOperations().get(currentId + 1 ), operationList));
            }
        }).addTo(buttons);
        edit = new Button("Edit", () -> {
            Controller.navigateTo(new AddExpenseController(operation.getTricount(),operation));
            close();
        }).addTo(buttons);
        close = new Button("Close", this::close).addTo(buttons);
        root.addComponent(buttons, LinearLayout.createLayoutData(LinearLayout.Alignment.End));
        addShortcut(close, KeyStroke.fromString("<A-c>"));


    }
    public ObjectTable<Repartition> table1(){
         ObjectTable<Repartition> tab = getTable();
           return tab;
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
    public void setUp(Button up) {
        this.up = up;
    }
}


