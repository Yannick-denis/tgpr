package tgpr.tricount.view;
import com.googlecode.lanterna.gui2.*;
import tgpr.framework.ColumnSpec;
import tgpr.framework.ObjectTable;
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
    private int index = 0;
    private  Button edit;
    private  Button close;
    private Operation controller;
    public ViewOperation(/*List<Expense> expenses, */ OperationController controller) {
        super("View Expense Detail");
        //this.expenses = expenses;
       // setTitle(operation.getTitle());
        setHints(List.of(Hint.EXPANDED));
        Panel root = new Panel();
        setComponent(root);

        Panel contentPanel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1)).addTo(root);
/*
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

        window.setComponent(buttonPanel);

         */
        GridLayout gridLayout = new GridLayout(4);

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
        new EmptySpace();
     /*   BasicWindow window = new BasicWindow();
        window.setComponent(contentPanel);

      */
       contentPanel.addComponent(new Label("From whom:" ));
       table = new ObjectTable<>(
               new ColumnSpec<>("Participant", Repartition::getUser),
               new ColumnSpec<>("Weight", Repartition::getWeight)
               //new ColumnSpec<>("Amout", Operation::getAmount)
              // new ColumnSpec<>(),
       );

       contentPanel.addComponent(table);


       // contentPanel.addComponent(new TextBox("Participant", TextBox.Style.valueOf(operation.getInitiator().getFullName())));
       // contentPanel.addComponent(new TextBox("Weight",repartition.getWeight()));
        // contentPanel.addComponent(new TextBox("Amout", TextBox.Style.valueOf(operation.getInitiator().getFullName())));


/*


        MultiWindowTextGUI textGUI = null;
        textGUI.addWindowAndWait(window);

 */
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

