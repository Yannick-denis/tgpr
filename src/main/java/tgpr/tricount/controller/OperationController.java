package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.framework.Controller;
import tgpr.tricount.model.Operation;
import tgpr.tricount.view.ViewOperation;

import java.util.List;

public class OperationController extends Controller {
    private ViewOperation viewOperation;
    private Operation operation;
    private List<Operation> operationList;
    public OperationController(Operation operation, List<Operation> operations) {
        this.operation = operation;
        viewOperation = new ViewOperation(this);

    }

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
    public List<Operation> getOperationList() {
        return operationList;
    }
    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }
}
