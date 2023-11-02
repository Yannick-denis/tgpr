package tgpr.tricount.view;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import tgpr.framework.ColumnSpec;
import tgpr.framework.Layouts;
import tgpr.framework.ObjectTable;
import tgpr.framework.ViewManager;
import tgpr.tricount.controller.OperationController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Repartition;

import java.util.List;


public class ViewOperation  extends BasicWindow {
    private Operation operation;
    private  Repartition repartition;
    private List<Expense> expenses;
    private final ObjectTable<Repartition> table;
    private Button up;
    private  Button down;
    private  Button edit;
    private  Button close;
    private OperationController controller;
    public ViewOperation(/*List<Expense> expenses, */ OperationController controller ) {
        super("View Expense Detail");
        //this.expenses = expenses;
       // setTitle(operation.getTitle());
        setHints(List.of(Hint.EXPANDED));
        Panel root = new Panel();
        setComponent(root);
        Panel content = new Panel().addTo(root).setLayoutManager(new LinearLayout(Direction.VERTICAL));
        Panel contentPanel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1)).addTo(root);

        operation = Operation.getByKey(1);
       // Panel contentPanel = new Panel(gridLayout);
        contentPanel.addComponent(new Label("Title:"));
        contentPanel.addComponent(new Label(operation.getTitle()).addTo(contentPanel));
        contentPanel.addComponent(new Label("Amount:"));
        contentPanel.addComponent(new Label(String.valueOf(operation.getAmount())).addTo(contentPanel));
        contentPanel.addComponent(new Label("Date:"));
        contentPanel.addComponent(new Label (String.valueOf(operation.getOperationDate())).addTo(contentPanel));
        contentPanel.addComponent(new Label("Paid by:"));
        contentPanel.addComponent(new Label(operation.getInitiator().getFullName()).addTo(contentPanel));
        new EmptySpace().addTo(contentPanel);

        Label nameTabel = new Label("From whom:");
        root.addComponent(nameTabel);
        table = new ObjectTable<>(
               new ColumnSpec<>("Participant", repartition -> repartition.getOperation().getInitiator().getFullName()),
               new ColumnSpec<>("Weight",Repartition::getWeight),
               new ColumnSpec<>("Amout", repartition -> repartition.getOperation().getAmount())
        ).addTo(contentPanel);
        table.setPreferredSize(new TerminalSize(ViewManager.getTerminalColumns(), 3));
        root.addComponent(table);

        new EmptySpace().addTo(content);
        Panel buttons = new Panel().setLayoutManager(new GridLayout(4))
                .setLayoutData(Layouts.LINEAR_END).addTo(root);
         up = new Button("Up",this::moveUp).addTo(buttons);
         buttons.addComponent(up );
         down = new Button("Down", this::moveDown).addTo(buttons);
         buttons.addComponent(down);
         edit = new Button("Edit", this::getEdit).addTo(buttons);
         buttons.addComponent(edit);
         close = new Button("Close", this::getClose).addTo(buttons);
         buttons.addComponent(up);
    }




    private void showExpense(Expense expense) {
        // Implement how to display the expense details on the UI

    }



  /*  public void addExpense(String paidBy, double amount) {
        amount = operation.getAmount();
        expenses.add(new Expense(paidBy,amount));
    }

   */

    /*public double getBalance(Repartition  repartition) {
        double balance = 0;
        for (Expense expense : expenses) {
            if (expense.paidBy.equals(repartition)) {
                balance -= operation.getAmount();
            } else {
                balance += (operation.getAmount() / operation.getId());
            }
        }
        return balance;
    }

     */
    private void moveUp() {
       controller.moveUp();
    }
    public void moveDown() {
        controller.moveDown();
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
        return edit;
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

    public static class Expense {
        private String paidBy;
        public Expense(String paidBy, double amount) {
            this.paidBy = paidBy;
        }

    }
}

