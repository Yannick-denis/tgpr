package tgpr.tricount.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
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
    //private final Paginator pagination;




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


        pnlBody = Panel.gridPanel(3, Spacing.of(0) ).addTo(root);



//        root.withBorder(pnlBody.withBorder(Borders.singleLine()));


//        new EmptySpace().addTo(root);
//        table = new ObjectTable<>(
//                new ColumnSpec<>("titre",Tricount::getTitle),
//                new ColumnSpec<>("descr",Tricount::getDescription)
//        );
//        root.addComponent(table);



//        table.setPreferredSize(new TerminalSize(ViewManager.getTerminalColumns(),15));
        reloadData();
        filter.takeFocus();
        new EmptySpace().addTo(root);
        pnlBasDePage = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_BEGIN).addTo(root);
        new EmptySpace().addTo(root);
        createTricount = new Button("Create a new Tricount").addTo(root);
//        pagination = new Paginator(this,12,Tricount.getPaginated(1,)).addTo(root);
//        int i = ;
//        pagination.setCount(30);
    }

    public void reloadData() {
//        table.clear();
        var tricounts = controller.getTricounts(filter.getText());
//        table.add(tricount);

        for (int i=0; i<Math.min(12, tricounts.size()); ++i) {
            var tricount = tricounts.get(i);
            Panel p = Panel.verticalPanel();
            new Label(tricount.getTitle()).setForegroundColor(TextColor.ANSI.BLUE).center().addTo(p);
            new Label(tricount.getDescription() == null ? "No description" : tricount.getDescription()).setForegroundColor(TextColor.ANSI.BLACK_BRIGHT).center().addTo(p);
            new Label("Created By "+tricount.getCreator().getFullName()).center().addTo(p);
            int nbrParticipant = tricount.getParticipants().size();
            if ((nbrParticipant-1) == 0){
                new Label("with no friends ").center().addTo(p);
            }else {

                new Label("with "+ (nbrParticipant-1) + "friends ").center().addTo(p);
            }
            new Button("Open").center().addTo(p);
            pnlBody.addComponent(p.withBorder(Borders.singleLine()));
        }
    }


}
