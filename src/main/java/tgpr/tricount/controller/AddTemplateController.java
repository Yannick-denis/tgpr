
// AddTemplateController.java
package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.*;
import tgpr.tricount.view.AddExpenseView;
import tgpr.tricount.view.AddTemplateView;

import java.util.ArrayList;
import java.util.List;

public class AddTemplateController extends Controller {

   // private AddTemplateView addTemplateView= new AddTemplateView(this, new Template("", 4));
    private List<Repartition> rep  = new ArrayList<>();
    private  AddTemplateView addTemplateView;
    private Template template;
    private TemplateItem templateItem;
    private AddExpenseView addExpenseView;
    private Tricount tricount;

    public AddTemplateController(Template template, Tricount tricount, List<Repartition> rep) {
        this.tricount = tricount;
        this.template = new Template(null, tricount.getId());
        addTemplateView = new AddTemplateView(this, template);
        this.rep = rep;
    }
    public void add(String title) {
        Template template1 = new Template(title, 0);
        template.setTitle(title);
        template.save();
    }

    public Template getTemplate() {
            return template;
        }
    public List<Repartition> getRepartitions() {
        return rep;
    }
    public void saveTempleItem(List<Repartition> repartitions, TemplateItem item) {
        for (Repartition repartition : repartitions) {
            TemplateItem templateItem = new TemplateItem(item.getUserId(), item.getTemplateId(), item.getWeight());
            templateItem.save();
        }
    }
    public void onSave() {
        if(template == null) {
          template = new Template("", tricount.getId());;
        }
        String enteredTitle = addTemplateView.getTxtTitle().getText();
        template.setTitle(enteredTitle);
        template.save();
        addTemplateView.close();
    }



    public void onCancel() {
        addTemplateView.close();
    }

    @Override
    public Window getView() {
        return addTemplateView;
    }
}