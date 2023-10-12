package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddExpenseController;
import tgpr.tricount.controller.TestController;

import java.util.List;
import java.util.regex.Pattern;

public class AddExpenseView extends BasicWindow {
    private AddExpenseController controler;


    public AddExpenseView(AddExpenseController controler) {
        this.controler = controler;

        setTitle("Add New expense");
        setHints(List.of(Hint.CENTERED));

        Panel root = new Panel();
        setComponent(root);

        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        panel.addComponent(new Label("Titlte:"));
        TextBox txtTitle = new TextBox().addTo(panel);
        panel.addComponent(new Label("Amount:"));
        TextBox txtAmount = new TextBox().addTo(panel);
        panel.addComponent(new Label("Date:"));
        TextBox Date = new TextBox().addTo(panel)
                .setValidationPattern(Pattern.compile("[/\\d]{0,10}"));
        panel.addComponent(new Label("Pay By:"));
        ComboBox<String> cklRole=new ComboBox<>();
        cklRole.addItem("cc");
        panel.addComponent(cklRole);
        panel.addComponent(new Label("use a repartition \n template (optional) "));
        var btnAply = new Button("Apply", () -> {
            System.out.println("apply");
        }).addTo(root);
        ComboBox<String> selectTampletate=new ComboBox<>();
        selectTampletate.addItem("No ,I use a custuom repartition ");
        panel.addComponent(selectTampletate);
        panel.addComponent(new Label("for Whom :\n (wheight <-/-> or -/+)"));
        var btnSave = new Button("Save", () -> {

        }).addTo(root);
        var btnSaveTemp = new Button("Save a repartition as a template", () -> {

        }).addTo(root);
        var btnCancel = new Button("Cancel", () -> {

        }).addTo(root);


    }


}
