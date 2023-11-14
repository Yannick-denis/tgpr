package tgpr.tricount.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyType;
import tgpr.framework.*;
import tgpr.tricount.controller.ViewTemplatesController;
import tgpr.tricount.model.*;

import java.util.List;

import static tgpr.framework.Controller.askConfirmation;
import static tgpr.framework.ViewManager.gui;

public class ViewTemplatesView extends DialogWindow {
    private final ViewTemplatesController controller;

    private final Button btnPost = new Button("Post");
    private final Label errBody = new Label("");
    private final CheckBox chkPrivate = new CheckBox();
    private ObjectTable<Template> templateTable;
    private List<User> participant;
    private CheckBoxList<TemplateItem> boxitem;
    private ObjectTable<Template> temp;
    private CheckBox repartition;
    private List<TemplateItem> rep;

    private Tricount triC;
    private Template template;
    private User me;
    private Panel root;


    public ViewTemplatesView(ViewTemplatesController controller) {
        super("Tricount Repartition Templates");
        this.controller = controller;
        me = controller.getMe();
        triC = controller.getTricount();
        setHints(List.of(Hint.CENTERED));
        setCloseWindowWithEscape(true);
        root = Panel.verticalPanel();
        //list=temp.getTitle();
        setComponent(root);
        temp = new ObjectTable<>(
                new ColumnSpec<>("templates      ", Template::getTitle)
        ).addTo(root);
        temp.add(triC.getTemplates());

        if(triC.getTemplates() == null){
            new Label("No template yet").setForegroundColor(TextColor.ANSI.RED).addTo(root);
        }

        temp.addSelectionChangeListener((oldRow, newRow, byUser) -> {
            template = temp.getSelected();
            refrech();
            System.out.println(template.toString());
        });
        template = temp.getSelected();
        //securite
        rep = TemplateItem.getByTemplate(template.getId());
        participant=triC.getParticipants();

        for (User elem :participant){
            if (!isIn(elem)){
               rep.add(new TemplateItem(elem.getId(),template.getId(),0));
            }
        }
        new Label("Repartition : ")
                .addTo(root).addStyle(SGR.UNDERLINE)
                .setForegroundColor(new TextColor.RGB(128,128,128));
        boxitem=new CheckBoxList<>();
         for (TemplateItem elem:rep){
             boxitem.addItem(elem,elem.getWeight()==0?false:true);
         }
        boxitem.addListener((index,checked)-> boxitem.getSelectedItem().setWeight(checked?1:0) );

        this.addKeyboardListener(boxitem,keyStroke -> {
            var character = keyStroke.getCharacter();
            var type = keyStroke.getKeyType();
            if (type== KeyType.ArrowRight||character!=null&& character=='+'){
                boxitem.getSelectedItem().setWeight(boxitem.getSelectedItem().getWeight()+1);
                if (boxitem.getSelectedItem().getWeight()==1){
                    boxitem.setChecked(boxitem.getSelectedItem(),true);
                }
            }else if ((type== KeyType.ArrowLeft||character!=null &&character=='-')&&boxitem.getSelectedItem().getWeight()>0){
                boxitem.getSelectedItem().setWeight(boxitem.getSelectedItem().getWeight()-1);
                if (boxitem.getSelectedItem().getWeight()==0){
                    boxitem.setChecked(boxitem.getSelectedItem(),false);
                }
            }
            return  true;
        });


        boxitem.addTo(root);





        //createTemplatesItemList(temp).addTo(root);
        createButtons().addTo(root);

    }
    private boolean isIn(User user){
        for (TemplateItem elem :rep){
            if (elem.getUser().equals(user)){
                return true;
            }
        }
        return false;
    }

    private void refrech() {
        boxitem.clearItems();
        rep=TemplateItem.getByTemplate(template.getId());
        for (User elem :participant){
            if (!isIn(elem)){
                rep.add(new TemplateItem(elem.getId(),template.getId(),0));
            }
        }
        for (TemplateItem elem:rep){
            boxitem.addItem(elem,elem.getWeight()==0?false:true);
        }

    }

