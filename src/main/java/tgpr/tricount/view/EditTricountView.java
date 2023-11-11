package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Margin;
import tgpr.framework.Tools;
import tgpr.tricount.controller.EditTricountController;
import tgpr.tricount.model.Subscription;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EditTricountView extends DialogWindow {

    private EditTricountController controller;
    private Tricount tricount;
    private TextBox txtTitle; //min 3 char
    private TextBox txtDescription; //min 3 char
    private ActionListBox listeBox;
    private ComboBox<String> selectUser;

    private final Label errTitle = new Label("");
    private final Label errDescription = new Label("");

    private Button btnAdd;
    private ArrayList listPourCombo = (ArrayList) User.getAll();

    public EditTricountView(EditTricountController controller, Tricount tricount){
        super("Edit Tricount");
        this.controller = controller;
        this.tricount = tricount;

        setHints(List.of(Hint.CENTERED, Hint.FIXED_SIZE));
        setCloseWindowWithEscape(true);
        setFixedSize(new TerminalSize(70, 15));

        Panel root = Panel.verticalPanel();
        setComponent(root);

        createFieldsGrid().addTo(root);
    }

    private Panel createFieldsGrid() {
        var panel = Panel.gridPanel(2, Margin.of(1));

        new Label("Title:").addTo(panel);
        txtTitle = new TextBox().sizeTo(15).addTo(panel)
                .setTextChangeListener((txt, byUser) -> validateForEdit());
        panel.addEmpty();
        errTitle.addTo(panel)
                .setForegroundColor(TextColor.ANSI.RED);

        new Label("Description:").addTo(panel);
        txtDescription = new TextBox().sizeTo(30).addTo(panel)
                .setTextChangeListener((txt, byUser) -> validateForEdit());
        panel.addEmpty();
        errDescription.addTo(panel).setForegroundColor(TextColor.ANSI.RED);


        new Label("Subscribers:").addTo(panel);
        listeBox = new ActionListBox().addTo(panel);

        refresh();

        panel.addEmpty();

        comboBoxAndButton().addTo(panel);

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
                txtDescription.getText()
        );
        errTitle.setText(errors.getFirstErrorMessage(Tricount.Fields.Title));
        errDescription.setText(errors.getFirstErrorMessage(Tricount.Fields.Description));

        btnAdd.setEnabled(errors.isEmpty());
    }

    private void refresh(){
        listPourCombo = (ArrayList) User.getAll();

        selectUser = new ComboBox<>();
        selectUser.clearItems();

        selectUser.addItem("--- Select a User ---").isReadOnly();

        listeBox.clearItems();
        for (var participant : tricount.getParticipants()) {
            listPourCombo.remove(participant);
            var libelle = participant.toString();
            if (controller.isImplicate(participant.getId(), tricount.getId()))
                libelle += " (*)";
            listeBox.addItem(libelle, () -> {
                System.out.println(participant);
            });
        }

        for (var participant: listPourCombo) {
            selectUser.addItem(participant.toString());
        }

        // arrive pas à supprimer l'item de la combobox
    }
}
