// AddTemplateView.java
package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddExpenseController;
import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.model.Template;

import java.util.List;
import java.util.regex.Pattern;

public class AddTemplateView extends DialogWindow {

    private final AddTemplateController addTemplateController;
    private final TextBox txtTitle;

    public AddTemplateView(AddTemplateController addTemplateController, AddExpenseController addExpenseController) {
        super(( addTemplateController.getTemplate() == null || addTemplateController.getTemplate().getTitle() == null) ? "Create a new Template Change" : "Template title");
        this.addTemplateController = addTemplateController;

        setHints(List.of(Hint.CENTERED, Hint.FIXED_SIZE));
        setFixedSize(new TerminalSize(25, 5));
        Panel root = new Panel();
        setComponent(root);

        new EmptySpace().addTo(root);

        Panel inputPanel = new Panel()
                .setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_CENTER)
                .addTo(root);

        inputPanel.addComponent(new Label("Title"));
        txtTitle = new TextBox().addTo(inputPanel).setValidationPattern(Pattern.compile(".*")).sizeTo(10);
        txtTitle.setText(getTemplateTitle());

        new EmptySpace().addTo(root);

        Panel buttonPanel = new Panel()
                .setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER)
                .addTo(root);

        Button btnSave = new Button(getSaveButtonText(), () -> addTemplateController.onSave(getTemplate(), addTemplateController.getRepartitions()))
                .addTo(buttonPanel);
        addShortcut(btnSave, KeyStroke.fromString("<A-s>"));

        Button btnCancel = new Button("Cancel", addTemplateController::onCancel).addTo(buttonPanel);
        addShortcut(btnCancel, KeyStroke.fromString("<A-c>"));
    }

    private Template getTemplate() {
        return addTemplateController.getTemplate();
    }

    private String getTemplateTitle() {
        return (getTemplate().getTitle() == null) ? "" : getTemplate().getTitle();
    }

    private String getSaveButtonText() {
        return (getTemplate().getTitle() == null ) ? "Create" : "Save";
    }

    @Override
    public Interactable getFocusedInteractable() {
        return super.getFocusedInteractable();
    }

    public TextBox getTxtTitle() {
        return txtTitle;
    }
}
