package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddExpenseController;
import tgpr.tricount.controller.TestController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;



public class AddExpenseView extends BasicWindow {
    private AddExpenseController controler;
    private  Operation operation;

    public AddExpenseView(AddExpenseController controler) {
        this.controler = controler;

        setTitle("Add New expense");
        setHints(List.of(Hint.CENTERED));

        Panel root = new Panel();
        setComponent(root);
        //creation du panel
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        //ajout de label au panel et de leur box pour recuillir les donne
        panel.addComponent(new Label("Titlte:"));
        TextBox txtTitle = new TextBox().addTo(panel);
        panel.addComponent(new Label("Amount:"));
        TextBox txtAmount = new TextBox().addTo(panel);
        panel.addComponent(new Label("Date:"));
        TextBox Date = new TextBox().addTo(panel)
                //validation que c'est une date
                .setValidationPattern(Pattern.compile("[/\\d]{0,10}"));
        panel.addComponent(new Label("Pay By:"));
        ComboBox<String> payBy=new ComboBox<>();
        payBy.addItem("cc");
        panel.addComponent(payBy);
        panel.addComponent(new Label("use a repartition \n template (optional) "));
        //ajout de bouton
        var btnAply = new Button("Apply", () -> {
            //logique du bouton
        }).addTo(root);
        ComboBox<String> selectTampletate=new ComboBox<>();
        selectTampletate.addItem("No ,I use a custuom repartition ");
        panel.addComponent(selectTampletate);
        panel.addComponent(new Label("for Whom :\n (wheight <-/-> or -/+)"));

        new EmptySpace().addTo(root);
        var btnSave = new Button("Save", () -> {

        }).addTo(root);
        var btnSaveTemp = new Button("Save a repartition as a template", () -> {

        }).addTo(root);
        var btnCancel = new Button("Cancel", () -> {

        }).addTo(root);
        //cration de l'operation avec les donne recuilli pour pouvoir l'ajoute en DB apres
        operation =new Operation(txtTitle, 1,txtAmount, LocalDate.of(),
                                  User.getByFullName(payBy.getSelectedItem()).getId(),
                LocalDateTime.now());


    }


}
