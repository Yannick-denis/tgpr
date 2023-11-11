
// AddTemplateView.java
package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.model.Template;

import java.util.List;
import java.util.regex.Pattern;

public class AddTemplateView extends DialogWindow {

    private final AddTemplateController addTemplateController;
    private final TextBox txtTitle;

    public AddTemplateView(AddTemplateController addTemplateController, Template template) {
        super((addTemplateController.getTemplate() == null) ? "Create a new Template" : "Change Template title");
        this.addTemplateController = addTemplateController;
        setHints(List.of( Hint.CENTERED, Hint.FIXED_SIZE));
        setFixedSize(new TerminalSize(17, 5));


        Panel root = new Panel();
        setComponent(root);

      new EmptySpace().addTo(root);
// si le buttons est Edit title alors
       Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
               .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);

        Panel inputPanel = new Panel()
                .setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_CENTER)
                .addTo(root);


        inputPanel.addComponent(new Label("Title"));
        txtTitle = new TextBox().addTo(inputPanel).setValidationPattern(Pattern.compile(".*"));
        txtTitle.setText(template == null || template.getTitle() == null ? "" : template.getTitle());
        new EmptySpace().addTo(root);

        Panel buttonPanel = new Panel()
                .setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                .setLayoutData(Layouts.LINEAR_CENTER)
                .addTo(root);

        Button btnSave = new Button((template == null || template.getTitle() == null ) ? "Create" : "Save", addTemplateController::onSave).addTo(buttonPanel);
        Button btnCancel = new Button("Cancel", addTemplateController::onCancel).addTo(buttonPanel);
    }

   /* public void refresh() {
        txtTitle.setText(addTemplateController.getTemplate().getTitle());
        setTitle((addTemplateController.getTemplate().getId() == 0 ) ? "Create a new Template" : "Change Template title");
    }
    */

    public TextBox getTxtTitle() {
        return txtTitle;
    }
}

