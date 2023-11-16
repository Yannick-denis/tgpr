// AddTemplateView.java
package tgpr.tricount.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddExpenseController;
import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.model.Template;

import java.util.List;

public class AddTemplateView extends DialogWindow {

    private AddTemplateController addTemplateController;
    private TextBox txtTitle;
    private Label errTitle = new Label("");

    public Button getBtnSave() {
        return btnSave;
    }

    private Button btnSave = new Button("");

    public AddTemplateView(AddTemplateController addTemplateController, AddExpenseController addExpenseController) {
        super((addTemplateController.getTemplate() == null || addTemplateController.getTemplate().getTitle() == null) ? "Create a new Template Change" : "Template title");
        this.addTemplateController = addTemplateController;
        if (addTemplateController.getTemplate().getTitle() != null) {
            setTitle("Template title");
        }
        setHints(List.of(Hint.CENTERED));
        Panel root = new Panel();
        setComponent(root);

        new EmptySpace().addTo(root);

        Panel inputPanel = new Panel()
                .setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER)
                .addTo(root);

        inputPanel.addComponent(new Label("Title"));
        txtTitle = new TextBox().addTo(inputPanel).setTextChangeListener((txt, txtTitle) ->
                validateForEdit()
        ).sizeTo(15);
        if (addTemplateController.getTemplate().getTitle() != null) {
            txtTitle.setText(addTemplateController.getTemplate().getTitle());
        }
        new EmptySpace().addTo(root);
        errTitle.addTo(inputPanel).setForegroundColor(TextColor.ANSI.RED);
        ;
        if (getTemplate().getTitle() != null) {
            System.out.println("cc");
            txtTitle.setText(getTemplateTitle());
        }


        new EmptySpace().addTo(root);

        Panel buttonPanel = new Panel()
                .setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER)
                .addTo(root);

        btnSave = new Button(getSaveButtonText(), () -> {
            addTemplateController.onSave(getTemplate(), addTemplateController.getRepartitions());
            validateForEdit();

        }).addTo(buttonPanel);
        addShortcut(btnSave, KeyStroke.fromString("<A-s>"));

        Button btnCancel = new Button("Cancel", addTemplateController::onCancel).addTo(buttonPanel);
        addShortcut(btnCancel, KeyStroke.fromString("<A-c>"));
    }

    private Template getTemplate() {
        return addTemplateController.getTemplate();
    }

    private String getTemplateTitle() {

        if (addTemplateController.getTemplate().getTitle() != null) {
            return addTemplateController.getTemplate().getTitle();
        }
        return (getTemplate().getTitle() != null) ? "" : getTemplate().getTitle();
     }



    private String getSaveButtonText() {
        if (getTemplate().getTitle() == null){
            return "Create";
        }

        return (getTemplate().getTitle() != null ) ? "Save" : "Create";
    }

        private void validateForEdit() {
            var errors = addTemplateController.validateForEdit(
                    txtTitle.getText()

            );
            errTitle.setText(errors.getFirstErrorMessage(Template.Fields.Title));
        }


    public String getTemTitel() {
        if (!txtTitle.getText().isEmpty()) {
            return txtTitle.getText();
        }
       return " ";
    }
}

