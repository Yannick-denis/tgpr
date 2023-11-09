package tgpr.tricount.controller;
import com.googlecode.lanterna.gui2.Window;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.view.TricountListView;
import tgpr.framework.Controller;
import tgpr.framework.SortOrder;
import java.util.List;
public class TricountListController extends Controller {
    private List<Tricount> tricounts;
    private final profileController controller = new profileController();
    @Override
    public Window getView(){
        return new TricountListView(this , controller );
    }
    public List<Tricount> getTricounts(String filter){
//        Tricount.getPaginated(1, 12);
//        return Tricount.getAll();
        if (filter.isBlank())
            return Tricount.getAll();
        else
            return Tricount.getFiltered(filter);
        }

}