package tgpr.tricount.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import tgpr.framework.Margin;
import tgpr.tricount.controller.AddTricountControler;

import com.googlecode.lanterna.TerminalSize;

import tgpr.tricount.model.Tricount;
import tgpr.tricount.model.User;

import java.util.List;




public class AddTricountView extends DialogWindow {
        private  TextBox txtTitle = new TextBox("", TextBox.Style.MULTI_LINE);
        private TextBox txtDescription;

        private Button btncreate;
        private Label errBody = new Label("");
        private AddTricountControler controler;

        public AddTricountView(AddTricountControler controler) {
            super("Create new Tricount ");

            this.controler=controler;

            setHints(List.of(Window.Hint.CENTERED, Window.Hint.MODAL));
            setCloseWindowWithEscape(true);

            Panel root = Panel.verticalPanel();
            setComponent(root);

            createFields().addTo(root);
            createButtons().addTo(root);

        }
        private Panel createFields() {
            Panel panel = Panel.gridPanel(2, Margin.of(1));

            new Label("Titre").addTo(panel);
            txtTitle.addTo(panel)
                    .setPreferredSize(new TerminalSize(20,1))
                    .setTextChangeListener((tkt,byuser)->validate());
            panel.addEmpty();
            errBody.setForegroundColor(TextColor.ANSI.RED).addTo(panel);

            new Label ("Description").addTo(panel);
            txtDescription=new TextBox().sizeTo(30,5).addTo(panel);


            return panel;
        }

        private Panel createButtons() {
            var panel = Panel.horizontalPanel().center();


            btncreate= new Button ("Create", () ->{
                save();
            }).setEnabled(false).addTo(panel);
            addShortcut(btncreate, KeyStroke.fromString("<A-s>"));
            Button btnclose=new Button ("Cancel", this::close).addTo(panel);
            addShortcut(btnclose, KeyStroke.fromString("<A-c>"));


            return panel;
        }

        public void validate(){
            var error= controler.validate(txtTitle.getText());
            errBody.setText(error.getFirstErrorMessage(Tricount.Fields.Title));
            btncreate.setEnabled(error.isEmpty());

        }

        private void save() {
            controler.save(txtTitle.getText(),txtDescription.getText());
            close();
        }


    }


