package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.TreeMap;

public class deleteWordController {
    private Stage stage;
    @FXML
    private TextField deleteWord;
    @FXML
    private Button delete;

    DictionaryCommandLine DCL= new DictionaryCommandLine();
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public static deleteWordController getInstance() {
        FXMLLoader loader = new FXMLLoader(Object.class.getResource("/sample/deleteWordController.fxml"));
        try {
            AnchorPane anchorPane =(AnchorPane) loader.load();
            deleteWordController controller =loader.getController();

            Scene scene =new Scene(anchorPane);
            Stage stage=new Stage();
            stage.setTitle("Xóa từ");
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

    public TreeMap<String, String> removeWord() throws Exception {
        //javafx.event.ActionEvent actionEvent
        this.DCL.insertFromDataset2();
        String word=deleteWord.getText();
        this.DCL.deleteWord(word);
        this.DCL.deleteWordInFile(word);
        this.DCL.reWrite();
        return this.DCL.map;
    }

}
