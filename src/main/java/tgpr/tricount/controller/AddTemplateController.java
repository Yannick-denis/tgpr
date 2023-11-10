
// AddTemplateController.java
package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Template;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.AddTemplateView;

public class AddTemplateController extends Controller {
    private final AddTemplateView addTemplateView;
    private Template template;

    private Tricount tricount;

    public AddTemplateController(Template template, Tricount tricount) {

        this.template = template;
        this.tricount = tricount;
        addTemplateView = new AddTemplateView(this);
    }

    public Template getTemplate() {
        return template;
    }

    public void onSave() {
        if(template == null) {
          template = new Template("", tricount.getId());
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