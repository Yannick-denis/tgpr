package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.Button;
import tgpr.tricount.model.Tricount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class add_tricountView extends Tricount {


        public static void main(String[] args) {
            JFrame fenetre = new JFrame("Saisie de Titre et Description");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setSize(400, 200);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));

            JLabel labelTitre = new JLabel("Titre:");
            JTextField champTitre = new JTextField(20);
            JLabel labelDescription = new JLabel("Description:");
            JTextField champDescription = new JTextField(20);

            panel.add(labelTitre);
            panel.add(champTitre);
            panel.add(labelDescription);
            panel.add(champDescription);

            JButton boutonCreate = new JButton("Create");
            boutonCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String titre = champTitre.getText();
                    String description = champDescription.getText();
                    JOptionPane.showMessageDialog(fenetre, "Titre : " + titre + "\nDescription : " + description);
                }
            });

            fenetre.add(panel, BorderLayout.CENTER);
            fenetre.add(boutonCreate, BorderLayout.SOUTH);

            fenetre.setVisible(true);
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


