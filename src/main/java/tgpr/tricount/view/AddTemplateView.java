package tgpr.tricount.view;

import com.googlecode.lanterna.gui2.*;
import tgpr.framework.Controller;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.model.Template;
import tgpr.tricount.model.Tricount;

public class AddTemplateView extends BasicWindow {
    private AddTemplateController addTemplateController;
    // private ViewTemplate viewTemplate;
    private Template template;
    private final TextBox txtTitle;
    Controller controller;
   public  AddTemplateView(AddTemplateController addTemplateController, Template template){
      super(template == null ? "Create a new Template" : "Changes Template title");
      this.addTemplateController = addTemplateController;
       this.template = template;
       Panel root = new Panel();
       setComponent(root);

       Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
               .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
       panel.addComponent(new Label("Title"));
       txtTitle = new TextBox().addTo(panel);

       new EmptySpace().addTo(root);
// si le buttons est Edit title alors
           Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
                   .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
           Button btnCreate = new Button(template == null ?"Create" : "save", () -> {
               String enteredTitle = txtTitle.getText();
               controller.navigateTo(new AddTemplateController());

           }).addTo(buttons);

       //Devrais permettre d'aller à la page precedente à tester quand j'aurais les pages.
       Button btnCancel = new Button("Cancel", this::close).addTo(buttons);




   }

}
