package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.view.carteTricountView;

public class carteTricountController extends Controller {
     private final carteTricountView view;

    public carteTricountController(carteTricountView view) {
        this.view = view;
    }

    @Override
    public Window getView() {
        return new carteTricountView();
    }
}
