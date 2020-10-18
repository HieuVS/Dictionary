package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.TreeMap;

/**
*
*/
public class addWordController {
    private Stage stage;
    @FXML
    private TextField ipNewWord;
    @FXML
    private TextArea ipMeaning;
    @FXML
    private Button add;

    public DictionaryCommandLine DCL=new DictionaryCommandLine();
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public static addWordController getInstance() {
        FXMLLoader loader =new FXMLLoader(Object.class.getResource("/sample/addWord.fxml"));
        try {
            AnchorPane anchorPane =(AnchorPane) loader.load();
            addWordController controller =loader.getController();

            Scene scene =new Scene(anchorPane);
            Stage stage=new Stage();
            stage.setTitle("Them tu moi");
            stage.setScene(scene);

            controller.setStage(stage);
            return controller;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void show() {
        if(stage !=null) {
            stage.show();
        }
    }
    //public TreeMap<String, String> newMap = new TreeMap<>();

    public TreeMap<String, String> addnewWord() throws Exception {
        if( !ipNewWord.getText().isEmpty()) {
            DCL.exportWordToFile(ipNewWord.getText(),ipMeaning.getText());
            String a= ipNewWord.getText();
            String b= ipMeaning.getText();
            DCL.map = DCL.addWord(a, b);

        }
        return DCL.map;
        //DCL.exportToFile(DCL.map);
    }

    /*  @FXML
    public void add(ActionEvent e) {
        addWordController controller = addWordController.getInstance();
        if (controller != null) {
            controller.show();
        } */
                /*      ObservableList<String> newWord= FXCollections.observableArrayList();
             /*       Alert alert =new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("ERROR");
                      alert.setHeaderText("Loi nhap tu");
                      alert.setContentText("Tu cua ban da bi trung. Vui long nhap lai");
                      alert.showAndWait();
                     ObservableList<String> matchedWords = new ArrayList<String>(dictionary.keySet());
                      if(newWord==) {}add an alert;
        wordList.add(String.valueOf(newWord));*/
}
