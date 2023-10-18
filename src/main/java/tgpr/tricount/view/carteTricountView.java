package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;

import static tgpr.framework.extensions.com.googlecode.lanterna.gui2.Window.WindowExtension.addShortcut;

public class carteTricountView extends BasicWindow {


    private Border carteTricount() {
        Panel panel = Panel.verticalPanel(LinearLayout.Alignment.Center) ;
        Button btnSeedData = new Button("Ouvrir");
        panel.addComponent();

        Border border = panel.withBorder(Borders.singleLine());
        return border;

    }
}
