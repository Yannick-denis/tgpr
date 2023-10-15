package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Controller;
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
import java.util.Locale;
import java.util.regex.Pattern;

import static java.time.LocalDate.parse;


// petit souci le tricount du contoleur rete null
//et repartition pour enregistre le poids en DB
public class AddExpenseView extends DialogWindow {
    private AddExpenseController controler;
    private Operation operation;
    private TextBox txtTitle;
    private TextBox txtAmount;
    private TextBox Date;
    private ComboBox<String> payBy;
    private List<User> participant;

    public void loadParticipand() {
        participant = controler.getTricount().getParticipants();
    }


    public AddExpenseView(AddExpenseController controler) {
        super("Add new expense");
        this.controler = controler;



        setHints(List.of(Hint.CENTERED));

        Panel root = new Panel();
        setComponent(root);
        //creation du panel
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        //ajout de label au panel et de leur box pour recuillir les donne
        panel.addComponent(new Label("Titlte:"));
        txtTitle = new TextBox().addTo(panel);
        panel.addComponent(new Label("Amount:"));
        txtAmount = new TextBox().addTo(panel);
        panel.addComponent(new Label("Date:"));
        Date = new TextBox().addTo(panel)
                //validation que c'est une date
                .setValidationPattern(Pattern.compile("[/\\d]{0,10}"));
        panel.addComponent(new Label("Pay By:"));
        payBy = new ComboBox<>();
        loadParticipand();

        for (User elem : participant) {
            payBy.addItem(elem.getFullName());
            System.out.println(elem.getFullName());

        }
        panel.addComponent(payBy);

        panel.addComponent(new Label("use a repartition \n template (optional) "));
        //ajout de bouton

        ComboBox<String> selectTampletate = new ComboBox<>();
        selectTampletate.addItem("No ,I use a custuom repartition ");
        panel.addComponent(selectTampletate);
        var btnAply = new Button("Apply", () -> {
            //logique du bouton
        }).addTo(root);
        panel.addComponent(new Label("for Whom :\n (wheight <-/-> or -/+)"));
        CheckBoxList<String> check = new CheckBoxList<>();
        for (User elme : participant) {
            check.addItem(elme.getFullName());
        }
        check.addTo(root);

        new EmptySpace().addTo(root);
        Button btnSave = new Button("Save", () -> {
            save();
        }).addTo(root);

        Button btnSaveTemp = new Button("Save a repartition as a template", () -> {
            // Controller.navigateTo(new TemplateController);
        }).addTo(root);
        Button btnCancel = new Button("Cancel", () -> {
            close();
        }).addTo(root);


    }



    //bricolege pour comprende la class localdate pour instancier apartir d'un string qui vient de la text box destiner a disparaitre
    private LocalDate verif() {
        String date = Date.getText();

        LocalDate dat = LocalDate.now();
        if (date.isValidDate()) {
            dat = parse(date);
        }
        return dat;
    }

    //appel la methode save du controleur qui a besoin d'une instance de la classs operation pour l'ecrir en DB
    //probleme avec amount et date
    //pay by fonctionne
    private void save() {
        controler.save(txtTitle.getText(), controler.getIdTricount()
                , txtAmount.getLineCount(), verif()
                , User.getByFullName(payBy.getSelectedItem()).getId(),
                LocalDateTime.now());
    }


}


