package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddTemplateController;

import java.util.List;

public class AddTemplateView extends BasicWindow {
    private AddTemplateController addTemplateController;
    // private ViewTemplate viewTemplate;
    private final TextBox txtTitle;
   public  AddTemplateView(AddTemplateController addTemplateController){
      super("Create a new Template");
      this.addTemplateController = addTemplateController;
       Panel root = new Panel();
       setComponent(root);

       Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
               .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
       panel.addComponent(new Label("Title"));
       txtTitle = new TextBox().addTo(panel);

       new EmptySpace().addTo(root);

       Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
               .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
       Button btnCreate = new Button("Create", () -> {
           String enteredTitle = txtTitle.getText();
           saveTitle(enteredTitle);
       }).addTo(buttons);
           Button btnCancel = new Button("Cancel").addTo(buttons);

       /*
       //Devrais permettre d'aller à la page precedente à tester quand j'aurais les pages.
       Button btnCancel = new Button("Cancel", () ->{
                previousPage.setVisible(true); // Afficher la page précédente
                setVisible(false);
        }).addTo(buttons);

        */


   }
   // Méthode saveTitle sauvegardera le titre dans le template
   private void saveTitle(String title){

   }
}
