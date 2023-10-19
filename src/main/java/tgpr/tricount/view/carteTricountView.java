package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.awt.Label;

import static tgpr.framework.extensions.com.googlecode.lanterna.gui2.Window.WindowExtension.addShortcut;

public class carteTricountView extends BasicWindow {

    private final TextBox titreTricount = new TextBox();
    private final TextBox description = new TextBox();
    private final TextBox createur = new TextBox();
    private Border carteTricount() {
        Panel panel = Panel.verticalPanel(LinearLayout.Alignment.Center) ;
        Label titre = new Label("User:");
        titreTricount.addTo(panel).takeFocus();

        Button btnSeedData = new Button("Ouvrir");
        panel.addComponent(btnSeedData);

        Border border = panel.withBorder(Borders.singleLine());
        return border;

    }
}
