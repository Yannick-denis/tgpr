package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.Button;

public class add_tricountView {

    //code visuel du addtricount


            new EmptySpace().addTo(root);

    // crée un bouton pour l'ajout d'un membre et lui associe une fonction lambda qui sera appelée
    // quand on clique sur le bouton
    var btnAddTricount = new Button("Add Tricount", () -> {
        System.out.println("Add Tricount");
    }).addTo(root);


    var btnAddTricount = new Button("Add Tricount", () -> {
        System.out.println("Add Tricount");
        Tricount t = controller.addTricount();
        if (t != null)
            reloadData();
    }).addTo(root);

}
