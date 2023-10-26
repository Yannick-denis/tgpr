package tgpr.tricount.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.ColumnSpec;
import tgpr.framework.Margin;
import tgpr.framework.ObjectTable;
import tgpr.tricount.model.Operation;

import java.util.List;

public class view_tricountView extends DialogWindow {
    private final Button btnPost = new Button("Post");
    private final Label errBody = new Label("");
    private final CheckBox chkPrivate = new CheckBox();
    private ObjectTable<Operation> operationsTable;





    public view_tricountView(String viewTricountDetails) {
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


        new Label("Titre :").addTo(panel); // doit renvoyer le titre
        panel.addEmpty();
        new Label ("Description :").addTo(panel); // doit renvoyer la description
        panel.addEmpty();
        new Label("Create by :").addTo(panel); // doit renvoyer le user qui a creer le tricount
        panel.addEmpty();
        new Label("Date :").addTo(panel); // renvoi la date de creation
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
        new Button("Balance", this::close).addTo(panel);
        new Button("New expense", this::close).addTo(panel);
        new Button("Edit tricount", this::close).addTo(panel);// doit renvoyer vers edit tricount
        new Button("Close", this::close).addTo(panel);


        return panel;
    }


}
