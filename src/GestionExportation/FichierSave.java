/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GestionExportation;

import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;


public class FichierSave {
      
       public File choisirFichierPDF(String Titre) throws IOException{
           
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Titre + " PDF ");

        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extentionFilter);

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if(!userDirectory.canRead()) {
                userDirectory = new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);
        
        File chosenFile = fileChooser.showSaveDialog(null);
       if(chosenFile != null)
        return chosenFile;
       else 
           return null;
    }
}
