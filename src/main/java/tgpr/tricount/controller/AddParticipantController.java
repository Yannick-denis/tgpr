package tgpr.tricount.controller;
import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Subscription;
import tgpr.tricount.view.AddParticipantView;

public class AddParticipantController extends Controller {
    private final AddParticipantView view = new AddParticipantView(this);


    public AddParticipantController(int idP, int idT){ //idP = id du participant, idT = id du Tricount
        this.subscription = new Subscription(idT, idP);
        this.subscription.addNew();
    }
    @Override
    public Window getView(){
        return view;
    }
}
