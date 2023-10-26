package tgpr.tricount.view;
import com.googlecode.lanterna.gui2.*;
import tgpr.framework.ColumnSpec;
import tgpr.framework.ObjectTable;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Repartition;

import java.util.List;


public class ViewOperation  extends BasicWindow {
    private Operation operation;
    private  Repartition repartition;
    private List<Expense> expenses;
    private ObjectTable<Operation> table;
    private Button up;
    private  Button down;
    private int index = 0;
    private  Button edit;
    private  Button close;
    private Operation controller;
    public ViewOperation(/*List<Expense> expenses, */ Operation controller, Repartition repartition) {
        super("View Expense Detail");
        //this.expenses = expenses;
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
        contentPanel.addComponent(new Label(operation.getTitle()));
        contentPanel.addComponent(new Label("Amount:"));
        contentPanel.addComponent(new Label(String.valueOf(operation.getAmount())));
        contentPanel.addComponent(new Label("Date:"));
        contentPanel.addComponent(new Label (String.valueOf(operation.getOperationDate())));
        contentPanel.addComponent(new Label("Paid by:"));
        contentPanel.addComponent(new Label(operation.getInitiator().getFullName()));
        new EmptySpace().addTo(root);
       contentPanel.addComponent(new Label("From whom:" ));
       table = new ObjectTable<>(
               new ColumnSpec<>("Participant", o ->operation.getInitiator().getFullName()),
               new ColumnSpec<>("Weight",w ->repartition.getWeight()),
               new ColumnSpec<>("Amout",o ->operation.getAmount())
              // new ColumnSpec<>(),

       );

       /*
        contentPanel.addComponent(new TextBox("Participant", TextBox.Style.valueOf(operation.getInitiator().getFullName())));
       // contentPanel.addComponent(new TextBox("Weight",repartition.getWeight()));
        contentPanel.addComponent(new TextBox("Amout", TextBox.Style.valueOf(operation.getInitiator().getFullName())));
       */


        window.setComponent(contentPanel);

        MultiWindowTextGUI textGUI = null;
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
    public Operation getController() {
        return controller;
    }

    public void setController(Operation controller) {
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

