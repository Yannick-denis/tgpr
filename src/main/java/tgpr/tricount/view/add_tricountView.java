package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import tgpr.framework.Margin;
import tgpr.tricount.controller.add_tricountController;

import java.util.List;

import static sun.security.util.KeyUtil.validate;


public class add_tricountView extends DialogWindow {

    private final TextBox txtBody = new TextBox("", TextBox.Style.MULTI_LINE);
    private final CheckBox chkPrivate = new CheckBox();
    private final Button btnPost = new Button("Post");
    private final Label errBody = new Label("");

    public add_tricountView(String title) {
        super(title);


        setHints(List.of(Hint.CENTERED, Hint.MODAL));
        setCloseWindowWithEscape(true);

        Panel root = Panel.verticalPanel();
        setComponent(root);

        createFields().addTo(root);
        createButtons().addTo(root);

        txtBody.takeFocus();
    }


    private Panel createFields() {
        Panel panel = Panel.gridPanel(2, Margin.of(1));
        //Panel panel2 = Panel.gridPanel(2, Margin.of(3));


        // body
        new Label("Titre").addTo(panel);
        txtBody.sizeTo(30, 1).addTo(panel);
        panel.addEmpty();
        errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);
      //  txtBody.setTextChangeListener((txt, byUser) -> validate());
        new Label ("Description").addTo(panel);
        txtBody.sizeTo(30, 5).addTo(panel);
        panel.addEmpty();
        errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);
        // private
        return panel;
    }

    private Panel createButtons() {
        var panel = Panel.horizontalPanel().center();

        //btnPost.setEnabled(false).addTo(panel).addListener(button -> post());
         new Button("Create", this::close).addTo(panel);
        new Button("Cancel", this::close).addTo(panel);


        return panel;
    }



//    private final TextBox txtBody = new TextBox("testets");
//    private final CheckBox chkPrivate = new CheckBox();
//    private final Label errBody = new Label("");

//
//        var root = new Panel().setLayoutManager(
//                new LinearLayout(Direction.VERTICAL)
//        );Label label = new Label("Hello World!");
//        Panel panel = new Panel();
//        panel.addComponent(new Label("Hello"));
//        new Label("World").addTo(panel);
//
//
//    }
//    private Panel createFields() {
//        Panel panel = Panel.gridPanel(2, Margin.of(1));
//
//        // body
//        new Label("Body:").addTo(panel);
//        txtBody.sizeTo(30, 5).addTo(panel);
//        panel.addEmpty();
//        errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);
//       // txtBody.setTextChangeListener((txt, byUser) -> validate());
//
//        // private
//        new Label("Is Private:").addTo(panel);
//        chkPrivate.addTo(panel);
//
//        return panel;
//    }
//    TextBox txt1 = new TextBox();
//
//




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
