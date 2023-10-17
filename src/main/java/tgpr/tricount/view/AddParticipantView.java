package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Controller;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddParticipantController;

import java.util.List;

public class AddParticipantView extends DialogWindow {
    private final AddParticipantController controller;
    public AddParticipantView(AddParticipantController controller){
        super("ADDDDDDDD");
        this.controller = controller;
        setHints(List.of(Hint.CENTERED));
        Panel root = new Panel();
        setComponent(root);

        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1)).setLayoutData(Layouts.LINEAR_CENTER).addTo(root);

        Button bout = new Button("Addddd", () -> {
            Controller.navigateTo(controller);
        }).addTo(panel);

    }
}
