package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.AddExpenseView;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddExpenseController extends Controller {
    private final AddExpenseView view = new AddExpenseView(this);





    private  Tricount tricount;
    // besoin de recevoir l' Id du tricount pour savoir dans quel tricount nous somme
    public  AddExpenseController(Tricount tricount) {
        this.tricount = new Tricount();
        tricount.setId(tricount.getId());
    }
    public int getIdTricount(){
        if (this.tricount==null){
            return 0;
        }
        return this.tricount.getId();
    }
    // ecrit la depense en DB et ferme la fenetre
    public void save(String title, int tricountId,
                     double amount, LocalDate operationDate, int initiatorId,
                     LocalDateTime createdAt) {
        Operation ope =new Operation(title,tricountId,amount,operationDate,initiatorId,createdAt);
        ope.save();
        view.close();

    }
    @Override
    public Window getView() {
        return view;
    }
}
