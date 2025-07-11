/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.ui.utils.LogicUI;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

// you use this when you want to switch pane in a given layout~~~~
public class PaneSwitch {
    private Pane view;
    
    public Pane getPage(String path) {
        try {
            URL paneURL = getClass().getResource(path);
            if(path == null) {
                throw new java.io.FileNotFoundException("UI ERROR: The path of page " + path + " cannot be found.");
            }
            
            view = FXMLLoader.load(paneURL);
        } catch (Exception e) {
            System.out.println("UI ERROR: The path of page " + path + " cannot be found.");
        }
        
        return view;
    }
    
}
