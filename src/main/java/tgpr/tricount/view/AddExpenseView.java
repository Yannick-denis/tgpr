package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import tgpr.framework.Error;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddExpenseController;
import tgpr.tricount.model.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


//cette vue servira pour  addopereation et edit operation avec deux constructeur diffferent
//si vous voullez utuliser addoperation conctructeur avec tricount
//pour edit operation conctructeur avec tricount + operation

public class AddExpenseView extends DialogWindow {
    private AddExpenseController controler;
    private Operation operation;
    private TextBox txtTitle =new TextBox(" ");
    private TextBox txtAmount=new TextBox("");
    private TextBox Date;
    private ComboBox<String> payBy;
    private CheckBoxList<Repartition> check ;
    private List<User> participant;
    private  List<Repartition> rep =new ArrayList<>();
    private Label errDate =new Label("");
    private Label  errcheched =new Label("");
    private Label errTitle =new Label("");
    private Label errAmount =new Label("");
    private Button btnSave;
    private Button btnDelete;


    public AddExpenseView(AddExpenseController controler) {
        super("Add new expense");
        this.controler = controler;


        setHints(List.of(Hint.CENTERED));

        Panel root = new Panel();
        setComponent(root);
        //creation du panel
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
        //ajout de label au panel et de leur box pour recuillir les donne
        panel.addComponent(new Label("Title:"));
        Title().addTo(panel);

        panel.addComponent(new Label("Amount:"));
        Amount().addTo(panel);
        panel.addComponent(new Label("Date:"));
        Date().addTo(panel);
        panel.addComponent(new Label("Pay By:"));
        payBy = new ComboBox<>();
        loadParticipand();

        for (User elem : participant) {
            payBy.addItem(elem.getFullName());
            System.out.println(elem.getFullName());

        }
        panel.addComponent(payBy);
        panel.addComponent(new Label("use a repartition \n template (optional) "));

        applyAndComb().addTo(panel);
        loadSub();
        panel.addEmpty();
        panel.addEmpty();
        panel.addComponent(new Label("for Whom :\n (wheight <-/-> or -/+)"));
        check = new CheckBoxList<Repartition>();

        for ( Repartition elme : rep) {
            check.addItem(elme,true);
        }
        check.addListener((index,checked) -> {check.getSelectedItem().setWeight(checked?1:0  );
        validate();
        });




        this.addKeyboardListener(check,keyStroke -> {
            var character = keyStroke.getCharacter();
            var type = keyStroke.getKeyType();
            if (type==KeyType.ArrowRight||character!=null&& character=='+'){
                check.getSelectedItem().setWeight(check.getSelectedItem().getWeight()+1);
                if (check.getSelectedItem().getWeight()==1){
                    check.setChecked(check.getSelectedItem(),true);
                    validate();
                }
            }else if ((type== KeyType.ArrowLeft||character!=null &&character=='-')&&check.getSelectedItem().getWeight()>0){
                check.getSelectedItem().setWeight(check.getSelectedItem().getWeight()-1);
                if (check.getSelectedItem().getWeight()==0){
                    check.setChecked(check.getSelectedItem(),false);
                    validate();
                }
            }

            return  true;
        });

        check.addTo(panel);
        panel.addEmpty();
        errcheched.addTo(panel).setForegroundColor(TextColor.ANSI.RED);



        //  new EmptySpace().addTo(root);

        boutondelet().addTo(panel);
        butons().addTo(panel);


    }

