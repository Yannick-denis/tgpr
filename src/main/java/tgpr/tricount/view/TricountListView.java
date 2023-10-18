package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.table.Table;
import tgpr.framework.ColumnSpec;
import tgpr.framework.ObjectTable;
import tgpr.framework.Tools;
import tgpr.framework.ViewManager;
import tgpr.tricount.controller.TricountListController;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;


import java.util.List;

public class TricountListView extends BasicWindow {

    private final TricountListController controller;
    private final ObjectTable<Tricount> table;

    public TricountListView(TricountListController controller) {
        this.controller = controller;

        setTitle("fils de pute");
        setHints(List.of(Hint.EXPANDED));

        Panel root = new Panel();
        setComponent(root);

        new EmptySpace().addTo(root);
        table = new ObjectTable<>(new ColumnSpec<>("Tricount",Tricount.getAll()));
        root.addComponent(table);
        table.setPreferredSize(new TerminalSize(ViewManager.getTerminalColumns(),15));

    }


}
