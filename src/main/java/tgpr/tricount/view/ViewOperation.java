package tgpr.tricount.view;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import tgpr.framework.ColumnSpec;
import tgpr.framework.ObjectTable;
import tgpr.tricount.controller.ViewOperationController;
import tgpr.tricount.model.Operation;
import java.util.List;


public abstract class ViewOperation  extends BasicWindow {
    private Operation operation;
    private List<Expense> expenses;
    private ObjectTable<Operation> table;
    private Button up;
    private  Button down;
    private  Button edit;
    private  Button close;
    private ViewOperationController controller;
    public ViewOperation(List<Expense> expenses, ViewOperationController controller) {
        super("View Expense Detail");
        this.expenses = expenses;
        this.controller = controller;
        setTitle(operation.getTitle());
        setHints(List.of(Hint.EXPANDED));
        Panel root = new Panel();
        setComponent(root);

        // = new TextBox().setPreferredSize(new TerminalSize(40,1));
    }

    Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
    //up = new Button("Up",)

    public void addExpense(String paidBy, double amount) {
        amount = operation.getAmount();
        expenses.add(new Expense(paidBy,amount));
    }

    public double getBalance(String person) {
        double balance = 0;
        for (Expense expense : expenses) {
            if (expense.paidBy.equals(person)) {
                balance -= operation.getAmount();
            } else {
                balance += (operation.getAmount() / operation.getId());
            }
        }
        return balance;
    }
    public ViewOperationController getController() {
        return controller;
    }

    public void setController(ViewOperationController controller) {
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

    public static class Expense {
        private String paidBy;

        public Expense(String paidBy, double amount) {
            this.paidBy = paidBy;
        }

    }
}

