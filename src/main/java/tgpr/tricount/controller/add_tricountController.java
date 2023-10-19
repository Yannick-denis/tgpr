package tgpr.tricount.controller;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import tgpr.framework.Controller;
import tgpr.tricount.view.add_tricountView;

public class add_tricountController extends Controller {
    @Override
    public Window getView() {
        return (Window) new add_tricountView("Create a new Tricount");

    }

    // creer les liens entre les infos entree dans le add_tricountView et la bases de données
    // ajouter le tricount dans la BD


}



//    private String title;
//    private String description;
//
//
//    //code controller du add_tricount
//
//    public add_tricountController(String Title,String Description){
//        setTitle(title);
//        setDescription(description);
//
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        description = description;
//    }


//    public Tricount addTricount() {
//        var controller = new addtricountController();
//        navigateTo(controller);
//        return controller.getTricount();
//    }

