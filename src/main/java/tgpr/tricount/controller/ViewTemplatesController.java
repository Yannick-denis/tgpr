package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.TemplateItem;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;
import tgpr.tricount.view.ViewTemplatesView;

import java.util.List;


public class ViewTemplatesController extends Controller {


    public User getMe() {
        return me;
    }

    private User me = Security.getLoggedUser();
    public ViewTemplatesController(Tricount tricount, User userConected){
        this.tricount=tricount;
        this.me=userConected;
        view =new ViewTemplatesView(this);

    }
    public ViewTemplatesController(Tricount tricount){
        this.tricount=tricount;
        view =new ViewTemplatesView(this);

    }
    private ViewTemplatesView view ;

    public Tricount getTricount() {
        return tricount;
    }

    private Tricount tricount ;
    @Override
    public Window getView() {
        return view;
    }

    public void save(List<TemplateItem> rep) {
        for (TemplateItem elem :rep){
            if (elem.getWeight()==0){
                rep.remove(elem);
            }
            elem.save();
        }

    }
}

