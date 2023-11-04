package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Repartition;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.TestView;
import tgpr.tricount.view.ViewOperation;

import java.util.List;

public class OperationController extends Controller {
    private final ViewOperation viewOperation = new ViewOperation(this);
    private Tricount tricount;
    private int index = 0 ;

    public OperationController() {

        // Vous pouvez ajouter d'autres initialisations si nécessaire
        viewOperation.getClose().addListener(this::handleCloseButtonClick);
        viewOperation.getEdit().addListener(this::handleEditButtonClick);
        viewOperation.getUp().addListener(this::handleUpButtonClick);
        viewOperation.getDown().addListener(this::handleDownButtonClick);


    }


    public void moveUp() {
        if (index == tricount.getId()){
            viewOperation.getUp().setEnabled(false);
        }
    }
    public void moveDown() {
        if (index == tricount.getId()) {
            viewOperation.getDown().setEnabled(false);
        }
    }

    public void handleCloseButtonClick(Button button) {
        if (button == viewOperation.getClose()){
            viewOperation.close();
        }
    }

    public void handleEditButtonClick(Button button) {
        if (button == viewOperation.getEdit()){
            viewOperation.getEdit();
        }
    }

    public void handleUpButtonClick(Button button) {
        if (button == viewOperation.getUp()){
            moveUp();
        }
    }

    public void handleDownButtonClick(Button button) {
        if (button == viewOperation.getDown()){
            moveDown();
        }

    }

    @Override
    public Window getView() {
        return viewOperation;
    }

    public ViewOperation getViewOperation() {
        return viewOperation;
    }
}
