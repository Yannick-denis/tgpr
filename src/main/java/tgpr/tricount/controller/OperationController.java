package tgpr.tricount.controller;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Operation;
import tgpr.tricount.view.ViewOperation;

import java.util.List;

public class OperationController extends Controller {
    private ViewOperation viewOperation;
    private Operation operation;

    public OperationController(Operation operation) {
        this.operation = operation;
        this.viewOperation = new ViewOperation(this);


        // Vous pouvez ajouter d'autres initialisations si nécessaire
        //viewOperation.getClose().addListener(this::handleCloseButtonClick);
        //viewOperation.getEdit().addListener(this::handleEditButtonClick);
       // viewOperation.getUp().addListener(this::handleUpButtonClick);
       // viewOperation.getDown().addListener(this::handleDownButtonClick);


    }


    public void handleCloseButtonClick(Button button) {
        if (button == viewOperation.getClose()){
            viewOperation.close();
        }
    }

  /*  public void handleEditButtonClick(Button button) {
        if (button == viewOperation.getEdit()){
            viewOperation.getEdit();
        }
    }

   */




    @Override
    public Window getView() {
        return getViewOperation();
    }

    public ViewOperation getViewOperation() {
        return viewOperation;
    }

    public Operation getOperation() {
        return operation;
    }


}
