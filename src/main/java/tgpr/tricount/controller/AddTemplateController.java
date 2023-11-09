package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Template;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.AddTemplateView;

public class AddTemplateController extends Controller {
    private AddTemplateView addTemplateView= new AddTemplateView(this, new Template("Benoit ne paye rien", 2));
    private Template template;
    public AddTemplateController() {
    }


    public void add(String title) {
        Template template1 =new Template(title, 0);
        template.save().setTitle(title);
    }

    public void update(String title, int id){
        Template exist = Template.getByKey(id);
        if (exist != null){
            exist.setTitle(title);
            exist.save().setTitle(title);

        }

    }
    @Override
    public Window getView() {
        return new AddTemplateView(this, new Template("Benoit ne paye rien", 2 ));
    }
}
