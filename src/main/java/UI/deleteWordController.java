package main.java.UI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Module.DictionaryCommandLine;

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
        FXMLLoader loader = new FXMLLoader(deleteWordController.class.getResource("../../resources/Fxml/deleteWordController.fxml"));
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
    public void removeWord() throws Exception {
        String word=deleteWord.getText();
        if(!word.isEmpty()) {
            this.DCL.map=this.DCL.insertFromDataset2();
            this.DCL.map.remove(word);
            //this.DCL.deleteWordInFile(word);
            //this.DCL.reWrite();
        }
    }

}
