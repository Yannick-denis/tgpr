package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Controller;
import tgpr.tricount.model.Operation;
import tgpr.tricount.view.ViewOperation;

import java.util.List;
import java.util.function.Function;

public class OperationController extends Controller {
    private ViewOperation viewOperation;
    private Operation operation;

    public OperationController(ViewOperation viewOperation, Operation operation) {
        this.viewOperation = viewOperation;
        this.operation = operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setViewOperation(ViewOperation viewOperation) {
        this.viewOperation = viewOperation;
    }

    @Override
    public Window getView() {
        return null;
    }
    public ViewOperation getViewOperation() {
        return viewOperation;
    }
    public Operation getOperation() {
        return operation;
    }
}
