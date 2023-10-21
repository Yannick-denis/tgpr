package tgpr.tricount;
import tgpr.framework.Controller;
import tgpr.framework.Model;
import tgpr.tricount.controller.DeleteTricountController;

public class TricountApp {
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";

    public static void main(String[] args) {
        if (!Model.checkDb(DATABASE_SCRIPT_FILE))
            Controller.abort("Database is not available!");
        else {
            Controller.navigateTo(new DeleteTricountController("Gers 2002", null, 1));
        }
    }
}
