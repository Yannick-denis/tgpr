package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import tgpr.framework.ColumnSpec;
import tgpr.framework.ObjectTable;
import tgpr.framework.Spacing;
import tgpr.framework.ViewManager;
import tgpr.tricount.controller.TricountListController;
import tgpr.tricount.model.Tricount;


import java.util.List;

public class TricountListView extends BasicWindow {

    private final TricountListController controller;
//    private final ObjectTable<Tricount> table;
    private final Panel pnlBody;

    public TricountListView(TricountListController controller) {
        this.controller = controller;

        setTitle("pas de titre");
        setHints(List.of(Hint.EXPANDED));

        Panel root = new Panel();
        setComponent(root);



        pnlBody = Panel.gridPanel(3, Spacing.of(1) ).addTo(root);


//        root.withBorder(pnlBody.withBorder(Borders.singleLine()));


//        new EmptySpace().addTo(root);
//        table = new ObjectTable<>(
//                new ColumnSpec<>("titre",Tricount::getTitle),
//                new ColumnSpec<>("descr",Tricount::getDescription)
//        );
//        root.addComponent(table);



//        table.setPreferredSize(new TerminalSize(ViewManager.getTerminalColumns(),15));
        reloadData();
    }

    public void reloadData() {
//        table.clear();
        var tricounts = controller.getTricounts();
//        table.add(tricount);

        for (int i=0; i<Math.min(12, tricounts.size()); ++i) {
            var tricount = tricounts.get(i);
            Panel p = Panel.verticalPanel();
            new Label(tricount.getTitle()).center().addTo(p);
            new Label(tricount.getDescription() == null ? "No description" : tricount.getDescription()).center().addTo(p);
            new Label(tricount.getCreator().getFullName()).center().addTo(p);
            pnlBody.addComponent(p.withBorder(Borders.singleLine()));
        }
    }


}
