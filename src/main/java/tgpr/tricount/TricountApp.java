package tgpr.tricount;

import tgpr.framework.Controller;
import tgpr.framework.Model;
<<<<<<< HEAD
import tgpr.tricount.controller.LoginController;
=======
import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.controller.LoginController;
import tgpr.tricount.model.Template;
import tgpr.tricount.model.Tricount;
>>>>>>> 4e2e3a7eae3b9b2a6755ad1630c49488210567b2


public class TricountApp {
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";
    public static void main(String[] args) {
        if (!Model.checkDb(DATABASE_SCRIPT_FILE)) {
            Controller.abort("Database is not available!");
        } else {
            Controller.navigateTo(new LoginController());

        }
    }

}
