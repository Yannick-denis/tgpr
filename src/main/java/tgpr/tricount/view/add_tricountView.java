package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import tgpr.tricount.controller.add_tricountController;

import java.util.List;

import static sun.security.util.KeyUtil.validate;


public class add_tricountView extends DialogWindow {

    public add_tricountView(String title){
        super(title);
    }

    public static void main(String[] args) {
        // Créer une fenêtre principale
        MultiWindowTextGUI gui = new MultiWindowTextGUI((TextGUIThreadFactory) new DefaultWindowManager(), (Screen) new EmptySpace(TextColor.ANSI.BLACK));

        // Créer une fenêtre de saisie
        BasicWindow window = new BasicWindow("Saisie de Titre et Description");
        Panel contentPanel = new Panel();
        contentPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        // Champ de saisie pour le titre
        Label labelTitre = new Label("Titre:");
        TextBox textBoxTitre = new TextBox().addTo(contentPanel);

        // Champ de saisie pour la description
        Label labelDescription = new Label("Description:");
        TextBox textBoxDescription = new TextBox().addTo(contentPanel);

        // Bouton "Create"
        Button createButton = new Button("Create", new Runnable() {
            @Override
            public void run() {
                String titre = textBoxTitre.getText();
                String description = textBoxDescription.getText();
                Object MessageBox;

            }
        }).addTo(contentPanel);

        window.setComponent(contentPanel);
        gui.addWindowAndWait(window);
    }
}






    //code visuel du addtricount

//
//            new EmptySpace().addTo(root);
//
//    // crée un bouton pour l'ajout d'un membre et lui associe une fonction lambda qui sera appelée
//    // quand on clique sur le bouton
//    var btnAddTricount = new Button("Add Tricount", () -> {
//        System.out.println("Add Tricount");
//    }).addTo(root);
//
//
//    var btnAddTricount = new Button("Add Tricount", () -> {
//        System.out.println("Add Tricount");
//        Tricount t = controller.addTricount();
//        if (t != null)
//            reloadData();
//    }).addTo(root);


//    private void validate() {
//        var errors = controller.validate(
//                txtBody.getText()
//        );
//        errBody.setText(errors.getFirstErrorMessage(Message.Fields.Body));
//        btnPost.setEnabled(errors.isEmpty());
//    }
//
//    private void post() {
//        controller.post(txtBody.getText(), chkPrivate.isChecked());
//        close();
//    }
