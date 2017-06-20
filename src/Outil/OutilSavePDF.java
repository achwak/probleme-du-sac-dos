/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outil;


import Entity.Objet;
import GestionExportation.FichierSave;
import GestionExportation.PDF.ExportationPDF;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class OutilSavePDF {
    
  public static File SaveFichierPDF(String Titre,int[][] data,String[] entete,ArrayList<Objet> ob,String TitreTable,String utilisation,float totalValuer,int poidOptimal) throws IOException, FileNotFoundException, DocumentException
    {
    File file = new FichierSave().choisirFichierPDF(Titre); //appel a saveFileDialogue
      if(file!=null){
      ExportationPDF export= new ExportationPDF();
      export.ExportaterPDF(file.getPath(), data, entete,ob,entete.length,TitreTable,utilisation,totalValuer,poidOptimal);
      }   
    return file;
    }
  
}
