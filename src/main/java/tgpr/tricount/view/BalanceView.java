package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.framework.Margin;
import tgpr.framework.Spacing;
import tgpr.tricount.controller.BalanceController;
import tgpr.tricount.controller.TestController;
import tgpr.tricount.model.Repartition;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.User;

import java.util.ArrayList;
import java.util.List;
/*
axel je tai mis en forme la vue jai ajoute une list pour tout les operation de lu ticount
la requete est faite tkt pas je l'ai ajoute dans le modele jai egalement ajouter une liste de participant
je pense quil faut ajoute un liste tmp pour le calcul des ballance afin de sommer tout les poids  pour ch   aque op
et divise le montant par ce resultat apres multiplier le res par le poids du user

ajoute la logique pour inverser le nom et le montant si pos et agrandir le baground en fonction du montant
en faisant je croit setSiz(nex TerminalSize(taille de sa balance ex 80 pour 80 euro))
 */
public class BalanceView extends DialogWindow {
    private final BalanceController controller;
    private User me = /*Security.getLoggedUser()*/User.getByKey(1);
    List <Repartition> rep;
    List<User> perticipant;


    public BalanceView(BalanceController controller) {
        super("Balance");
        this.controller = controller;
        setHints(List.of(Hint.CENTERED));
        //initialisation de la liste de toutes les repertition du tricount
        rep= Repartition.getAllByTricount(this.controller.getTricount().getId());
        //initialisation de la liste des participant du tricount
        perticipant=controller.getTricount().getParticipants();

        var root = new Panel().setLayoutManager(new LinearLayout(Direction.VERTICAL));

        compasantCentral().addTo(root);

        setComponent(root.withBorder(Borders.singleLine()));


        Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        Button btnClose = new Button("Close", this::close).addTo(buttons);

    }

    private Border createCell(String i) {
        return new Panel()
                .addComponent(new Label(i))
                .withBorder(Borders.singleLine());
    }
    private Panel compasantCentral(){
        Panel panel=new Panel().setLayoutManager(new GridLayout(3).setTopMarginSize(1).setVerticalSpacing(1));
        new Label("labalance negative").setBackgroundColor(TextColor.ANSI.RED).addTo(panel);
        new Label("|").addTo(panel);
        new Label(perticipant.get(0).getFullName()).addTo(panel);
        return panel;
    }


}
