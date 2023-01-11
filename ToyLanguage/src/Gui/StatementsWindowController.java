package Gui;

import Controller.Controller;
import Exception.*;
import Model.ADT.Dictionary.MyDictionary;
import Model.Exp.*;
import Model.State.PrgState;
import Model.Statement.BasicStmt.*;
import Model.Statement.FileStmt.closeRFile;
import Model.Statement.FileStmt.openRFile;
import Model.Statement.FileStmt.readFile;
import Model.Statement.HeapStmt.NewStmt;
import Model.Statement.HeapStmt.WriteHeapStmt;
import Model.Statement.IStmt;
import Model.Statement.forkStmt;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.IRepository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatementsWindowController implements Initializable {

    @FXML
    Button statementButton;
    @FXML
    ListView<IStmt> statementListView;
    MainWindowController mainWindowController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayExemples();
    }

    @FXML
    public IStmt selectExample(ActionEvent actionEvent) {
        return statementListView.getSelectionModel().getSelectedItem();
    }



    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public List<IStmt> initExemple() {
        List<IStmt> statementList = new ArrayList<>();

        IStmt ex1 = new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        IStmt ex2 = new CompStmt(new VarDecl("a", new IntType()), new CompStmt(new VarDecl("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        IStmt ex3 = new CompStmt(new VarDecl("a", new BoolType()), new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

        IStmt ex4 = new CompStmt(
                new VarDecl("varf", new StringType()), new CompStmt(
                new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(
                new openRFile(new VarExp("varf")), new CompStmt(
                new VarDecl("varc", new IntType()), new CompStmt(
                new readFile(new VarExp("varf"), "varc"), new CompStmt(
                new PrintStmt(new VarExp("varc")), new CompStmt(
                new readFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));

        IStmt ex5 = new CompStmt(
                new VarDecl("a", new IntType()),
                new CompStmt(new VarDecl("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(10))),
                                new CompStmt(new IfStmt(new RelExp(1, new VarExp("a"), new VarExp("v")),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));

        IStmt ex6 = new CompStmt(
                new VarDecl("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(
                                new WhileStmt(new RelExp(6, new VarExp("v"), new ValueExp(new IntValue(0))),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")),
                                                new AssignStmt("v", new ArithExp(2, new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));

        IStmt ex7 = new CompStmt(
                new VarDecl("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))), new CompStmt(new WriteHeapStmt("v", new ValueExp(
                                new IntValue(30))), new PrintStmt(new ArithExp(1, new ReadHeapExp(new VarExp("v")), new ValueExp(
                                new IntValue(5))))))));

        IStmt ex8 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))), new CompStmt(new NewStmt("a", new VarExp("v")),
                                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));

        IStmt forked = new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))));

        IStmt ex9 = new CompStmt(new VarDecl("v", new IntType()),
                new CompStmt(new VarDecl("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new forkStmt(forked), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))
                        )));

        statementList.add(ex1);
        statementList.add(ex2);
        statementList.add(ex3);
        statementList.add(ex4);
        statementList.add(ex5);
        statementList.add(ex6);
        statementList.add(ex7);
        statementList.add(ex8);
        statementList.add(ex9);

        return statementList;
    }

    private void displayExemples() {
        List<IStmt> exempleList = initExemple();
        statementListView.setItems(FXCollections.observableList(exempleList));
        statementListView.getSelectionModel().select(0);

        statementListView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2){
                int index = statementListView.getSelectionModel().getSelectedIndex() + 1;
                System.out.println(index);
                IStmt currentProgramSelected = statementListView.getSelectionModel().getSelectedItem();

                PrgState programState = new PrgState(currentProgramSelected);
                IRepository repo = new Repository("log"+index+".txt");
                Controller controller = new Controller(repo);

                controller.addPrgState(programState);

                try {
                    currentProgramSelected.typecheck(new MyDictionary<String, Type>());
                    mainWindowController.setController(controller);
                } catch (MyException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
                    alert.show();
                }
            }
        });
    }


}
