package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.view.AddParticipantView;
import tgpr.tricount.view.EditTricountView;

public class EditTricountController extends Controller {
    private final EditTricountView view = new EditTricountView(this);
    @Override
    public Window getView() {
        return view;
    }


}
