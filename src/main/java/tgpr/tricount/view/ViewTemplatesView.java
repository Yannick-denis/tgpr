package tgpr.tricount.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import tgpr.framework.ColumnSpec;
import tgpr.framework.Controller;
import tgpr.framework.ObjectTable;
import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.controller.EditTricountController;
import tgpr.tricount.controller.ViewTemplatesController;
import tgpr.tricount.model.*;

import java.util.ArrayList;
import java.util.List;

import static tgpr.framework.Controller.askConfirmation;
import static tgpr.framework.Controller.navigateTo;
import static tgpr.framework.ViewManager.gui;

public class ViewTemplatesView extends DialogWindow {
    private final ViewTemplatesController controller;
    private List<User> participant;
    private CheckBoxList<TemplateItem> boxitem;
    private ObjectTable<Template> temp;

    private List<TemplateItem> rep;

    private Tricount triC;
    private Template template;
    private User me;
    private Panel root;
    private  Label repartitiontitle;


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
        temp.addSelectionChangeListener((oldRow, newRow, byUser) -> {
            template = temp.getSelected();
            temp.getItem(oldRow).setTitle(temp.getItem(oldRow).getTitle().replace("> ",""));
            temp.getItem(newRow).setTitle("> "+temp.getItem(newRow).getTitle());
            temp.refresh();
            refrech();
            System.out.println(template.toString());
        });
        template = temp.getSelected();
        if (template == null) {
            new Label("No template yet").setForegroundColor(TextColor.ANSI.RED).addTo(root);

        } else {
            rep = TemplateItem.getByTemplate(template.getId());
            participant = triC.getParticipants();

            for (User elem : participant) {
                if (!isIn(elem)) {
                    rep.add(new TemplateItem(elem.getId(),0, 0));
                }
            }
            repartitiontitle= new Label("Repartition : ")
                    .addTo(root).addStyle(SGR.UNDERLINE)
                    .setForegroundColor(new TextColor.RGB(128, 128, 128));
            boxitem = new CheckBoxList<>();
            for (TemplateItem elem : rep) {
                boxitem.addItem(elem, elem.getWeight() == 0 ? false : true);
            }
            boxitem.addListener((index, checked) -> {
                boxitem.getSelectedItem().setWeight(checked ? 1 : 0);
                repartitiontitle.setText("Repartition : (modifed)");
            });

            this.addKeyboardListener(boxitem, keyStroke -> {

                var character = keyStroke.getCharacter();
                var type = keyStroke.getKeyType();
                if (type == KeyType.ArrowRight || character != null && character == '+') {
                    boxitem.getSelectedItem().setWeight(boxitem.getSelectedItem().getWeight() + 1);
                    repartitiontitle.setText("Repartition : (modified) ");
                    if (boxitem.getSelectedItem().getWeight() == 1) {
                        boxitem.setChecked(boxitem.getSelectedItem(), true);
                    }
                } else if ((type == KeyType.ArrowLeft || character != null && character == '-') && boxitem.getSelectedItem().getWeight() > 0) {
                    boxitem.getSelectedItem().setWeight(boxitem.getSelectedItem().getWeight() - 1);
                    repartitiontitle.setText("Repartition : (modified) ");
                    if (boxitem.getSelectedItem().getWeight() == 0) {
                        boxitem.setChecked(boxitem.getSelectedItem(), false);
                    }
                }
                return true;
            });

            boxitem.addTo(root);
        }
        createButtons().addTo(root);

    }

    private boolean isIn(User user) {
        for (TemplateItem elem : rep) {
            if (elem.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    private void refrech() {
        boxitem.clearItems();
        repartitiontitle.setText("Repartition :");
        rep = TemplateItem.getByTemplate(template.getId());
        for (User elem : participant) {
            if (!isIn(elem)) {
                rep.add(new TemplateItem(elem.getId(), template.getId(), 0));
            }
        }
        for (TemplateItem elem : rep) {
            boxitem.addItem(elem, elem.getWeight() == 0 ? false : true);
        }

    }

    private Panel createButtons() {

        var panel = Panel.horizontalPanel().center();

        //btnPost.setEnabled(false).addTo(panel).addListener(button -> post());

        new Button("New",()->{
            Controller.navigateTo(new AddTemplateController(new Template(null, triC.getId()),triC,null));
            this.close();
            Controller.navigateTo(new ViewTemplatesController(triC));
        }).addTo(panel);
        new Button("Edit Title", ()->{
            List<Repartition> litsForEdit=new ArrayList<>();
            for (TemplateItem elem :rep){
                litsForEdit.add(new Repartition(0,elem.getUserId(),elem.getWeight()));
            }
            template.setTitle(template.getTitle().replace("< ",""));
            Controller.navigateTo(new AddTemplateController(template ,triC,litsForEdit));
            template.setTitle("> "+template.getTitle());
            temp.refresh();
            Controller.navigateTo(new ViewTemplatesController(triC));
        }).setEnabled(template != null).addTo(panel);
        Button btndel= new Button("Delete", this::deleteTemplate).setEnabled(template != null).addTo(panel);
        addShortcut(btndel, KeyStroke.fromString("<A-d>"));
        Button btnsave= new Button("Save",()->{
            save();
            refrech();

        }).setEnabled(template != null).addTo(panel);
        addShortcut(btnsave, KeyStroke.fromString("<A-s>"));
       Button btnclose=  new Button("Close", this::close).addTo(panel);
        addShortcut(btnclose, KeyStroke.fromString("<A-c>"));


        return panel;
    }



    private void deleteTemplate() {
        if (askConfirmation("You're about to delete this the template: " + template.getTitle() + " Do you confirm!", "Delete Template")) {
            try {
                template.delete();
                this.close();
                Controller.navigateTo(new ViewTemplatesController(triC));
            } catch (Exception e) {

            }
        }
    }

    private void save() {
        if (askConfirmation("Do you want save ","Save confirmation")){
        controller.save(rep);
        close();
        Controller.navigateTo(new EditTricountController(triC));
        }
    }


}
