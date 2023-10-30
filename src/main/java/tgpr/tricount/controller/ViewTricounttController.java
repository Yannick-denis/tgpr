package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.view.ViewTricountView;

public class ViewTricounttController extends Controller {
    @Override
    public Window getView() {
        return (Window) new ViewTricountView("View Tricount Details");

    }
}