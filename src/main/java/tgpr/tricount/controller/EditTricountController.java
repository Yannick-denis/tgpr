package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.ErrorList;
import tgpr.tricount.model.Repartition;
import tgpr.tricount.model.Subscription;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;
import tgpr.tricount.view.AddParticipantView;
import tgpr.tricount.view.EditTricountView;

public class EditTricountController extends Controller {
    private final EditTricountView view;
    private Tricount tricount;

    public EditTricountController(Tricount tricount){
        this.tricount = tricount;
        view = new EditTricountView(this, this.tricount);
    }

    @Override
    public Window getView() {
        return view;
    }

    public boolean isImplicate(int idU, int idT){
        return Repartition.isImplicate(User.getByKey(idU), idT);
    }

    public void add(int idP, int idT){ //idP = id du participant, idT = id du Tricount
        Subscription subscription = new Subscription(idT, idP);
        subscription.addNew();
    }

    public ErrorList validateForEdit(String title, String descriptions){
        var error = new ErrorList();
        if (title.length() < 3){
            error.add("Minimum 3 chars", Tricount.Fields.Title);
        }
        if (descriptions.length() < 3){
            error.add("Minimum 3 chars", Tricount.Fields.Description);
        }
        return error;
    }

}
