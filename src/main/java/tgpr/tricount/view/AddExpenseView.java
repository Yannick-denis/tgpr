package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyType;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddExpenseController;
import tgpr.tricount.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



public class AddExpenseView extends DialogWindow {
    private AddExpenseController controler;
    private Operation operation;
    private TextBox txtTitle =new TextBox(" ");
    private TextBox txtAmount=new TextBox("");
    private TextBox Date;
    private ComboBox<String> payBy;
    private CheckBoxList<Repartition> check = new CheckBoxList<Repartition>();;
    private List<User> participant;
    private  List<Repartition> rep =new ArrayList<>();
    private Label errDate =new Label("");
    private Label errTitle =new Label("");
    private Label errAmount =new Label("");
    private Button btnSave;
    private Button btnApply;


    public void loadParticipand() {
        participant = controler.getTricount().getParticipants();
    }

    private void loadSub(){

            for (int i=0;i<participant.size();i++) {
                rep.add(new Repartition(0,participant.get(i).getId(),1));
            }
    }
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

        for ( Repartition elme : rep) {
            check.addItem(elme,true);
        }
        check.addListener((index,checked)-> check.getSelectedItem().setWeight(checked?1:0) );



        this.addKeyboardListener(check,keyStroke -> {
            var character = keyStroke.getCharacter();
            var type = keyStroke.getKeyType();
            if (type==KeyType.ArrowRight||character!=null&& character=='+'){
                check.getSelectedItem().setWeight(check.getSelectedItem().getWeight()+1);
                if (check.getSelectedItem().getWeight()==1){
                    check.setChecked(check.getSelectedItem(),true);
                }
            }else if ((type== KeyType.ArrowLeft||character!=null &&character=='-')&&check.getSelectedItem().getWeight()>0){
                check.getSelectedItem().setWeight(check.getSelectedItem().getWeight()-1);
                if (check.getSelectedItem().getWeight()==0){
                    check.setChecked(check.getSelectedItem(),false);
                }
            }
            return  true;
        });

        check.addTo(panel);



      //  new EmptySpace().addTo(root);

        new EmptySpace().addTo(panel);
        butons().addTo(panel);


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
        ComboBox<String> selectTemplate = new ComboBox<>();
        selectTemplate.addItem("No ,I use a custom repartition ");
        for (Template elem:Template.getByTricount(controler.getTricount().getId())){
            selectTemplate.addItem(elem.getTitle());
        }
        panel.addComponent(selectTemplate);

        //bouton apply template (Par Margaux)
        btnApply = new Button("Apply", () -> {
            apply(Template.getByKey(selectTemplate.getSelectedIndex()));
            refresh();
        }).addTo(panel);

        return panel;
    }
    private Panel butons(){
        Panel panel = new Panel().setLayoutManager(new GridLayout(3).setTopMarginSize(1).setVerticalSpacing(1))
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
                LocalDateTime.now());
        operation=Operation.getByTitle(txtTitle.getText());
        for (int i=0;i<rep.size();i++){
            rep.get(i).setOperationId(operation.getId());
            if (rep.get(i).getWeight()==0){
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
                                          title  ,amount);
        errDate.setText(errors.getFirstErrorMessage(Operation.Fields.CreatedAt));
        errAmount.setText(errors.getFirstErrorMessage(Operation.Fields.Amount));
        errTitle.setText(errors.getFirstErrorMessage(Operation.Fields.Title));

        btnSave.setEnabled(toutComplet()&&errors.isEmpty());
    }

    private void apply(Template template){
        List<TemplateItem> ti = template.getTemplateItems();
        for (TemplateItem templateItem: ti) {
            int idx = atIndex(templateItem);
            rep.get(idx).setWeight(templateItem.getWeight());
        }
    }

    private int atIndex(TemplateItem ti){
        int cpt =0;
        for (Repartition elem: rep) {
            if(!isIn(elem, ti.getTemplateId())){
                elem.setWeight(0);
            }
            if(elem.getUserId() == ti.getUserId()){
                return cpt;
            }
            ++cpt;
        }
        return cpt;
    }

    private boolean isIn(Repartition rep, int id){
        Template te = Template.getByKey(id);
        List<TemplateItem> ti = te.getTemplateItems();
        for (TemplateItem elem: ti) {
            if(elem.getUserId() == rep.getUserId())
                return true;
        }
        return false;
    }

    private void refresh(){
        check.clearItems();
        for (Repartition elem: rep) {
            check.addItem(elem, elem.getWeight()==0?false:true);
        }
        check.addListener((index,checked)-> check.getSelectedItem().setWeight(checked?1:0) );
    }
}


