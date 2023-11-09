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
import tgpr.tricount.controller.BalanceController;
import tgpr.tricount.controller.TricountListController;
import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;

import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.tricount.model.Security;


import java.util.List;

public class TricountListView extends BasicWindow {

    private final TricountListController controller;
    private final Menu menuFile;



    public TricountListView(TricountListController controller) {
        this.controller = controller;

        setTitle("fils de pute");
        setHints(List.of(Hint.EXPANDED));

        Panel root = new Panel();
        setComponent(root);
        MenuBar menuBar = new MenuBar().addTo(root);
        menuFile = new Menu("File");
        menuBar.add(menuFile);
        MenuItem menuLogout = new MenuItem("Logout!", controller::logout);
        menuFile.add(menuLogout);
        MenuItem balance = new MenuItem("Balance", controller::balance);
        menuFile.add(balance);
        MenuItem ChPsw = new MenuItem("Change Password", controller::chpsw); // à bouger dans profileview
        menuFile.add(ChPsw);                                                    // à bouger dans profileview

    }


}
