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
import tgpr.tricount.controller.OperationController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.view.ViewOperation;
import java.awt.*;
import tgpr.tricount.controller.DeleteTricountController;
 public class TricountApp {
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";
    public static void main(String[] args) {
        if (!Model.checkDb(DATABASE_SCRIPT_FILE)) {
            Controller.abort("Database is not available!");
        } else {


            //    Controller.navigateTo(new AddParticipantController(4,5)); //à changer dans edit (POUR MARGAUX)

        }
    }

}
