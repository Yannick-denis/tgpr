package tgpr.tricount.view;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.tricount.controller.profileController;
import java.awt.*;
import java.util.List;
public class profileView extends DialogWindow  {
    private final profileController controller;
    public profileView(profileController controller, String title) {
        super(title);
        this.controller = controller;
        setHints(List.of(Hint.CENTERED));
        setCloseWindowWithEscape(true);

        Panel root = Panel.verticalPanel();
        setComponent(root);

        Panel panel = new Panel().addTo(root);
        Label label = new Label("zzz").addTo(panel);


    }
}



