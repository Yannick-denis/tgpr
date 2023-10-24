package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.Error;
import tgpr.framework.ErrorList;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Repartition;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.AddExpenseView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

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
    public void save(List<Repartition> list){
        for (Repartition elem :list){
            Repartition rep =elem;
            rep.save();
        }
    }
    public ErrorList validateDate(String date,String title,double amount) {
        var erorr=new ErrorList();
         String today=LocalDate.now().asString();
        if (date.compareTo(today)>0){
            erorr.add("Date many not be in the future",Operation.Fields.CreatedAt);
        }
        if (date.compareTo("2023/00/00")<=0||date.compareTo("2023/00/01")<=0){
            erorr.add("margaux arette te betise",Operation.Fields.CreatedAt);
        }
        if(date.length()<10){

            erorr.add("respect format dd/mm/yyyy",Operation.Fields.CreatedAt);
        }
        if (amount<0){
            erorr.add("amount must be positive",Operation.Fields.Amount);
        }
        if(title.length()<3){
            erorr.add("minimum 3 chars",Operation.Fields.Title);
        }
        return erorr;
    }
    @Override
    public Window getView() {
        return view;
    }
}

