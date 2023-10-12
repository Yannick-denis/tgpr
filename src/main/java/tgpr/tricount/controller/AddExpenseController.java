package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.view.AddExpenseView;

public class AddExpenseController extends Controller {
    private final AddExpenseView view = new AddExpenseView(this);


    @Override
    public Window getView() {
        return view;
    }
}
