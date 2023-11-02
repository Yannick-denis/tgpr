package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;
import tgpr.tricount.view.ViewTemplatesView;
import tgpr.tricount.view.ViewTricountView;

public class ViewTemplatesController extends Controller {


    public User getMe() {
        return me;
    }

    private User me;
    public ViewTemplatesController(Tricount tricount, User userConected){
        this.tricount=tricount;
        this.me=userConected;
       // view =new ViewTemplatesView(this);

    }
    private ViewTricountView view ;

    public Tricount getTricount() {
        return tricount;
    }

    private Tricount tricount ;
    @Override
    public Window getView() {
        return view;
    }
}

