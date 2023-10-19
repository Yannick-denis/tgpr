package tgpr.tricount.view;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import tgpr.framework.ColumnSpec;
import tgpr.framework.ObjectTable;
import tgpr.tricount.controller.OperationController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Repartition;
import tgpr.tricount.model.Tricount;

import java.util.List;


public abstract class ViewOperation  extends BasicWindow {
    private Operation operation;
    private  Repartition repartition;
    private List<Expense> expenses;
    private ObjectTable<Operation> table;
    private Button up;
    private  Button down;
    private int index = 0;
    private  Button edit;
    private  Button close;
    private OperationController controller;
    public ViewOperation(List<Expense> expenses, OperationController controller, Repartition repartition) {
        super("View Expense Detail");
        this.expenses = expenses;
        this.controller = controller;
        this.repartition = repartition;
        setTitle(operation.getTitle());
        setHints(List.of(Hint.EXPANDED));
        Panel root = new Panel();
        setComponent(root);

        Panel fields = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1)).addTo(root);

        up = new Button("Up", this::moveUp);
        down = new Button("Down", this::moveDown);
        edit = new Button("Edit",this::getEdit);
        close = new Button("Close", this::close);

        Panel buttonPanel = new Panel();
        buttonPanel.addComponent(up);
        buttonPanel.addComponent(down);

        if (index == 0){
            up.setEnabled(false);
        }

        if(index == expenses.size() -1){
            down.setEnabled(false);
        }
        BasicWindow window = new BasicWindow();
        window.setComponent(buttonPanel);
        GridLayout gridLayout = new GridLayout(4);

        Panel contentPanel = new Panel(gridLayout);
        contentPanel.addComponent(new Label("Title:"));
        contentPanel.addComponent(new TextBox(operation.getTitle()));
        contentPanel.addComponent(new Label("Amount:"));
        contentPanel.addComponent(new TextBox(String.valueOf(operation.getAmount())));
        contentPanel.addComponent(new Label("Date:"));
        contentPanel.addComponent(setComponent(););
        contentPanel.addComponent(new Label("Paid by:"));
        contentPanel.addComponent(new TextBox(""));


        window.setComponent(contentPanel);

        MultiWindowTextGUI textGUI;
        textGUI.addWindowAndWait(window);
    }

    private void showExpense(Expense expense) {
        // Implement how to display the expense details on the UI

    }

    private void moveUp() {
        if (index > 0) {
            index--;
            showExpense(expenses.get(index));
            down.setEnabled(true);
        }
        if (index == 0) {
            up.setEnabled(false);
        }
    }

    private void moveDown() {
        if (index < expenses.size() - 1) {
           index++;
            showExpense(expenses.get(index));
            up.setEnabled(true);
        }
        if (index == expenses.size() - 1) {
            down.setEnabled(false);
        }


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

