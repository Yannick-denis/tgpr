package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.view.AddTemplateView;

public class AddTemplateController extends Controller {
    private AddTemplateView addTemplateView;


    @Override
    public Window getView() {
        return new AddTemplateView(this);
    }
}
