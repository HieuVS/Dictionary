package main.java.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Module.DictionaryCommandLine;
import main.java.Module.Trie;

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
        FXMLLoader loader =new FXMLLoader(addWordController.class.getResource("../../resources/Fxml/addWord.fxml"));
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

    public Trie addnewWord() throws Exception {
        if( !ipNewWord.getText().isEmpty()) {
            //DCL.exportWordToFile(ipNewWord.getText(),ipMeaning.getText());//?
            String a= ipNewWord.getText();
            String b= ipMeaning.getText();
            //DCL.map = DCL.addWord(a, b);
            DCL.trie.insert(a,b);
        }
        return DCL.trie;
    }

}
