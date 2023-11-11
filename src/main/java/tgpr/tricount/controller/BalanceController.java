package tgpr.tricount.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.BalanceView;
import tgpr.tricount.view.TestView;

public class BalanceController extends Controller {
    private final BalanceView view ;

    public Tricount getTricount() {
        return tricount;
    }

    private Tricount tricount;

    public BalanceController(Tricount tricount){
        this.tricount=tricount;
        view = new BalanceView(this);


    }


    @Override
    public Window getView() {
        return view;
    }



}
