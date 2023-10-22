package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import tgpr.framework.*;
import tgpr.tricount.controller.TricountListController;
import tgpr.tricount.model.Tricount;


import java.util.List;

public class TricountListView extends BasicWindow {

    private final TricountListController controller;
//    private final ObjectTable<Tricount> table;
    private final Panel pnlBody;
    private final Panel pnlEnTete;
    private final Panel pnlBasDePage;
    private final TextBox filter;
    private final Button createTricount;




    public TricountListView(TricountListController controller) {
        this.controller = controller;

        setTitle("pas de titre");
        setHints(List.of(Hint.EXPANDED));

        Panel root = new Panel();
        setComponent(root);


        Component MenuBar = new MenuBar();
        pnlEnTete = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_BEGIN).addTo(root);
        pnlEnTete.addComponent(new Label("filter:"));
        filter = new TextBox().addTo(pnlEnTete).sizeTo(20);
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
        pnlBasDePage = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_BEGIN).addTo(root);
        createTricount = new Button("Create a new Tricount").addTo(root);
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
            new Label("Created By "+tricount.getCreator().getFullName()).center().addTo(p);
            if (tricount.getParticipants().isEmpty()){
                new Label("with no friends ").center().addTo(p);
            }else {
                int nbrParticipant = tricount.getParticipants().size();
                new Label("with "+ nbrParticipant + "friends ").center().addTo(p);
            }
            new Button("Open").center().addTo(p);
            pnlBody.addComponent(p.withBorder(Borders.singleLine()));
        }
    }


}
