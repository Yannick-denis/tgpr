package tgpr.tricount;
import tgpr.framework.Controller;
import tgpr.framework.Model;
//<<<<<<< HEAD
//<<<<<<< HEAD
//<<<<<<< HEAD
//<<<<<<< HEAD
//<<<<<<< HEAD
import tgpr.tricount.controller.AddParticipantController;
//=======
import tgpr.tricount.controller.DeleteParticipantController;
//>>>>>>> feat_delete_participant
//=======
import tgpr.tricount.controller.AddTricountControler;
//>>>>>>> feat_add_tricount
import tgpr.tricount.controller.TestController;
import tgpr.tricount.model.User;

//=======



import tgpr.tricount.controller.AddParticipantController;

import tgpr.tricount.controller.DeleteParticipantController;


import tgpr.tricount.controller.AddExpenseController;


import tgpr.tricount.controller.AddParticipantController;

import tgpr.tricount.controller.DeleteParticipantController;

import tgpr.tricount.controller.LoginController;


import tgpr.tricount.controller.LoginController;


import tgpr.tricount.controller.TestController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Tricount;
//>>>>>>> abdd79b1e57528a825f1675de0b797dc20d75570
//=======
import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.controller.TestController;
import tgpr.tricount.view.AddTemplateView;

import java.awt.*;
//>>>>>>> add_template
//=======
import tgpr.tricount.controller.DeleteTricountController;
//>>>>>>> delete_tricount

public class TricountApp {
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";
  public static Tricount ticount;

    public static void main(String[] args) {
        if (!Model.checkDb(DATABASE_SCRIPT_FILE))
            Controller.abort("Database is not available!");
        else {
//<<<<<<< HEAD
//<<<<<<< HEAD
//<<<<<<< HEAD

//<<<<<<< HEAD
        //    Controller.navigateTo(new AddParticipantController(4,5)); //à changer dans edit (POUR MARGAUX)
          //  Controller.navigateTo(new DeleteParticipantController(2,1,true));
//=======
          //
            //  Controller.navigateTo(new AddTricountControler(User.getByKey(1)));
//>>>>>>> feat_add_tricount
//=======

//            Controller.navigateTo(new AddParticipantController(4,5)); //à changer dans edit (POUR MARGAUX)
//            Controller.navigateTo(new DeleteParticipantController(2,1,true));


         /*   ticount=Tricount.getByKey(4);
            Operation ope=Operation.getByTitle("ccc");
            Controller.navigateTo(new  AddExpenseController(ticount));
//            Controller.navigateTo(new DeleteParticipantController(4,3,true));

          */

//            Controller.navigateTo(new LoginController());
// pour les test de edit operation et add operation
// si vous voullez utuliser addoperation conctructeur avec tricount
//pour edit operation conctructeur avec tricount + operation


//>>>>>>> abdd79b1e57528a825f1675de0b797dc20d75570
//=======
           // Controller.navigateTo(new AddTemplateController());
//>>>>>>> add_template
//=======
            Controller.navigateTo(new DeleteTricountController("Gers 2002", null, 1));
//>>>>>>> delete_tricount
        }
    }
}
