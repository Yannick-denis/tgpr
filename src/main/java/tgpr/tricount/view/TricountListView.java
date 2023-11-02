package tgpr.tricount.view;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import tgpr.framework.*;
import tgpr.tricount.controller.TricountListController;
import tgpr.tricount.model.Security;
import tgpr.tricount.model.Tricount;
import java.util.ArrayList;
import java.util.List;
public class TricountListView extends BasicWindow {
    private final TricountListController controller;
    private final List<Tricount> tricountList = Tricount.getAll();
    private final Panel pnlBody;
    private final Panel pnlEnTete;
    private final Panel pnlBasDePage;
    private final Panel pnlProfile;
    private final TextBox filter;
    private final Paginator pagination;
    private final Menu menuFile;
    private final Button createTricount;
    public TricountListView(TricountListController controller) {
        this.controller = controller;
        setTitle(getTitleWithUser());
        setHints(List.of(Hint.EXPANDED));
        Panel root = new Panel().setLayoutManager(new BorderLayout());
        setComponent(root);




        pnlEnTete = new Panel().setLayoutManager(new GridLayout(2).setTopMarginSize(1).setVerticalSpacing(1))
                .setLayoutData(Layouts.LINEAR_BEGIN);
        root.addComponent(pnlEnTete, BorderLayout.Location.TOP);

        MenuBar menuBar = new MenuBar().addTo(pnlEnTete);
        menuFile = new Menu("File");
        menuBar.add(menuFile);
        MenuItem menuprofile = new MenuItem("View Profile" , new profileView("djhdjd"));
        menuFile.add(menuprofile);
        new EmptySpace().addTo(pnlEnTete);
        pnlProfile = Panel.verticalPanel();



        pnlEnTete.addComponent(new Label("filter:"));
        filter = new TextBox().addTo(pnlEnTete).sizeTo(20).setTextChangeListener((txt, filter) -> reloadData());
        filter.addTo(pnlEnTete);
        pnlBody = Panel.gridPanel(3, Spacing.of(0) );
        root.addComponent(pnlBody, BorderLayout.Location.CENTER);

        pnlBasDePage = new Panel().setLayoutManager(new BorderLayout());
        root.addComponent(pnlBasDePage, BorderLayout.Location.BOTTOM);
        createTricount = new Button("Create a new Tricount");
        pnlBasDePage.addComponent(createTricount, BorderLayout.Location.LEFT);
        pagination = new Paginator(this,12,this::pageChanged);
        pnlBasDePage.addComponent(pagination, BorderLayout.Location.RIGHT);
        reloadData();

    }

        public void pageChanged ( int page){
            // Remarque: les pages sont indexées à partir de zéro
            System.out.println("L'utilisateur a demandé la page " + (page + 1));
            // aller en db pour récupérer les tricounts de la page courante (limit)
            // appeler reloadData() pour afficher
            reloadData();
        }
        public void reloadData () {
            var tricounts = controller.getTricounts(filter.getText());
            pagination.setCount(tricounts.size());
            pnlBody.removeAllComponents();
            int start = pagination.getStart();
            int end = Math.min(start + 12, tricounts.size());
            for (int i = start; i < end; ++i) {
                var tricount = tricounts.get(i);
                Panel p = Panel.verticalPanel();
                new Label(tricount.getTitle()).setForegroundColor(TextColor.ANSI.BLUE).center().addTo(p);
                new Label(tricount.getDescription() == null ? "No description" : tricount.getDescription()).setForegroundColor(TextColor.ANSI.BLACK_BRIGHT).center().addTo(p);
                new Label("Created By " + tricount.getCreator().getFullName()).center().addTo(p);
                int nbrParticipant = tricount.getParticipants().size();
                if ((nbrParticipant - 1) == 0) {
                    new Label("with no friends ").center().addTo(p);
                } else {
                    new Label("with " + (nbrParticipant - 1) + "friends ").center().addTo(p);
                }
                new Button("Open").center().addTo(p);
                p.sizeTo(35, 5);
                pnlBody.addComponent(p.withBorder(Borders.singleLine()));
                }


        }
        private String getTitleWithUser() {
            return "Tricount (" + Security.getLoggedUser().getMail() + " - User )";
        }
        public void profile(){
            Panel pnlProfile = Panel.verticalPanel();
            pnlProfile.withBorder(Borders.singleLine("View"));
            new Label("Hey Boris!").addTo(pnlProfile);
            setHints(List.of(Hint.CENTERED));
            setCloseWindowWithEscape(true);
            pnlBody.addComponent(pnlProfile.withBorder(Borders.singleLine("View Profile")));
        }
    }