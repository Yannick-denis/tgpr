package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.tricount.controller.EditProfileController;

import java.util.List;

public class EditProfileView extends DialogWindow {
    private final EditProfileController controller;
    public EditProfileView(EditProfileController controller, String title) {
        super(title);
        this.controller = controller;

        setHints(List.of(Hint.CENTERED));
        setCloseWindowWithEscape(true);

        Panel root = Panel.verticalPanel();
        setComponent(root);

        Panel panel = new Panel().addTo(root);
    }
}
