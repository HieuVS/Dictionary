package main.java.UI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javazoom.jl.decoder.JavaLayerException;
import main.java.Module.DictionaryCommandLine;
import main.java.Module.Trie;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Predicate;

public class Controller  implements Initializable {
    @FXML
    private AnchorPane buttonAdd;
//    @FXML
//    private Button buttonDel;
    @FXML
    private AnchorPane buttonSearch;
    @FXML
    private AnchorPane buttonChange;
    @FXML
    private AnchorPane speaker;
    @FXML
    private AnchorPane image;
    @FXML
    private AnchorPane buttonDel;
    @FXML
    private TextArea toMeaning;
    @FXML
    private TextField inputWord;
    @FXML
    private ListView<String> words;

    public TreeMap<String, String> newMap = new TreeMap<String, String>();
    DictionaryCommandLine DCL = new DictionaryCommandLine();
 /*   public void control () throws Exception {
        DCL.insertFromDataset2();
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            newMap=DCL.insertFromDataset2();
        } catch (Exception e) {
            e.printStackTrace();
        }
        words.getItems().addAll(DCL.map.keySet());

        words.setOnMouseClicked(event -> {
            String item = words.getSelectionModel().getSelectedItem();
            if (newMap.containsKey(item)) {
//                String meaning = DCL.map.get(item);
//                toMeaning.setText(meaning);
                inputWord.setText(item);
            }
            /*
            String meaning =DCL.dictionaryLookupVjp(item);
            if (meaning!=null) {
                toMeaning.setText(meaning);
            }

             */
        });
        inputWord.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                List<String> matchedWords = new ArrayList<>(newMap.keySet());
                String pre = inputWord.getText();
                matchedWords.removeIf(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return !s.startsWith(pre);
                    }
                });
                words.getItems().clear();
                words.getItems().addAll(matchedWords);
            }
        });
        speaker.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Audio audio = Audio.getInstance();
                InputStream sound = null;
                if (inputWord.getText().trim().isEmpty()) {
                    try {
                        sound = audio.getAudio("Type the word that need translating");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        audio.play(sound);
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sound = audio.getAudio(inputWord.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        audio.play(sound);
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        buttonDel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deleteWordController controller = deleteWordController.getInstance();
                if (controller != null) {
                    controller.show();
                    try {
                        DCL.trie= new Trie();
                        controller.removeWord();
                        DCL.trie.putAll(controller.DCL.map,DCL.trie);
                        newMap.putAll(controller.DCL.map);
                        //newMap.putAll(DCL.map);
                        //words.getItems().addAll(newMap.keySet());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        buttonAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addWordController controller = addWordController.getInstance();
                if (controller != null) {
                    controller.show();
                    try {
                        DCL.trie=controller.addnewWord();
                        //DCL.map.putAll(newMap);
                        DCL.trie.putAll(DCL.map, DCL.trie);
                        //words.getItems().clear();
                        //words.getItems().addAll(DCL.map.keySet());
                        //newMap.putAll(DCL.map);
                      //  DCL.map.putAll();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    if(newMap.isEmpty()) {
//                        DCL.map.clear();
//                    }
                }
            }
        });
        buttonSearch.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (DCL.trie.contains(inputWord.getText())) {
                    toMeaning.setText(DCL.trie.search(inputWord.getText()));
                }
                else{
                    toMeaning.setText("Sorry! We can't find your word!");
                }
            }
        });
        buttonChange.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeWordController controller = changeWordController.getInstance();
                if (controller != null) {
                    controller.show();
                        try {
                            DCL.map=controller.changeWord();
                            DCL.map.putAll(newMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }
        });
    }

}

