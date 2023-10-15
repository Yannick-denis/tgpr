package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.Error;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.AddExpenseView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class AddExpenseController extends Controller {
    private final AddExpenseView view ;


    public Tricount getTricount() {
        return tricount;
    }

    private  Tricount tricount ;
    // besoin de recevoir l' Id du tricount pour savoir dans quel tricount nous somme
    public  AddExpenseController(Tricount tricount) {
        this.tricount = tricount;
        tricount.setId(tricount.getId());
       view =new AddExpenseView(this);
    }
    public int getIdTricount(){
        if (this.tricount==null){
            return 1;
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
    public Error validateDate(String date) {
        Error erorr=new Error("");
         String today=LocalDate.now().asString();
        if (date.compareTo(today)>0){
            erorr=new Error("pas de date dans le future");
        }
        return erorr;
    }
    @Override
    public Window getView() {
        return view;
    }
}

