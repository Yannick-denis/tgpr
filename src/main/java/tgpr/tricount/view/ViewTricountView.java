package tgpr.tricount.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import org.w3c.dom.ls.LSOutput;
import tgpr.framework.*;
import tgpr.tricount.controller.*;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;

import java.text.DecimalFormat;
import java.util.List;

public class ViewTricountView extends DialogWindow {
    private final Button btnPost = new Button("Post");
    private final Label errBody = new Label("");
    private final CheckBox chkPrivate = new CheckBox();
    private ObjectTable<Operation> operationsTable;
    private Tricount triC;
    private  Operation operation;
    private List<Operation >list;
    private User me;
   private ViewTricoutController controller;




    public ViewTricountView(ViewTricoutController controller) {
        super("View Details Tricount");
        this.controller=controller;
        setHints(List.of(Hint.CENTERED, Hint.MODAL));
        setCloseWindowWithEscape(true);
        Panel root = Panel.verticalPanel();
        setComponent(root);
        triC=controller.getTricount();
        list=triC.getOperations();
        this.me=controller.getMe();

        createFields(triC).addTo(root);
       tableaudesOperation().addTo(root);
        createButtons().addTo(root);



        setHints(List.of(Hint.CENTERED));
        //remplit la list des operation

        setComponent(root);

    }

    private Panel createFields(Tricount triC) {
        Panel panel = Panel.gridPanel(2, Margin.of(1));

        new Label("Titre :").addTo(panel);
        new Label(triC.getTitle()).addTo(panel).setForegroundColor(new TextColor.RGB(128,128,128));
        new Label ("Description :").addTo(panel);
        new Label(triC.getDescription()==null?"no description":triC.getDescription()).addTo(panel).setForegroundColor(new TextColor.RGB(128,128,128));
        new Label("Create by :").addTo(panel);
        new Label(triC.getCreator().getFullName()).addTo(panel).setForegroundColor(new TextColor.RGB(128,128,128));
        new Label("Date :").addTo(panel);
        new Label(triC.getCreatedAt().asString()).addTo(panel).setForegroundColor(new TextColor.RGB(128,128,128));
        new Label("Total Expenses :").addTo(panel);
        new Label(totalExpense()).addTo(panel).setForegroundColor(new TextColor.RGB(128,128,128));
        new Label("My Expenses :").addTo(panel);
        new Label(myExpense()).addTo(panel).setForegroundColor(new TextColor.RGB(128,128,128));
        new Label("My Balance:").addTo(panel);

        /*
        * dans la liste des opération :
        * par opération if(userConnecté est dans l'ope){
        * addition des poids =X
        * total de l'ope Amount
        * amout/x = part égale (Y)
        * y * le poids du user connecté dans cette opération = prix a payer par ope (PAPPO)
        * somme totale des PAPPO dans l'entièreté du tricount - Myexpense = Mybalances
        *
        *
        *
        * }
        *
        *
        *
        *
        * */
        panel.addEmpty();

        return panel;
    }



    public Panel tableaudesOperation() {
        //panel a 4 colone pour cree le tableau  avec les operation
        /*
        il faut s'arrange pour que le 4 label ajoute dans la foreach objet table
         */


        Panel panel = new Panel().setLayoutManager(new GridLayout(4).setTopMarginSize(1).setVerticalSpacing(0))
                .setLayoutData(Layouts.LINEAR_CENTER);
       /* new Label("Operation           ").addTo(panel).addStyle(SGR.UNDERLINE);
        new Label("      Amount").addTo(panel).addStyle(SGR.UNDERLINE);
        new Label("Pay by    ").addTo(panel).addStyle(SGR.UNDERLINE);
        new Label("Date").addTo(panel).addStyle(SGR.UNDERLINE);
        //comparateur implemente comparable qu'on a vu en pro2 pour dire a la methode sort sur quoi comparer
        //trie avec la date la plus recent en premier
        list.sort(new Comparateur());

        */

/*       for (Operation elem:list){
            new Label(elem.getTitle()).addTo(panel);
            new Label("    "+new DecimalFormat("#.0#").format(elem.getAmount())+"€").addTo(panel);
            new Label(elem.getInitiator().getFullName()).addTo(panel);
            //substrig garde entre les index 0 et 10 pour enlever l'heure
            new Label(elem.getCreatedAt().asString().substring(0,10)).addTo(panel);
        }

 */

       operationsTable = new ObjectTable<>(
               new ColumnSpec<>("Operation           ", Operation::getTitle),
               new ColumnSpec<>("      Amount", Operation::getAmountTostring),
               new ColumnSpec<>("Pay by    ", Operation::getInitiator),
               new ColumnSpec<>("Date", Operation::getOperationDateFormat)

       ).addTo(panel);
        operationsTable.add(triC.getOperations());
        operationsTable.setSelectAction(()->{
            Controller.navigateTo(new OperationController(Operation.getByTitle(operationsTable.getSelected().getTitle()),triC.getOperations()));
            close();
            Controller.navigateTo(new ViewTricoutController(triC,Security.getLoggedUser()));
        });

        return panel;
    }


    private Panel createButtons() {
        var panel = Panel.horizontalPanel().center();

        //btnPost.setEnabled(false).addTo(panel).addListener(button -> post());
        new Button("Balance", this::view_balance).addTo(panel);
        new Button("New expense", this::add_operation).addTo(panel);
        new Button("Edit tricount", this::edit_tricount).addTo(panel);
       Button btnclose= new Button("Close", this::close).addTo(panel);
       addShortcut(btnclose, KeyStroke.fromString("<A-c>"));


        return panel;
    }
    private void view_balance(){
      Controller.navigateTo(new BalanceController(triC));

    }
    private void add_operation(){
//        Controller.navigateTo(new AddOperationController);
        Controller.navigateTo(new AddExpenseController(triC));
        close();
        Controller.navigateTo(new ViewTricoutController(triC, Security.getLoggedUser()));


    }
    private void edit_tricount(){
        Controller.navigateTo(new EditTricountController(triC));
    }

    private String totalExpense(){
        String res;
        double resdouble=0;
        for(Operation elem:list){
            resdouble+= elem.getAmount();
        }
        res =new DecimalFormat("#.0#").format(resdouble);
        return res + "€";
    }

    private String myExpense(){
        String res;
        double resdouble=0;
        for(Operation elem:list){
          if(me.getId()==elem.getInitiatorId()){
              resdouble+= elem.getAmount();
          }
        }
        res =new DecimalFormat("#.0#").format(resdouble);

        return res + "€";
    }

    private String myBalance(List<Operation> list){
        String res;
        double resdouble=0;
        /*
         * dans la liste des opération :
         * par opération if(userConnecté est dans l'ope){
         * addition des poids =X
         * total de l'ope Amount
         * amout/x = part égale (Y)
         * y * le poids du user connecté dans cette opération = prix a payer par ope (PAPPO)
         * somme totale des PAPPO dans l'entièreté du tricount(RES) - Myexpense = Mybalances
         *
         * if (myExpense-RES >=0){
         * vert}
         * else{
         * rouge}
         *
       */
        res =new DecimalFormat("#.0#").format(resdouble);
        return res;
    }


}
