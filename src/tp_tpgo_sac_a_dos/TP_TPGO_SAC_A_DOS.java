/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_tpgo_sac_a_dos;

import Entity.Objet;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author walid y
 */
public class TP_TPGO_SAC_A_DOS extends Application {
    
    public static  int nbr_objet=-1,nbr_objet_ajoute,max_poid;
    public static ArrayList<Objet> listObjet = new ArrayList<Objet>();
    
    public static Stage mainstage;
    @Override
    public void start(Stage stage) throws Exception {
        //AquaFx.style();
        //FlatterFX.style();
        Parent root = FXMLLoader.load(getClass().getResource("FenetreMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        mainstage = stage;
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
