package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Template;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.AddTemplateView;

public class AddTemplateController extends Controller {
    private AddTemplateView addTemplateView= new AddTemplateView(this, new Template("Benoit ne paye rien", 2));


    private Tricount tricount;


    public void save(String title){
        tricount = new Tricount(title,tricount.getId());
    }
    @Override
    public Window getView() {
        return new AddTemplateView(this, new Template("Benoit ne paye rien", 1));
    }
}
