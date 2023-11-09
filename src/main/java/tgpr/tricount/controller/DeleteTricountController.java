package tgpr.tricount.controller;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;

import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.TestView;


import static tgpr.framework.Controller.askConfirmation;

public class DeleteTricountController extends Controller {
    private Tricount tricount;

    private Button button;

    public DeleteTricountController(String title, String destription, int creator) {
        this.tricount = new Tricount(title, destription, creator);

    }

    public void delete() {
        if (askConfirmation("You're about to delete this tricount. Do you confirm!", "Delete Tricount")) {
            tricount.delete();
            // La branche n'existe pas encore, je l'ai déjà ecrit.
            // viewTricount.close();
        }

    }

    public Window getView() {
        return new TestView(new TestController());
    }
}
