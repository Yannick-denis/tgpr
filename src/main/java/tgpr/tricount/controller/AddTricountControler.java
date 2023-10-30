package tgpr.tricount.controller;

import tgpr.framework.Controller;
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
        tricount= new Tricount(title,description,me.getId());
        tricount.save();


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

//public class add_tricountController extends Controller {
//    @Override
//    public Window getView() {
//        return (Window) new add_tricountView("Create a new Tricount");
//
//
//
//    }
//    public Tricount addTricount(String title, String description, int creatorId){
//       return new Tricount();
////           public Tricount(String title, String description, int creatorId) {
////            this.title = title;
////            this.description = description;
////            this.createdAt = LocalDateTime.now();
////            this.creatorId = creatorId;
//        }
//    }
//
//    // creer les liens entre les infos entree dans le add_tricountView et la bases de données
//    // ajouter le tricount dans la BD
//
//
//
//
//
//
////    private String title;
////    private String description;
////
////
////    //code controller du add_tricount
////
////    public add_tricountController(String Title,String Description){
////        setTitle(title);
////        setDescription(description);
////
////    }
////
////    public String getTitle() {
////        return title;
////    }
////
////    public void setTitle(String title) {
////        this.title = title;
////    }
////
////    public String getDescription() {
////        return description;
////    }
////
////    public void setDescription(String description) {
////        description = description;
////    }
//
//
////    public Tricount addTricount() {
////        var controller = new addtricountController();
////        navigateTo(controller);
////        return controller.getTricount();
////    }
//
