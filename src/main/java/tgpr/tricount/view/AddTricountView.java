package tgpr.tricount.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;

import com.googlecode.lanterna.gui2.*;
import tgpr.framework.Margin;
import tgpr.tricount.controller.AddTricountControler;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Margin;
import tgpr.tricount.controller.AddTricountControler;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;

import java.util.List;
import java.util.List;

import static sun.security.util.KeyUtil.validate;



public class AddTricountView extends DialogWindow {
        private  TextBox txtTitle = new TextBox("", TextBox.Style.MULTI_LINE);
        private TextBox txtDescription;

        private Button btncreate;
        private Label errBody = new Label("");
        private AddTricountControler controler;

        public AddTricountView(AddTricountControler controler) {
            super("Create new Tricount ");

            this.controler=controler;

            setHints(List.of(Window.Hint.CENTERED, Window.Hint.MODAL));
            setCloseWindowWithEscape(true);

            Panel root = Panel.verticalPanel();
            setComponent(root);

            createFields().addTo(root);
            createButtons().addTo(root);

        }
        private Panel createFields() {
            Panel panel = Panel.gridPanel(2, Margin.of(1));
            //Panel panel2 = Panel.gridPanel(2, Margin.of(3));


            // body
            new Label("Titre").addTo(panel);
            txtTitle.addTo(panel)
                    .setPreferredSize(new TerminalSize(20,1))
                    .setTextChangeListener((tkt,byuser)->validate());
            panel.addEmpty();
            errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);
            //  txtBody.setTextChangeListener((txt, byUser) -> validate());
            new Label ("Description").addTo(panel);
            txtDescription=new TextBox().sizeTo(30,5).addTo(panel);

            // private
            return panel;
        }

        private Panel createButtons() {
            var panel = Panel.horizontalPanel().center();

            //btnPost.setEnabled(false).addTo(panel).addListener(button -> post());
            btncreate= new Button ("Create", () ->{
                save();
            }).setEnabled(false).addTo(panel);
            new Button("Cancel", this::close).addTo(panel);


            return panel;
        }

        public void validate(){
            var error= controler.validate(txtTitle.getText());
            errBody.setText(error.getFirstErrorMessage(Tricount.Fields.Title));
            btncreate.setEnabled(error.isEmpty());

        }
/////cccccc


        ///cccc
        private void save() {
            controler.save(txtTitle.getText(),txtDescription.getText());
            close();
        }


    }


//    private final TextBox txtTitre = new TextBox("", TextBox.Style.MULTI_LINE);
//    private final TextBox txtDescription = new TextBox("", TextBox.Style.MULTI_LINE);
//    private final CheckBox chkPrivate = new CheckBox();
//    private final Button btnPost = new Button("Post");
//    private final Label errBody = new Label("");
//
//    public add_tricountView(String title) {
//        super(title);
//
//
//        setHints(List.of(Hint.CENTERED, Hint.MODAL));
//        setCloseWindowWithEscape(true);
//
//        Panel root = Panel.verticalPanel();
//        setComponent(root);
//
//        createFields().addTo(root);
//        createButtons().addTo(root);
//
//        txtTitre.takeFocus();
//    }
//
//
//    private Panel createFields() {
//        Panel panel = Panel.gridPanel(2, Margin.of(1));
//
//
//        new Label("Titre :").addTo(panel);
//        txtTitre.sizeTo(30, 1).addTo(panel);
//        panel.addEmpty();
//        errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);
//        // txtTitre.setTextChangeListener((txt, byUser) -> validate());
//        new Label ("Description :").addTo(panel);
//        txtDescription.sizeTo(30, 5).addTo(panel);
//        panel.addEmpty();
//        errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);
//
//        return panel;
//    }
//
//    private Panel createButtons() {
//        var panel = Panel.horizontalPanel().center();
//
//        //btnPost.setEnabled(false).addTo(panel).addListener(button -> post());
//        new Button("Create", this::create_tricount).addTo(panel); // DOIT CREER LE TRICOUNT AVEC UN TITLE ,IDCREATOR ET DESCRIPTION
//        /*
//        *     public Tricount(String title, String description, int creatorId) {
//        this.title = title;
//        this.description = description;
//        this.createdAt = LocalDateTime.now();
//        this.creatorId = creatorId;
//    }*/
//
//
//
//        new Button("Cancel", this::close).addTo(panel);
//
//
//        return panel;
//    }
//
//    public void create_tricount(){
//        //add_tricountController.navigateTo();
//      //  addtricount du controller;
//        // DOIT CREER LE TRICOUNT AVEC UN TITLE ,IDCREATOR ET DESCRIPTION
//        /*
//        *     public Tricount(String title, String description, int creatorId) {
//        this.title = title;
//        this.description = description;
//        this.createdAt = LocalDateTime.now();
//        this.creatorId = creatorId;
//    }*/
//
//
//    }
////    private void validate() {
////        var errors = controller.validate(
////                txtTitre.getText()
////        );
////        errBody.setText(errors.getFirstErrorMessage(Message.Fields.Body));
////        btnPost.setEnabled(errors.isEmpty());
////    }
//
//
//
////    private final TextBox txtBody = new TextBox("testets");
////    private final CheckBox chkPrivate = new CheckBox();
////    private final Label errBody = new Label("");
//
////
////        var root = new Panel().setLayoutManager(
////                new LinearLayout(Direction.VERTICAL)
////        );Label label = new Label("Hello World!");
////        Panel panel = new Panel();
////        panel.addComponent(new Label("Hello"));
////        new Label("World").addTo(panel);
////
////
////    }
////    private Panel createFields() {
////        Panel panel = Panel.gridPanel(2, Margin.of(1));
////
////        // body
////        new Label("Body:").addTo(panel);
////        txtBody.sizeTo(30, 5).addTo(panel);
////        panel.addEmpty();
////        errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);
////       // txtBody.setTextChangeListener((txt, byUser) -> validate());
////
////        // private
////        new Label("Is Private:").addTo(panel);
////        chkPrivate.addTo(panel);
////
////        return panel;
////    }
////    TextBox txt1 = new TextBox();
////
////
//
//
//
//
//}
//
//
//
//
//
//
//
////code visuel du addtricount
//
////
////            new EmptySpace().addTo(root);
////
////    // crée un bouton pour l'ajout d'un membre et lui associe une fonction lambda qui sera appelée
////    // quand on clique sur le bouton
////    var btnAddTricount = new Button("Add Tricount", () -> {
////        System.out.println("Add Tricount");
////    }).addTo(root);
////
////
////    var btnAddTricount = new Button("Add Tricount", () -> {
////        System.out.println("Add Tricount");
////        Tricount t = controller.addTricount();
////        if (t != null)
////            reloadData();
////    }).addTo(root);
//
//
////    private void validate() {
////        var errors = controller.validate(
////                txtBody.getText()
////        );
////        errBody.setText(errors.getFirstErrorMessage(Message.Fields.Body));
////        btnPost.setEnabled(errors.isEmpty());
////    }
////
////    private void post() {
////        controller.post(txtBody.getText(), chkPrivate.isChecked());
////        close();
////    }
