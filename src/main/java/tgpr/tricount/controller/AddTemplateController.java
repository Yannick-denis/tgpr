
// AddTemplateController.java
package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.framework.ErrorList;
import tgpr.tricount.model.*;
import tgpr.tricount.view.AddExpenseView;
import tgpr.tricount.view.AddTemplateView;

import java.util.ArrayList;
import java.util.List;

public class AddTemplateController extends Controller {
    private List<Repartition> rep = new ArrayList<>();
    private AddTemplateView addTemplateView;
    private Template template;
    private TemplateItem templateItem;
    private AddExpenseView addExpenseView;
    private AddExpenseController addExpenseController;
    private Tricount tricount;
    private User user;

    public AddTemplateController(Template template, Tricount tricount, List<Repartition> rep) {
        this.tricount = tricount;
        this.template = template;
        this.template.setId(template.getId());
        addTemplateView = new AddTemplateView(this, addExpenseController);
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

    public void saveTempleItem(int idTemplate, List<Repartition> repartitions) {
        if (repartitions == null) {
            List<User> users = tricount.getParticipants();
            for (User user : users) {
                TemplateItem templateItem1 = new TemplateItem(user.getId(), idTemplate, 1);
                templateItem1.save();
            }
        } else {
            for (Repartition repartition : repartitions) {
                TemplateItem templateItem = new TemplateItem(repartition.getUserId(), idTemplate, repartition.getWeight());
                templateItem.save();
            }
        }
    }

    public void onSave(Template template, List<Repartition> repartitions) {
        if (template == null) {
            template = new Template("", tricount.getId());
            System.out.println("lol");
        }
        System.out.println("toi la bas");
        String enteredTitle = addTemplateView.getTxtTitle().getText();
        template.setTitle(enteredTitle);
        template.save();
        template = Template.getByTitle(tricount.getId(), enteredTitle);
        saveTempleItem(template.getId(), repartitions);
        addTemplateView.close();
    }
    public ErrorList validateForEdit(String title){
        var error = new ErrorList();
        if (title.length() < 3){
            error.add("Minimum 3 chars", Template.Fields.Title);
        }
        return error;
    }

    public void onCancel() {
        addTemplateView.close();
    }

    @Override
    public Window getView() {
        return addTemplateView;
    }
}