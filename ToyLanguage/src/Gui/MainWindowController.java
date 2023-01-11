package Gui;

import Controller.Controller;
import Exception.*;
import Model.ADT.Stack.MyIStack;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Value.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {

    @FXML
    TableView<Map.Entry<String, Value>> symTableView;
    @FXML
    TableColumn<Map.Entry<String, Value>,String> symTableName;
    @FXML
    TableColumn<Map.Entry<String, Value>,String> symTableValue;
    @FXML
    TableView<Map.Entry<Integer, Value>> heapTableView;
    @FXML
    TableColumn<Map.Entry<Integer, Value>,Integer> heapTableAddr;
    @FXML
    TableColumn<Map.Entry<Integer, Value>,String> heapTableValue;
    @FXML
    ListView<String> exeStackListView;
    @FXML
    ListView<String> outListView;
    @FXML
    ListView<String> fileListView;
    @FXML
    ListView<String> prgStatesIDListView;
    @FXML
    Label prgStatesNumber;
    @FXML
    Button execButton;

    Controller controller;


    public Controller getController() {
        return controller;
    }
    public void setController(Controller controller) {
        this.controller = controller;
        populatePrgStatesNumber();
        populatePrgStatesIDListView();
        execButton.setDisable(false);
    }

    private void populateSymTableView(PrgState givenProgramState){
        symTableView.setItems(FXCollections.observableList(new ArrayList<>(givenProgramState.getSymTable().getContent().entrySet())));
        symTableView.refresh();
    }

    private void populateHeapTableView(PrgState givenProgramState){
        heapTableView.setItems(FXCollections.observableList(new ArrayList<>(givenProgramState.getHeap().getContent().entrySet())));
        heapTableView.refresh();

    }

    private void populateExeStackListView(PrgState givenProgramState){
        MyIStack<IStmt> stack = givenProgramState.getStk();
        List<String> stackOutput = new ArrayList<>();
        for (Object stm : stack.getValues()){
            stackOutput.add(stm.toString());
        }
        Collections.reverse(stackOutput);
        exeStackListView.setItems(FXCollections.observableArrayList(stackOutput));
    }

    private void populateOutListView(PrgState givenProgramState){
        outListView.setItems(FXCollections.observableArrayList(givenProgramState.getOut().getContent().toString()));
    }

    private void populateFileListView(PrgState givenProgramState){
        fileListView.setItems(FXCollections.observableArrayList(givenProgramState.getFileTable().getContent().keySet()));

    }

    private void populatePrgStatesIDListView(){
        prgStatesIDListView.setItems(FXCollections.observableArrayList(controller.getRepo().getPrgStates().stream().map(PrgState::getId).collect(Collectors.toList()).toString()));
        prgStatesIDListView.refresh();
    }

    private void populatePrgStatesNumber(){
        prgStatesNumber.setText("Number of Program States: " + controller.getRepo().getPrgStates().size());
    }

    @FXML
    private void oneStepHandler(ActionEvent actionEvent){
        if(controller==null){
            Alert error = new Alert(Alert.AlertType.ERROR,"No program selected!");
            error.show();
            execButton.setDisable(true);
            return;
        }
        PrgState programState = getSelectedProgramState();
        if(programState!=null && !programState.isNotCompleted()){
            Alert error = new Alert(Alert.AlertType.ERROR,"Nothing left to execute!");
            error.show();
            return;
        }
        try {
            controller.oneStep();
            changeProgramStateHandler(programState);
            if(controller.getRepo().getPrgStates().size()==0)
                execButton.setDisable(true);

        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage());
            error.show();
            execButton.setDisable(true);
        }

    }

    private PrgState getSelectedProgramState(){
        if(prgStatesIDListView.getSelectionModel().getSelectedIndex()==-1)
            return null;
        int id = prgStatesIDListView.getSelectionModel().getSelectedIndex();
        return controller.getRepo().getPrgStateWithId(id);
    }
    private void changeProgramStateHandler(PrgState currentProgState){
        if(currentProgState==null)
            return;
        populatePrgStatesNumber();
        populatePrgStatesIDListView();
        populateHeapTableView(currentProgState);
        populateOutListView(currentProgState);
        populateFileListView(currentProgState);
        populateExeStackListView(currentProgState);
        populateSymTableView(currentProgState);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.controller = null;

        heapTableAddr.setCellValueFactory(p-> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapTableValue.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getValue() + " "));

        symTableName.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey() + " "));
        symTableValue.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getValue() + " "));

        prgStatesIDListView.setOnMouseClicked(mouseEvent -> changeProgramStateHandler(getSelectedProgramState()));

    }
}
