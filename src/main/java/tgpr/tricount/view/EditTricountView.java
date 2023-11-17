package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Controller;
import tgpr.framework.Margin;
import tgpr.framework.Model;
import tgpr.tricount.controller.EditTricountController;
import tgpr.tricount.controller.TricountListController;
import tgpr.tricount.controller.ViewTemplatesController;
import tgpr.tricount.model.Subscription;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;

import java.util.ArrayList;
import java.util.List;

import static tgpr.framework.Controller.*;

public class EditTricountView extends DialogWindow {

    private EditTricountController controller;

    private Tricount tricount;
    private TextBox txtTitle = new TextBox(); //min 3 char
    private TextBox txtDescription = new TextBox(); //min 3 char
    private ActionListBox listeBox;
    private ComboBox<String> selectUser = new ComboBox<>();

    private final Label errTitle = new Label("");
    private final Label errDescription = new Label("");

    private Button btnAdd;
    private List<User> listPourCombo = User.getAll();
    private List<User> listPourAction = new ArrayList<>();
    private Button btnDelete;
    private Button btnCancel;
    private Button btnSave;
    private Button btnTemplates;
    private final String titleOriginal;


    public EditTricountView(EditTricountController controller, Tricount tricount){
        super("Edit Tricount");
        this.controller = controller;
        this.tricount = tricount;
        titleOriginal = tricount.getTitle();

        setHints(List.of(Hint.CENTERED));
        setCloseWindowWithEscape(true);

        Panel root = Panel.verticalPanel();
        setComponent(root);

        createFieldsGrid().addTo(root);

    }

    private Panel createFieldsGrid() {
        var panel = Panel.gridPanel(2, Margin.of(1));

        new Label("Title:").addTo(panel);
        txtTitle = new TextBox().sizeTo(15).addTo(panel)
                .setTextChangeListener((txt, byUser) -> {
                    if(byUser)
                        validateForEdit();
                }).setText(tricount.getTitle());
        panel.addEmpty();
        errTitle.addTo(panel)
                .setForegroundColor(TextColor.ANSI.RED);

        new Label("Description:").addTo(panel);
        if(tricount.getDescription() == null) {
            txtDescription = new TextBox().setPreferredSize(new TerminalSize(30, 4)).addTo(panel)
                    .setTextChangeListener((txt, byUser) -> validateForEdit());
        }
        else {
            txtDescription = new TextBox().setPreferredSize(new TerminalSize(30, 4)).addTo(panel)
                    .setTextChangeListener((txt, byUser) -> {
                        if(byUser)
                            validateForEdit();
                    }).setText(tricount.getDescription());
        }

        panel.addEmpty();
        errDescription.addTo(panel).setForegroundColor(TextColor.ANSI.RED);


        new Label("Subscribers:").addTo(panel);
        listeBox = new ActionListBox().addTo(panel);

        refresh();

        panel.addEmpty();

        comboBoxAndButton().addTo(panel);

        panel.addEmpty();

        btn().addTo(panel);

        return panel;
    }

    private Panel comboBoxAndButton() {
        var panel = Panel.gridPanel(3, Margin.of(1));


        selectUser.addTo(panel);


        btnAdd = new Button("Add", () -> {
            add(selectUser.getSelectedItem());
            refresh();
        }).addTo(panel);

        return panel;
    }


    private Panel btn(){
        var panel = Panel.gridPanel(4, Margin.of(1));

        btnDelete = new Button("Delete", () -> {
            deleteT();
        }
        ).addTo(panel);

        //if()
        btnSave = new Button("Save", () -> {
            this.save();
            this.close();
        }).addTo(panel);
        addShortcut(btnSave, KeyStroke.fromString("<A-s>"));
        btnSave.setEnabled(false);

        btnTemplates = new Button("Templates", () -> {

            Controller.navigateTo(new ViewTemplatesController(tricount));
        }
        ).addTo(panel);

        btnCancel = new Button("Cancel", ()->this.close()).addTo(panel);
        addShortcut(btnCancel, KeyStroke.fromString("<A-c>"));

        return panel;
    }

    private void add(String nomUser){
        try {
            controller.add(User.getByFullName(nomUser).getId(), tricount.getId());
        }
        catch(Exception e){

        }
    }

    private void validateForEdit() {
        var errors = controller.validateForEdit(
                txtTitle.getText(),
                txtDescription.getText(),
                titleOriginal
        );
        errTitle.setText(errors.getFirstErrorMessage(Tricount.Fields.Title));
        errDescription.setText(errors.getFirstErrorMessage(Tricount.Fields.Description));

        btnSave.setEnabled(errors.isEmpty());
    }

    private void refresh(){
        listPourCombo = User.getAll();

        listeBox.clearItems();

        Model.clearCache();

        for (User participant : tricount.getParticipants()) {
            listPourCombo.remove(participant);
            //listPourAction.add(participant); a completer plus tard si le temps
            var libelle = participant.toString();
            if (controller.isImplicate(participant.getId(), tricount.getId()))
                libelle += " (*)";
            listeBox.addItem(libelle, () -> {
                delete(participant);
            });
        }

        selectUser.clearItems();
        selectUser.addItem("--- Select a User ---").isReadOnly();
        for (User participant: listPourCombo) {
            selectUser.addItem(participant.toString());
        }
    }

    private void delete(User particpant){
        Subscription sub = new Subscription(tricount.getId(), particpant.getId());
        if (!controller.isImplicate(particpant.getId(),tricount.getId())&&!tricount.getCreator().equals(particpant)){
            try{
            sub.delete();
            listPourCombo.add(particpant);
            }
            catch(Exception e){
                showError("Ne peut pas etre supp");
            }
        }
        else {
            showError("This user is involved in an expense or creator");
        }
        refresh();
    }

    private void save (){
        tricount.setTitle(txtTitle.getText());
        tricount.setDescription(txtDescription.getText());
        tricount.save();
    }

    private void deleteT (){
        if (askConfirmation("You're about to delete this tricount. Do you confirm!", "Delete Tricount")) {
            tricount.delete();
            Controller.navigateTo(new TricountListController());
        }
    }
}
