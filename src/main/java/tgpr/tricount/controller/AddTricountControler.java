package tgpr.tricount.controller;

import tgpr.framework.Controller;
import tgpr.framework.Model;
import tgpr.tricount.model.Subscription;
import tgpr.tricount.model.Tricount;


import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Error;
import tgpr.framework.ErrorList;
import tgpr.tricount.model.User;
import tgpr.tricount.view.AddTricountView;


public class AddTricountControler extends Controller {

    private AddTricountView view=new AddTricountView(this);
    private Tricount tricount;
    private User me;
    public AddTricountControler(User userConected){

        this.me=userConected;
    }

    @Override
    public Window getView() {
        return view;
    }

    public void save(String title,String description) {
        if (description.isEmpty()){
            description=null;
        }
        Model.clearCache();
        tricount= new Tricount(title,description,me.getId());
        tricount.save();
        Subscription sub=new Subscription(Tricount.getByTitleAndUser(title,me).getId(),me.getId());
        sub.save();
        Model.clearCache();
        Controller.navigateTo(new TricountListController());

    }

    public ErrorList validate(String text) {
        var errors =new ErrorList();
        if (text.length()<3){
            errors.add(new Error("minimum 3 char",Tricount.Fields.Title));
        }
        if (Tricount.getByTitleAndUser(text,me)!=null){
            errors.add("this title already exists",Tricount.Fields.Title);
        }
        return errors;
    }
}

