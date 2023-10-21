package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.TestView;

import javax.xml.crypto.Data;
import java.util.Date;

import static tgpr.framework.Controller.askConfirmation;

public class deleteTricountController {
    private Tricount tricount;


    public deleteTricountController(String title, String destription, int creator) {
        this.tricount = new Tricount(title, destription, creator);

    }

    public void delete() {
        if (askConfirmation("You're about to delete this tricount. Do you confirm!", "Delete Tricount")) {
            tricount.delete();
            // La branche n'existe pas encore, je l'ai déjà ecrit.
            // viewTricount.close();
        }

    }
}
