package tgpr.tricount;
import tgpr.framework.Controller;
import tgpr.framework.Model;


import tgpr.tricount.controller.AddParticipantController;

import tgpr.tricount.controller.DeleteParticipantController;

import tgpr.tricount.controller.AddTricountControler;

import tgpr.tricount.controller.TestController;
import tgpr.tricount.model.User;




import tgpr.tricount.controller.AddParticipantController;
import tgpr.tricount.controller.DeleteParticipantController;


import tgpr.tricount.controller.AddExpenseController;


import tgpr.tricount.controller.AddParticipantController;

import tgpr.tricount.controller.DeleteParticipantController;

import tgpr.tricount.controller.LoginController;


import tgpr.tricount.controller.LoginController;


import tgpr.tricount.controller.TestController;
import tgpr.tricount.model.Operation;

import tgpr.tricount.controller.EditTricountController;
import tgpr.tricount.controller.TestController;

import tgpr.tricount.model.Tricount;

import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.controller.TestController;
import tgpr.tricount.view.AddTemplateView;

import java.awt.*;

import tgpr.tricount.controller.DeleteTricountController;


public class TricountApp {
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";

    public static void main(String[] args) {
        if (!Model.checkDb(DATABASE_SCRIPT_FILE)) {
            //Controller.abort("Database is not available!");
        }
        else {

            //    Controller.navigateTo(new AddParticipantController(4,5)); //à changer dans edit (POUR MARGAUX)
            //  Controller.navigateTo(new DeleteParticipantController(2,1,true));

            //
            //  Controller.navigateTo(new AddTricountControler(User.getByKey(1)));



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


            // Controller.navigateTo(new EditTricountController(Tricount.getByKey(4))); //à changer dans edit (POUR MARGAUX)


            // Controller.navigateTo(new AddTemplateController());

            Controller.navigateTo(new DeleteTricountController("Gers 2002", null, 1));


            //Controller.navigateTo(new OperationController(Operation.getByKey(3)));

        }

    }
}
