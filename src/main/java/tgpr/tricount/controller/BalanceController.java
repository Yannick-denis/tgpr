package tgpr.tricount.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.view.BalanceView;
import tgpr.tricount.view.TestView;

public class BalanceController extends Controller {
    private final BalanceView view = new BalanceView(this);
    Label labelrouge = new Label("Hello, world! This is a colored label.")
            .setSize(new TerminalSize(10,1))
            .setForegroundColor(TextColor.ANSI.BLACK) // Choisir une couleur pour le texte
            .setBackgroundColor(TextColor.ANSI.RED); // Choisir une couleur pour l'arrière-plan

    @Override
    public Window getView() {
        return view;
    }



}
