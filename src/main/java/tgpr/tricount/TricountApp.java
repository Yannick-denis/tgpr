package tgpr.tricount;

import tgpr.framework.Controller;
import tgpr.framework.Model;
<<<<<<< HEAD
import tgpr.tricount.controller.AddParticipantController;
=======
import tgpr.tricount.controller.DeleteParticipantController;
>>>>>>> feat_delete_participant
import tgpr.tricount.controller.TestController;

public class TricountApp {
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";

    public static void main(String[] args) {
        if (!Model.checkDb(DATABASE_SCRIPT_FILE))
            Controller.abort("Database is not available!");
        else {

            Controller.navigateTo(new AddParticipantController(4,5)); //à changer dans edit (POUR MARGAUX)
            Controller.navigateTo(new DeleteParticipantController(2,1,true));
        }
    }
}
