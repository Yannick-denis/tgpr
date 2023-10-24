package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Margin;
import tgpr.tricount.controller.EditTricountController;
import tgpr.tricount.model.Tricount;

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

    private Button btnSave;

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
        createButtonsPanel().addTo(root);

        refresh();



    }

    private Panel createFieldsGrid() {
        var panel = Panel.gridPanel(2, Margin.of(1));

        new Label("Title:").addTo(panel);
        txtTitle = new TextBox().sizeTo(15).addTo(panel)
                .setTextChangeListener((txt, byUser) -> validate());
        panel.addEmpty();
        errTitle.addTo(panel)
                .setForegroundColor(TextColor.ANSI.RED);

        new Label("Description:").addTo(panel);
        txtDescription = new TextBox().sizeTo(30).addTo(panel)
                .setTextChangeListener((txt, byUser) -> validate());
        errDescription.addTo(panel).setForegroundColor(TextColor.ANSI.RED);

        new Label("Subscribers:").addTo(panel);
        listeBox = new ActionListBox();
        for (var participant : tricount.getParticipants()) {
            var libelle = participant.toString();
            if (si le participant est dans une dépense ou est le createur)
                libelle += " (.)"
            listeBox.addItem(libelle, () -> {
                System.out.println(participant);
            });

        }
        listeBox.addTo(panel);
    }


    private static int nbParticipants();

    private void validate() {
        var errors = controller.validate(
                txtTitle.getText(),
                txtDescription.getText(),
                selectUser.getText()
        );

        errTitle.setText(errors.getFirstErrorMessage(Tricount.));
        errDescription.setText(errors.getFirstErrorMessage(Member.Fields.Profile));

        btnAddUpdate.setEnabled(errors.isEmpty());
    }

}
