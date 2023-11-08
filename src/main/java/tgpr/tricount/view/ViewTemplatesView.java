package tgpr.tricount.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.framework.Margin;
import tgpr.framework.ObjectTable;
import tgpr.tricount.controller.ViewTemplatesController;
import tgpr.tricount.controller.ViewTricoutController;
import tgpr.tricount.model.*;

import java.util.List;

public class ViewTemplatesView extends DialogWindow {
    private final ViewTemplatesController controller;

    private final Button btnPost = new Button("Post");
    private final Label errBody = new Label("");
    private final CheckBox chkPrivate = new CheckBox();
    private ObjectTable<Template> templateTable;
      private CheckBoxList<TemplateItem> templateItem;
    private Tricount triC;
    private Template temp;
    private User me;

    public ViewTemplatesView(ViewTemplatesController controller) {
        super("Tricount Repartition Templates");
        this.controller=controller;
        setHints(List.of(Hint.CENTERED, Hint.MODAL));
        setCloseWindowWithEscape(true);
        Panel root = Panel.verticalPanel();
        setComponent(root);
        createTemplatesList(triC).addTo(root);
        createTemplatesItemList(temp).addTo(root);
        createButtons().addTo(root);

    }

    private Panel createTemplatesList(Tricount tric) {


        Panel panel = new Panel().setLayoutManager(new GridLayout(4).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER);

        new Label("Templates : ").addTo(panel).addStyle(SGR.UNDERLINE);
       //list des templates avec ">" comme curseur

        return panel;
    }
    private Panel createTemplatesItemList(Template temp) {


        Panel panel = new Panel().setLayoutManager(new GridLayout(4).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER);

        new Label("Repartition : ").addTo(panel).addStyle(SGR.UNDERLINE);
        new Label("Repartition :(modified) ").addTo(panel).addStyle(SGR.UNDERLINE);// afficher uniquement si la repartition a étée modifiée
        //list des repartition avec les user impliquer cocher
/*La partie inférieure de la vue affiche la répartition associée au template couramment sélectionné (ici la répartition du template "Benoit ne paye rien").
Cette répartition peut être modifiée en cochant / décochant des participants et/ou en modifiant les poids au moyen des flèches du clavier (même principe que pour l'édition des opérations).
Une fois que la répartition a été modifiée, une indication "(modified)" est affichée.
Lorsqu'on sauve (bouton "Save"), on reçoit un message de confirmation et l'indication "(modified)" disparaît (voir ci-dessous).*/
        return panel;
    }

    private Panel createConfirmation(Tricount tric) {
        Panel panel = new Panel().setLayoutManager(new GridLayout(4).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER);
        new Label("The template repartition has been updated ").addTo(panel);



        return panel;

    }






    private Panel createButtons() {
        var panel = Panel.horizontalPanel().center();

        //btnPost.setEnabled(false).addTo(panel).addListener(button -> post());
        new Button("New", this::close).addTo(panel);
        new Button("Edit Title", this::close).addTo(panel);
        new Button("Delete", this::close).addTo(panel);
        new Button("Save", this::close).addTo(panel);
        new Button("Close", this::close).addTo(panel);
       // new Button("Ok", this::close).addTo(panel);//bouton du paneau de confirmation


        return panel;
    }

}
