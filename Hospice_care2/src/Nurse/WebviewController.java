/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nurse;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Reverside
 */
public class WebviewController implements Initializable {
   
     @FXML private WebView webView;
    
   
    private WebEngine engine;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //this is going to load the engine for the web view and then you can use this web view to load different kinds of web content
      engine = webView.getEngine();
    }    
    
}
