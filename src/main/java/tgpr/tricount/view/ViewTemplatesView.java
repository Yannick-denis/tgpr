package tgpr.tricount.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Layouts;
import tgpr.framework.ObjectTable;
import tgpr.tricount.controller.ViewTemplatesController;
import tgpr.tricount.controller.ViewTricoutController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Tricount;

import java.util.List;

public class ViewTemplatesView extends DialogWindow {
    private final ViewTemplatesController controller;

    private final Button btnPost = new Button("Post");
    private final Label errBody = new Label("");
    private final CheckBox chkPrivate = new CheckBox();
    private ObjectTable<Operation> operationsTable;
    private Tricount triC;

    public ViewTemplatesView(ViewTemplatesController controller) {
        super("Tricount Repartition Templates");
        this.controller=controller;
        setHints(List.of(Hint.CENTERED, Hint.MODAL));
        setCloseWindowWithEscape(true);
        Panel root = Panel.verticalPanel();
        setComponent(root);
        createTemplatesList();
        createButtons().addTo(root);

    }

    private Panel createTemplatesList() {
        Panel panel = new Panel().setLayoutManager(new GridLayout(4).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER);
        new Label("Templates           ").addTo(panel).addStyle(SGR.UNDERLINE);
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


        return panel;
    }

}