    private Panel boutondelet() {
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setBottomMarginSize(0).setRightMarginSize(0))
                .setLayoutData(Layouts.LINEAR_CENTER);
       panel.addEmpty(new TerminalSize(10,1));
        btnDelete=new Button("delete",()->{
            delete();
        }).setVisible(false).addTo(panel);
        return  panel;
    }

    private void delete() {
        controler.delet(operation);

    }

    public AddExpenseView(AddExpenseController controler,Operation operation){
        this(controler);
        setTitle("Edit Expense");
        this.operation=operation;
        txtTitle.setText(operation.getTitle());
        txtAmount.setText(String.valueOf( operation.getAmount()));
        Date.setText(operation.getOperationDate().asString());
        rep=null;
        rep=new ArrayList<>();
        for (User elem :participant){
            if (Repartition.getByKey(operation.getId(),elem.getId())==null){
                rep.add(new Repartition(operation.getId(),elem.getId(),0));

            }else{
            rep.add(Repartition.getByKey(operation.getId(),elem.getId()));
            }
        }
         check.clearItems();
        for ( Repartition elme : rep) {
            if (elme.getWeight()==0){
                check.addItem(elme,false);
            }else {
                check.addItem(elme, true);
            }
        }
        check.addListener((index,checked)->{ check.getSelectedItem().setWeight(checked?1:0);
            validate();

        } );




        this.addKeyboardListener(check,keyStroke -> {
            var character = keyStroke.getCharacter();
            var type = keyStroke.getKeyType();
            if (type==KeyType.ArrowRight||character!=null&& character=='+'){
                check.getSelectedItem().setWeight(check.getSelectedItem().getWeight()+1);
                if (check.getSelectedItem().getWeight()==1){
                    check.setChecked(check.getSelectedItem(),true);
                    validate();
                }
            }else if ((type== KeyType.ArrowLeft||character!=null &&character=='-')&&check.getSelectedItem().getWeight()>0){
                check.getSelectedItem().setWeight(check.getSelectedItem().getWeight()-1);
                if (check.getSelectedItem().getWeight()==0){
                    check.setChecked(check.getSelectedItem(),false);
                    validate();
                }
            }

            return  true;
        });


        btnDelete.setVisible(true);
        btnSave.setEnabled(false);



    }
    public void loadParticipand() {
        participant = controler.getTricount().getParticipants();
    }

    private void loadSub(){

            for (int i=0;i<participant.size();i++) {
                rep.add(new Repartition(0,participant.get(i).getId(),1));
            }
    }

   private Panel Date(){
       Panel panel = new Panel().setLayoutManager(new GridLayout(1).setBottomMarginSize(0).setLeftMarginSize(0))
               .setLayoutData(Layouts.LINEAR_CENTER);
       Date = new TextBox().addTo(panel)
               //validation que c'est une date
               .setValidationPattern(Pattern.compile("[/\\d]{0,10}"))
               .setText(LocalDate.now().asString())
               .setTextChangeListener((txt, byUser)-> validate());

       panel.addEmpty();
       errDate.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
       return panel;

   }
    private Panel Amount() {
        Panel panel = new Panel().setLayoutManager(new GridLayout(1).setBottomMarginSize(0).setLeftMarginSize(0))
                .setLayoutData(Layouts.LINEAR_CENTER);
        txtAmount = new TextBox().addTo(panel)
                .setValidationPattern(Pattern.compile("[.\\d]{0,10}"))
                .setTextChangeListener((txt, byUser)-> validate());
        errAmount.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        return  panel;
    }

    private Panel Title(){
        Panel panel = new Panel().setLayoutManager(new GridLayout(1).setLeftMarginSize(0).setBottomMarginSize(0))
                .setLayoutData(Layouts.LINEAR_CENTER);
        txtTitle = new TextBox().addTo(panel)
                .setPreferredSize(new TerminalSize(40,1))
                .setTextChangeListener((txt, byUser)-> validate());
        errTitle.addTo(panel).setForegroundColor(TextColor.ANSI.RED);
        return panel;
    }

    private Panel applyAndComb(){
        Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_CENTER);
        ComboBox<String> selectTampletate = new ComboBox<>();
        selectTampletate.addItem("No ,I use a custuom repartition ");
        for (Template elem:Template.getByTricount(controler.getTricount().getId())){
            selectTampletate.addItem(elem.getTitle());
        }
        panel.addComponent(selectTampletate);
        //bouton aply template
        var btnAply = new Button("Apply", () -> {
            //save();
        }).addTo(panel);

        return panel;
    }
    private Panel butons(){
        Panel panel = new Panel().setLayoutManager(new GridLayout(3).setTopMarginSize(0).setVerticalSpacing(1).setLeftMarginSize(0))
                .setLayoutData(Layouts.LINEAR_CENTER);

         btnSave = new Button("Save", () -> {
            save();
        }).addTo(panel);
         btnSave.setEnabled(false);

        Button btnSaveTemp = new Button("Save a repartition as a template", () -> {
            // save repartition comme template
        }).addTo(panel);
        Button btnCancel = new Button("Cancel", () -> {
            close();
        }).addTo(panel);


        return panel;
    }
     //permet de savoir si le titre et le montant sont rempli tout les deux
    private boolean toutComplet() {
        return !(txtAmount.getText().isEmpty()||txtTitle.getText().isEmpty()||errDate.getText().equals("pas de date dans le future"));
    }


    //transforme  le string de la textbox en LocalDate
    // si le champ date est vidé nous metrons la date du jour en DB
    private LocalDate StringToDate() {
        LocalDate  dat ;
        if (Date.getText().isEmpty()){
            dat=  LocalDate.now();
        }else {
        DateTimeFormatter format= new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toFormatter();
          dat = LocalDate.parse(Date.getText(),format);
        }
        return dat;
    }

    //appel la methode save du controleur qui a besoin d'une instance de la classs operation pour l'ecrir en DB
    //probleme avec amount et date
    //pay by fonctionne
    private void save() {
        controler.save(txtTitle.getText(), controler.getIdTricount()
                , Double.parseDouble(txtAmount.getText()), StringToDate()
                , User.getByFullName(payBy.getSelectedItem()).getId(),
                LocalDateTime.now(),operation);
        operation=Operation.getByTitle(txtTitle.getText());
        for (int i=0;i<rep.size();i++){
            rep.get(i).setOperationId(operation.getId());
            if (rep.get(i).getWeight()==0&&rep.get(i).getOperationId()==0){
                rep.remove(i);
                i--;
            }
        }
        controler.save(rep);
    }
    private  void validate(){
        double amount;
        String title;
        if (txtAmount.getText().isEmpty()){
            amount=0;
        }else{
            amount=Double.parseDouble(txtAmount.getText());
        }
        if (txtTitle.getText().isEmpty()){
            title=" ";
        }else {
            title=txtTitle.getText();
        }
        var errors = controler.validateDate(Date.getText(),
                                          title  ,amount,check.getCheckedItems());
        errDate.setText(errors.getFirstErrorMessage(Operation.Fields.CreatedAt));
        errAmount.setText(errors.getFirstErrorMessage(Operation.Fields.Amount));
        errTitle.setText(errors.getFirstErrorMessage(Operation.Fields.Title));
        errcheched.setText(errors.getFirstErrorMessage(Operation.Fields.Repartition));
        btnSave.setEnabled(toutComplet()&&errors.isEmpty());
    }


}


