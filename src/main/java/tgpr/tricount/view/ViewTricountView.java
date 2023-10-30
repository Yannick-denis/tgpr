package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Margin;
import tgpr.framework.ObjectTable;
import tgpr.tricount.model.Operation;

import java.util.List;

public class ViewTricountView extends DialogWindow {
    private final Button btnPost = new Button("Post");
    private final Label errBody = new Label("");
    private final CheckBox chkPrivate = new CheckBox();
    private ObjectTable<Operation> operationsTable;





    public ViewTricountView(String viewTricountDetails) {
        super(viewTricountDetails);

        setHints(List.of(Hint.CENTERED, Hint.MODAL));
        setCloseWindowWithEscape(true);

        Panel root = Panel.verticalPanel();
        setComponent(root);

        createFields().addTo(root);
        createOperationsPanel().addTo(root);
        createButtons().addTo(root);


    }

    private Panel createFields() {
        Panel panel = Panel.gridPanel(2, Margin.of(1));


        new Label("Titre :").addTo(panel); // GET TITLE
        panel.addEmpty();
        new Label ("Description :").addTo(panel); // GET DESCRIPTION
        panel.addEmpty();
        new Label("Create by :").addTo(panel); // GET CREATOR ID
        panel.addEmpty();
        new Label("Date :").addTo(panel); // getCreatedAt
        panel.addEmpty();
        new Label("Total Expenses :").addTo(panel);// total des montants des dépsense de ce tricount
        panel.addEmpty();
        new Label("My Expenses :").addTo(panel);// total des dépenses de l'utilisateur courant
        panel.addEmpty();
        new Label("My Balances:").addTo(panel);// total de montants payes moins le total des dépenses que l'user courant a faite renvois ce qu'il doit paye ou ce qu'il doit recevoir
        panel.addEmpty();



       // errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);




      //  errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);

        return panel;
    }
    private Panel createOperationsPanel() {

        Panel panel = Panel.gridPanel(2, Margin.of(1));

        new Label("operations:").addTo(panel);
        panel.addEmpty();

        // DOIT RECUPERER LA TABLE DES OPERATIONS DE CE TRICOUNT POUR L'AFFICHER

//        var panel  = new Panel().fill();
//        Border border = panel.withBorder(Borders.singleLine(" Operation"));

      //  operationsTable = new ObjectTable<>(
//               new ColumnSpec<>("Operation",
//                new ColumnSpec<>("Amount",
//                new ColumnSpec<>("Paid by",
//                       .setOverflowHandling(ColumnSpec.OverflowHandling.Wrap),
//                new ColumnSpec<>("Date")).addTo(panel);
// code du DIsplayMemberView du TUTO

        return panel;
    }


    private Panel createButtons() {
        var panel = Panel.horizontalPanel().center();

        //btnPost.setEnabled(false).addTo(panel).addListener(button -> post());
        new Button("Balance", this::view_balance).addTo(panel);//renvoi vers view_balance
        new Button("New expense", this::add_operation).addTo(panel); //renvoi vers add_operation
        new Button("Edit tricount", this::edit_tricount).addTo(panel);// doit renvoyer vers edit_tricount
        new Button("Close", this::close).addTo(panel);


        return panel;
    }
    private void view_balance(){
        //renverra vers la classe view balance.controller

    }
    private void add_operation(){
        //renverra vers la classe view add_operation.controller

    }
    private void edit_tricount(){
        //renverra vers la classe view edit_tricount.controller

    }


}
