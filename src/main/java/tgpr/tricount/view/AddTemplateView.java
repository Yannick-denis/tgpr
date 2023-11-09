package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import tgpr.framework.Controller;
import tgpr.framework.Layouts;
import tgpr.tricount.controller.AddTemplateController;
import tgpr.tricount.model.Template;
import tgpr.tricount.model.Tricount;

import java.util.List;
import java.util.regex.Pattern;

public class AddTemplateView extends BasicWindow {
    private  Tricount tricount;
    private AddTemplateController addTemplateController;
    // private ViewTemplate viewTemplate;
    private Template template;

    private final TextBox txtTitle;
    Controller controller;
   public  AddTemplateView(AddTemplateController addTemplateController, Template template){
      super(template == null ? "Create a new Template" : "Changes Template title");
       setHints(List.of(Hint.EXPANDED));
      this.addTemplateController = addTemplateController;
       setHints(List.of(Hint.CENTERED, Hint.FIXED_SIZE));
       setFixedSize(new TerminalSize(17, 5));
      Panel root = new Panel();
      setComponent(root);

      Panel panel = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
               .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
      panel.addComponent(new Label("Title"));
      txtTitle = new TextBox().addTo(panel).setValidationPattern(Pattern.compile("[a-z][a-z\\d]{0,7}"));

      new EmptySpace().addTo(root);
// si le buttons est Edit title alors
       Panel buttons = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
               .setLayoutData(Layouts.LINEAR_CENTER).addTo(root);
       Button btnCreate = new Button(template == null ?"Create" : "save", () -> {
           String enteredTitle = txtTitle.getText();
           if (template == null){
               addTemplateController.add(enteredTitle);
           }else {
             template.save();
           }
          // controller.navigateTo(new AddTemplateController());
       }).addTo(buttons);

       //Devrais permettre d'aller à la page precedente à tester quand j'aurais les pages.
       Button btnCancel = new Button("Cancel", this::close).addTo(buttons);






   }

}
