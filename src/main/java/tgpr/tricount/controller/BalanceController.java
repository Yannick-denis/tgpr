package tgpr.tricount.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Repartition;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;
import tgpr.tricount.view.BalanceView;
import tgpr.tricount.view.TestView;

import java.util.List;

public class BalanceController extends Controller {
    private final BalanceView view ;
    List<Repartition> reps;
    List<Operation> opes;
    List<User> perticipant;
    private User me = /*Security.getLoggedUser()*/User.getByKey(1);//il faudra decomenter Security.getLoggedUser
    // et suprimer User.getByKey(1);

    public Tricount getTricount() {
        return tricount;
    }

    private Tricount tricount;

    public BalanceController(Tricount tricount){
        this.tricount=tricount;
        //initialisation de la liste de toutes les repertition du tricount
        reps= Repartition.getAllByTricount(this.tricount.getId());
        //initialisation de la liste des participant du tricount
        perticipant=this.tricount.getParticipants();
        //une list des operation du tricount
        opes = this.tricount.getOperations();
        view = new BalanceView(this);
    }


    @Override
    public Window getView() {
        return view;
    }


//    public double balance() {
//        double res =0;
//        int poids=0;
//        int monpids=0;
//        double amountTotal=0;
//        for (Repartition elem :reps){
//            poids+= elem.getWeight();
//            if (me.getId()==elem.getUserId()){
//                monpids+=elem.getWeight();
//            }
//        }
//        for (Operation elem :opes){
//            amountTotal=+elem.getAmount();
//        }
//        System.out.println(amountTotal);
//        System.out.println(poids);
//        System.out.println(monpids);
//        res=(amountTotal/poids)*monpids;
//        return res;
//    }
}
