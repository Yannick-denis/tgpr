package tgpr.tricount.view;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import java.util.List;
public class profileView extends DialogWindow  {
    public profileView(String title) {
        super(title);

        setHints(List.of(Hint.CENTERED, Hint.FIXED_SIZE));
//      permet de fermer la fenêtre en pressant la touche Esc
        setCloseWindowWithEscape(true);
        // définit une taille fixe pour la fenêtre de 15 lignes et 70 colonnes
        setFixedSize(new TerminalSize(70, 15));
    }
}


//
//public class EditMemberView extends DialogWindow {
//
//    private final EditMemberController controller;
//
//    private final Member member;
//
//    public EditMemberView(EditMemberController controller, Member member) {
//        // définit le titre de la fenêtre
//        super((member == null ? "Add " : "Update ") + "Member");
//
//        this.member = member;
//        this.controller = controller;
//
//        setHints(List.of(Hint.CENTERED, Hint.FIXED_SIZE));
//        // permet de fermer la fenêtre en pressant la touche Esc
//        setCloseWindowWithEscape(true);
//        // définit une taille fixe pour la fenêtre de 15 lignes et 70 colonnes
//        setFixedSize(new TerminalSize(70, 15));
//    }
//}
