package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.tricount.controller.ViewTemplatesController;
import tgpr.tricount.controller.ViewTricoutController;

public class ViewTemplatesView extends DialogWindow {
    private final ViewTemplatesController controller;

    public ViewTemplatesView(ViewTemplatesController controller) {
        super("Tricount Repartition Templates");
        this.controller=controller;
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
