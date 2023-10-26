package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.view.add_tricountView;
import tgpr.tricount.view.view_tricountView;

public class view_tricountController extends Controller {
    @Override
    public Window getView() {
        return (Window) new view_tricountView("View Tricount Details");

    }
}