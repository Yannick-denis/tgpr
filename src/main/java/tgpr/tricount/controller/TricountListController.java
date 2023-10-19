package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.TricountListView;
import tgpr.framework.Controller;

import java.util.List;
public class TricountListController extends Controller {
    private List<Tricount> tricounts;

    @Override
    public Window getView(){
        return new TricountListView(this);
    }
    public List<Tricount> getTricounts(){
        return Tricount.getPaginated(1, 12);
    }

}
