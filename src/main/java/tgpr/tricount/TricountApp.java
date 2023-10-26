package tgpr.tricount;

import tgpr.framework.Controller;
import tgpr.framework.Model;
import tgpr.tricount.controller.AddParticipantController;
import tgpr.tricount.controller.EditTricountController;
import tgpr.tricount.controller.TestController;
import tgpr.tricount.model.Tricount;

public class TricountApp {
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";

    public static void main(String[] args) {
        if (!Model.checkDb(DATABASE_SCRIPT_FILE))
            Controller.abort("Database is not available!");
        else {
            Controller.navigateTo(new EditTricountController(Tricount.getByKey(4))); //à changer dans edit (POUR MARGAUX)
        }
    }
}
