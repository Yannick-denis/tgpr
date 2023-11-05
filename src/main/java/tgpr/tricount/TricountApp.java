package tgpr.tricount;

import tgpr.framework.Controller;
import tgpr.framework.Model;
import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.controller.TestController;
import tgpr.tricount.view.AddTemplateView;

import java.awt.*;

public class TricountApp {
    public final static String DATABASE_SCRIPT_FILE = "/database/tgpr-2324-c01.sql";

    public static void main(String[] args) {
        if (!Model.checkDb(DATABASE_SCRIPT_FILE))
            Controller.abort("Database is not available!");
        else {
            Controller.navigateTo(new AddTemplateController());
        }
    }
}
