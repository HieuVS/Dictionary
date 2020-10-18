package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.TreeMap;

public class changeWordController {
    @FXML
    private Button change;
    @FXML
    private TextField ipChangedWord;
    @FXML
    private TextArea ipMeaning;

    public DictionaryCommandLine DCL=new DictionaryCommandLine();
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public static changeWordController getInstance() {
        FXMLLoader loader =new FXMLLoader(Object.class.getResource("/sample/changeWord.fxml"));
        try {
            AnchorPane anchorPane =(AnchorPane) loader.load();
            changeWordController controller =loader.getController();

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

    public TreeMap<String, String> changeWord() throws Exception {
        if( !ipChangedWord.getText().isEmpty()) {
            DCL.exportWordToFile( ipChangedWord.getText(),ipMeaning.getText());
            String a= ipChangedWord.getText();
            String b= ipMeaning.getText();
            DCL.map = DCL.addWord(a, b);
        }
        return DCL.map;
    }
}
