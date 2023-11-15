package tgpr.tricount.view;


import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Panel;

import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Model;
import tgpr.tricount.controller.*;
import tgpr.tricount.model.Tricount;


import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import tgpr.framework.Controller;
import tgpr.framework.Paginator;
import tgpr.framework.Spacing;
import tgpr.tricount.model.Security;


import java.util.List;

public class TricountListView extends BasicWindow {
    private final TricountListController controller;
    private final List<Tricount> tricountList = Tricount.getAll();
    private final Panel pnlBody;
    private final Panel pnlEnTete;
    private final Panel pnlFile;
    private final Panel pnlBasDePage;
    private final Panel pnlProfile = new Panel();
    private final TextBox filter;
    private final Paginator pagination;
    private Menu menuFile = new Menu("File");
    private final Button createTricount;
    private Button close;

    public TricountListView(TricountListController controller, profileController controllerProfil) {
        Model.clearCache();
        this.controller = controller;
        setTitle(getTitleWithUser());
        setHints(List.of(Hint.EXPANDED));
        Panel root = new Panel().setLayoutManager(new BorderLayout());
        setComponent(root);

        MenuBar menuBar = new MenuBar().addTo(root);
        menuFile = new Menu("File");
        addShortcut(menuFile, KeyStroke.fromString("<A-f>"));
        menuBar.add(menuFile);
        MenuItem menuLogout = new MenuItem("Logout!", controller::logout);
        menuFile.add(menuLogout);


        pnlEnTete = Panel.gridPanel(3, Spacing.of(0));

        pnlFile = Panel.gridPanel(2);
        pnlFile.addComponent(createMenu(controllerProfil));
        pnlEnTete.addComponent(pnlFile, BorderLayout.Location.TOP);
        root.addComponent(pnlEnTete);
        root.addComponent(pnlEnTete, BorderLayout.Location.TOP);
        new EmptySpace().addTo(pnlEnTete);
        new EmptySpace().addTo(pnlEnTete);


        pnlEnTete.addComponent(new Label("filter:"));
        filter = new TextBox().addTo(pnlEnTete).sizeTo(30).setTextChangeListener((txt, filter) -> reloadData());
        pnlEnTete.addComponent(filter, BorderLayout.Location.BOTTOM);
        pnlBody = Panel.gridPanel(3, Spacing.of(0));
        root.addComponent(pnlBody, BorderLayout.Location.CENTER);

        pnlBasDePage = new Panel().setLayoutManager(new BorderLayout());
        root.addComponent(pnlBasDePage, BorderLayout.Location.BOTTOM);
        createTricount = new Button("Create a new Tricount", () -> Controller.navigateTo(new AddTricountControler(Security.getLoggedUser())));
        pnlBasDePage.addComponent(createTricount, BorderLayout.Location.LEFT);
        pagination = new Paginator(this, 12, this::pageChanged);
        pnlBasDePage.addComponent(pagination, BorderLayout.Location.RIGHT);
        reloadData();
        close = new Button("ce bouton naparait pas", () -> System.exit(0));
        close.setVisible(false);
        close.addTo(root);
        addShortcut(close, KeyStroke.fromString("<A-c>"));


    }

    public void pageChanged(int page) {
        // Remarque: les pages sont indexées à partir de zéro
        System.out.println("L'utilisateur a demandé la page " + (page + 1));
        // aller en db pour récupérer les tricounts de la page courante (limit)
        // appeler reloadData() pour afficher
        reloadData();
    }

    public void reloadData() {
        Model.clearCache();
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
            new Button("Open", () -> Controller.navigateTo(new ViewTricoutController(tricount, Security.getLoggedUser()))).center().addTo(p);
            p.sizeTo(35, 5);
            pnlBody.addComponent(p.withBorder(Borders.singleLine()));
        }


    }

    private String getTitleWithUser() {
        return "Tricount (" + Security.getLoggedUser().getMail() + " - User )";
    }

    public void profile() {
        Panel pnlProfile = Panel.verticalPanel();
        pnlProfile.withBorder(Borders.singleLine("View"));
        new Label("Hey Boris!").addTo(pnlProfile);
        setHints(List.of(Hint.CENTERED));
        setCloseWindowWithEscape(true);
        pnlBody.addComponent(pnlProfile.withBorder(Borders.singleLine("View Profile")));
    }

    private MenuBar createMenu(profileController controllerProfil) {
        MenuBar menuBar = new MenuBar();
        menuBar.add(menuFile);
        MenuItem menuprofile = new MenuItem("View Profile", () -> {
            Controller.navigateTo(controllerProfil);
        });
        menuFile.add(menuprofile);
        MenuItem menuLogout = new MenuItem("Logout", controller::logout);
        addShortcut(menuLogout, KeyStroke.fromString("<A-c>"));
        menuFile.add(menuLogout);
        return menuBar;
    }
}
