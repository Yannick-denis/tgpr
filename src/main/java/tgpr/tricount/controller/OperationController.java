package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Operation;
import tgpr.tricount.model.Repartition;
import tgpr.tricount.view.TestView;
import tgpr.tricount.view.ViewOperation;

import java.util.List;

public class OperationController extends Controller {
    private final ViewOperation viewOperation = new ViewOperation(this);
    private List<ViewOperation.Expense> expenses;
    private int index = 0 ;

    public OperationController() {

        // Vous pouvez ajouter d'autres initialisations si nécessaire
        viewOperation.getClose().addListener(this::handleCloseButtonClick);
        viewOperation.getEdit().addListener(this::handleEditButtonClick);
        viewOperation.getUp().addListener(this::handleUpButtonClick);
        viewOperation.getDown().addListener(this::handleDownButtonClick);


    }

    private void moveUp() {
        while (expenses.size() -1 < index) {
            index--;
            //showExpense(expenses.get(index));
            viewOperation.getDown().setEnabled(true);
        }
        if (index == expenses.size() -1) {
            viewOperation.getUp().setEnabled(false);
        }
    }
    private void moveDown() {
        while (index < expenses.size() - 1) {
            index++;
            //showExpense(expenses.get(index));
            viewOperation.getUp().setEnabled(true);
        }
        if (index == expenses.size() - 1) {
            viewOperation.getDown().setEnabled(false);
            viewOperation.getUp();
        }


    }

    private void handleCloseButtonClick(Button button) {
        // Logique à exécuter lorsque le bouton "Close" est cliqué
        // Par exemple, fermer la fenêtre ou effectuer d'autres actions
        if (button == viewOperation.getClose()){
            viewOperation.close();
        }
    }

    private void handleEditButtonClick(Button button) {
        // Logique à exécuter lorsque le bouton "Edit" est cliqué
        // Par exemple, ouvrir une vue d'édition
        if (button == viewOperation.getEdit()){
            viewOperation.getEdit();
        }
    }

    private void handleUpButtonClick(Button button) {
        // Logique à exécuter lorsque le bouton "Up" est cliqué
        // Par exemple, passer à l'élément précédent de la liste d'expenses
        if (button == viewOperation.getUp()){
            moveUp();
        }
    }

    private void handleDownButtonClick(Button button) {
        // Logique à exécuter lorsque le bouton "Down" est cliqué
        // Par exemple, passer à l'élément suivant de la liste d'expenses
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
