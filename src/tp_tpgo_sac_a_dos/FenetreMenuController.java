/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_tpgo_sac_a_dos;

import Calcul.traitement;
import com.itextpdf.text.DocumentException;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static tp_tpgo_sac_a_dos.TP_TPGO_SAC_A_DOS.mainstage;


public class FenetreMenuController implements Initializable {
    
    @FXML private void ClikEntrer(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("FenetrePrincipale.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMaximized(true);
        TP_TPGO_SAC_A_DOS.mainstage.close();
        stage.show();
    }
    
    @FXML private void ClikSortir(ActionEvent event){
        TP_TPGO_SAC_A_DOS.mainstage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
   

}
