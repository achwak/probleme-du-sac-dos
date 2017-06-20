/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GestionExportation.PDF;

import java.io.File;
import java.io.IOException;


public class FilePDF extends File{

    public FilePDF(String pathname) {
        super(pathname);
        
        if(this.createNewFile()) System.out.println("fichier pdf est creer");
    }
    
    
    @Override
    public boolean createNewFile(){
        try{
            super.createNewFile();
            return true;
        }
        catch(IOException e){
            System.out.println("Erreur lors de creation du fichier pdf");
            e.getStackTrace();
            return false;
        }
    }
}
