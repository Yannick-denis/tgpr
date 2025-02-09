package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.ErrorList;
import tgpr.tricount.model.*;
import tgpr.tricount.view.AddExpenseView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class AddExpenseController extends Controller {
    private final AddExpenseView view;
    private  Operation operation;
    public Tricount getTricount() {
        return tricount;
    }
    private Tricount tricount;

    // besoin de recevoir l' Id du tricount pour savoir dans quel tricount nous somme
    public AddExpenseController(Tricount tricount) {
        this.tricount = tricount;
        tricount.setId(tricount.getId());
        view = new AddExpenseView(this);
    }

    public AddExpenseController(Tricount tricount, Operation operation) {
        this.tricount = tricount;
        tricount.setId(tricount.getId());
        this.operation=operation;
        view = new AddExpenseView(this, operation);
    }

    public int getIdTricount() {
        if (this.tricount == null) {
            return 1;
        }
        return this.tricount.getId();
    }

    // ecrit la depense en DB et ferme la fenetre
    public void save(String title, int tricountId,
                     double amount, LocalDate operationDate, int initiatorId,
                     LocalDateTime createdAt, Operation operation) {

        Operation ope;
        if (operation == null) {
            ope = new Operation(title, tricountId, amount, operationDate, initiatorId, createdAt);
        } else {
            ope = operation;
            ope.setAmount(amount);
            ope.setTitle(title);
            ope.setOperationDate(operationDate);
            ope.setInitiatorId(initiatorId);
            ope.setCreatedAt(createdAt);
        }
        ope.save();
        view.close();

    }

    public void save(List<Repartition> list) {
        for (Repartition elem : list) {
            Repartition rep = elem;
            if (rep.getWeight() == 0) {
                rep.delete();
            } else {
                rep.save();
            }
        }
    }
   /* public void saveTemplateItem(List<Repartition> list){
        for (Repartition repartition : list)
            addTemplateController.saveTempleItem();

    }
    */

    public ErrorList validateDate(String date, String title, double amount, List<Repartition> repartitions) {
        var erorr = new ErrorList();
        String today = LocalDate.now().asString();

        //faut il ignore la casse???? a voir demain
        if (Operation.getByTitle(title)!=null && (operation ==null||title.compareToIgnoreCase(operation.getTitle())!=0) ){
            erorr.add("Arely  exist", Operation.Fields.Title);
        }
        if (repartitions.size() < 1) {
            erorr.add("you must selected must least one", Operation.Fields.Repartition);
        }
        if (dateInvalide(date)) {
            erorr.add("respect format dd/mm/yyyy", Operation.Fields.CreatedAt);
        } else if (dateInTheFuture(date)) {
            erorr.add("Date many not be in the future", Operation.Fields.CreatedAt);
        }
        if (amount < 0) {
            erorr.add("amount must be positive", Operation.Fields.Amount);
        }
        if (title.length() < 3) {
            erorr.add("minimum 3 chars", Operation.Fields.Title);
        }
        return erorr;
    }

    private boolean dateInTheFuture(String date) {
        DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toFormatter();
        LocalDate dat = LocalDate.parse(date, format);
        return dat.isAfter(LocalDate.now());
    }

    private boolean dateInvalide(String date) {
        try {
            DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toFormatter();
            LocalDate dat = LocalDate.parse(date, format);

        } catch (Exception e) {
            return true;
        }
        return false;
    }

    @Override
    public Window getView() {
        return view;
    }

    public void delet(Operation operation) {
        if (askConfirmation("You are about to delete this expense. Please confirm.", "delete Expense")) {
            operation.delete();
            view.close();
        }
    }

    public void showMar() {
        showError("Margaux arrete tes betise !!!!!!!!!");
    }
}