    private Panel createTemplatesList(Tricount tric) {


        Panel panel = new Panel().setLayoutManager(new GridLayout(1).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER);


        //ObjectTable<Template> templatetable
        //list des templates avec ">" comme curseur  et clickable pour changer de templates
        // le click doit changer aussi l'affichage de la repartition et répartir selon le template ou est placé le curseur


//        for(Template elem:list.getUSERname){
//            new Label(temp.getTitle()).addTo(panel);
//
//        }

        return panel;
    }

    private Panel createTemplatesItemList(Template temp) {


        Panel panel = new Panel().setLayoutManager(new GridLayout(4).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER);

        new Label("Repartition : ").addTo(panel).addStyle(SGR.UNDERLINE);
        /*IF (modifie)*/
        new Label("Repartition :(modified) ").addTo(panel).addStyle(SGR.UNDERLINE);// afficher uniquement si la repartition a étée modifiée
        //list des repartition avec les user impliquer cocher
/*La partie inférieure de la vue affiche la répartition associée au template couramment sélectionné (ici la répartition du template "Benoit ne paye rien").
Cette répartition peut être modifiée en cochant / décochant des participants et/ou en modifiant les poids au moyen des flèches du clavier (même principe que pour l'édition des opérations).
Une fois que la répartition a été modifiée, une indication "(modified)" est affichée.
Lorsqu'on sauve (bouton "Save"), on reçoit un message de confirmation et l'indication "(modified)" disparaît (voir ci-dessous).*/

        //besoin d'une liste de user qui font parti de ce tricount
        //CheckBoxList<TemplateItem> templateItemCheckBoxList;


//        panel.addComponent(new Label("for Whom :\n (wheight <-/-> or -/+)"));
//        check = new CheckBoxList<Repartition>();
//
//        for ( Repartition elme : rep) {
//            check.addItem(elme,true);
//        }
//        check.addListener((index,checked)-> check.getSelectedItem().setWeight(checked?1:0) );



        /* visuel
         * Repartition :
         * [] user1
         * [x] user2 on peut cocher et modifier le poids avec les flèches haut-bas [2]
         * [x] user3
         *
         * if on modifie le titre change a Repartition(modified)
         * Repartition (modified) :
         * [] user1
         * [x] user2 (2) on peut cocher et modifier le poids avec les flèches haut-bas,le poids s'affiche entre parenthèses
         * [x] user3
         *
         *
         *
         */


        return panel;
    }


    private Panel createButtons() {
        var panel = Panel.horizontalPanel().center();

        //btnPost.setEnabled(false).addTo(panel).addListener(button -> post());
        new Button("New", this::addTemplate).addTo(panel);
        new Button("Edit Title", this::editTemplate).addTo(panel);
        new Button("Delete", this::deleteTemplate).addTo(panel);
        new Button("Save", this::save).addTo(panel);// qui renverra vers le MessageDialogButton avec le message de confirmation et "modified" disparait dans le titre "Repartition:"
        new Button("Close", this::close).addTo(panel);


        return panel;
    }

    private void addTemplate() {
        // Controller.navigateTo(new AddTemplateController);
    }

    private void editTemplate() {
        // Controller.navigateTo(new EditTemplateController);
    }

    private void deleteTemplate() {
        if (askConfirmation("You're about to delete this the template: "+template.getTitle()+" Do you confirm!", "Delete Template")) {
            try{
                template.delete();
                this.close();
                Controller.navigateTo(new ViewTemplatesController(triC));
            }
            catch (Exception e){

            }
        }
    }

    static MessageDialogButton showMessage(String message, String title, MessageDialogButton... buttons) {
        return MessageDialog.showMessageDialog(gui, title, message, buttons);
    }

    private void save() {
       controller.save(rep);
    }


}
