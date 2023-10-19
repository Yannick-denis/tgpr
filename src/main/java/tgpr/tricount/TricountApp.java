package tgpr.tricount;

import tgpr.framework.Controller;
import tgpr.framework.Model;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 936800f97b144a3e386b1b8ae474b5847428a980
import tgpr.tricount.controller.AddParticipantController;

import tgpr.tricount.controller.DeleteParticipantController;
<<<<<<< HEAD
>>>>>>> feat_delete_participant
=======
import tgpr.tricount.controller.LoginController;
>>>>>>> feat_login
=======

import tgpr.tricount.controller.LoginController;

>>>>>>> 936800f97b144a3e386b1b8ae474b5847428a980
import tgpr.tricount.controller.TestController;

public class TricountApp {
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";

    public static void main(String[] args) {
        if (!Model.checkDb(DATABASE_SCRIPT_FILE))
            Controller.abort("Database is not available!");
        else {


//            Controller.navigateTo(new AddParticipantController(4,5)); //à changer dans edit (POUR MARGAUX)
//            Controller.navigateTo(new DeleteParticipantController(2,1,true));

            Controller.navigateTo(new LoginController());

        }
    }
}
