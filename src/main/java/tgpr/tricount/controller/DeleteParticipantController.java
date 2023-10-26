package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Subscription;
import tgpr.tricount.view.TestView;

//pas vraiment un controleur plutot quelque fonction a inclure dans viewTricount
public class DeleteParticipantController extends Controller {

 private  Subscription sub;
 private boolean beDeleted;
    public DeleteParticipantController(int idTricount,int idUser ,boolean beDeleted){
        this.sub=new Subscription(idTricount,idUser);
        this.beDeleted=beDeleted;
        delete();

    }

    private void delete(){

        if (beDeleted){
            try {
                sub.delete();
            }catch (Exception e){
                showError("this user be not deledeted because not in tricount");
            }
            sub=null;
        }
        else {
            showError("this user be not deledeted");
        }
    }
 

    @Override
    public Window getView() {
        return new TestView(new TestController());
    }
}
