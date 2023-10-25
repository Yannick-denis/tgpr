package tgpr.tricount.controller;

import com.googlecode.lanterna.gui2.Window;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.TricountListView;
import tgpr.framework.Controller;
import tgpr.framework.SortOrder;

import java.util.List;
public class TricountListController extends Controller {
    private List<Tricount> tricounts;

    @Override
    public Window getView(){
        return new TricountListView(this);
    }
    public List<Tricount> getTricounts(String filter){
        Tricount.getPaginated(1, 12);
//        return Tricount.getFiltered(filter);
        return Tricount.getAll();
    }

}
