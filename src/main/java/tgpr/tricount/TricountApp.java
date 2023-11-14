package tgpr.tricount;
import tgpr.framework.Controller;
import tgpr.framework.Model;
import tgpr.tricount.controller.LoginController;


public class TricountApp {
/*
Racorci clavier :
                alt+c => close la vue acuetel
                alt+l =>login
                alt+s => save
                alt +s =>sinup
                alt +f =>menufile
 */
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";
    public static void main(String[] args) {

        if (!Model.checkDb(DATABASE_SCRIPT_FILE)) {
            Controller.abort("Database is not available!");



        } else {
               Controller.navigateTo(new LoginController());

        }
    }

}
