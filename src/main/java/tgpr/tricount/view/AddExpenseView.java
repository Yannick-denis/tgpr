package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import tgpr.framework.Error;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddExpenseController;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.regex.Pattern;




// le poids
//et repartition pour enregistre le poids en DB
//comment cree un keyboard listner
public class AddExpenseView extends DialogWindow {
    private AddExpenseController controler;
    private Operation operation;
    private TextBox txtTitle;
    private TextBox txtAmount;
    private TextBox Date;
    private ComboBox<String> payBy;
    private CheckBoxList<String> check ;
    private List<User> participant;
    private Label errDate =new Label("");

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
        panel.addComponent(new Label("Title:"));
        txtTitle = new TextBox().addTo(panel)
                .setPreferredSize(new TerminalSize(40,1));
        panel.addComponent(new Label("Amount:"));
        txtAmount = new TextBox().addTo(panel);
        panel.addComponent(new Label("Date:"));
        Date = new TextBox().addTo(panel)
                //validation que c'est une date
                .setValidationPattern(Pattern.compile("[/\\d]{0,10}"))
                .setText(LocalDate.now().asString())
                 .setTextChangeListener((txt, byUser)-> validate());
        panel.addEmpty();
        errDate.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        panel.addComponent(new Label("Pay By:"));
        payBy = new ComboBox<>();
        loadParticipand();

        for (User elem : participant) {
            payBy.addItem(elem.getFullName());
            System.out.println(elem.getFullName());

        }
        panel.addComponent(payBy);
        //new EmptySpace().addTo(root);
        panel.addComponent(new Label("use a repartition \n template (optional) "));
        ComboBox<String> selectTampletate = new ComboBox<>();
        selectTampletate.addItem("No ,I use a custuom repartition ");
        panel.addComponent(selectTampletate);
        //bouton aply template
        var btnAply = new Button("Apply", () -> {
            //logique du bouton
        }).addTo(root);
       // new EmptySpace().addTo(root);
        panel.addComponent(new Label("for Whom :\n (wheight <-/-> or -/+)"));
         check = new CheckBoxList<>();
        for (User elme : participant) {
            check.addItem(elme.getFullName() +weight(),true);

        }
         check.addListener((check,ArrowRight)-> keyPressed());
        check.addTo(root);

      //  new EmptySpace().addTo(root);

        new EmptySpace().addTo(root);
        Button btnSave = new Button("Save", () -> {
            save();
        }).addTo(root);

        Button btnSaveTemp = new Button("Save a repartition as a template", () -> {
            // save repartition comme template
        }).addTo(root);
        Button btnCancel = new Button("Cancel", () -> {
            close();
        }).addTo(root);


    }

    private void keyPressed() {
        if (check.isEnabled())
            System.out.println("cc");
    }


    private String weight() {
            return " ("+1+")";
    }



    //transforme  le string de la textbox en LocalDate
    private LocalDate StringToDate() {
        DateTimeFormatter format= new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toFormatter();
        LocalDate  dat = LocalDate.parse(Date.getText(),format);
        return dat;
    }

    //appel la methode save du controleur qui a besoin d'une instance de la classs operation pour l'ecrir en DB
    //probleme avec amount et date
    //pay by fonctionne
    private void save() {
        controler.save(txtTitle.getText(), controler.getIdTricount()
                , Double.parseDouble(txtAmount.getText()), StringToDate()
                , User.getByFullName(payBy.getSelectedItem()).getId(),
                LocalDateTime.now());
    }
    private  void validate(){
        Error errors = controler.validateDate(Date.getText());
        errDate.setText(errors.getMessage());
    }


}


