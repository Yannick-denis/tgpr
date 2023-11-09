package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Template;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.AddTemplateView;

public class AddTemplateController extends Controller {
    private AddTemplateView addTemplateView;
    private Template template ;
    private Tricount tricount;
    public AddTemplateController(Tricount tricount) {
        this.tricount=tricount;
        this.template= new Template(null, tricount.getId());
        addTemplateView= new AddTemplateView(this,template);
    }


    public void add(String title) {
        template.setTitle(title);
        template.save();
        addTemplateView.close();
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
        return addTemplateView;
    }
}
