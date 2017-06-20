/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GestionExportation.PDF;

import Entity.Objet;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.ArrayList;


public class AjoutTableau {
    
     public void addTable(Document myDoc,int nbCol, int table[][] , String entete[] ,ArrayList<Objet> ob, String nomTable,String utilisation,float gainTotal,int poidOptimal) throws DocumentException{
        
         // Ajouter un Titre 
        PdfPTable Titre = new PdfPTable(1);
        Titre.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        Titre.setWidthPercentage(100f); // Rendre la largeur de la table == la largeur du document
        Titre.setSpacingBefore(8);
        Titre.setSpacingAfter(10);
        Phrase titr =new Phrase(nomTable,new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.CYAN));
        PdfPCell cel = new PdfPCell(titr);
        cel.setBorderColor(BaseColor.WHITE);
        cel.setHorizontalAlignment(Element.ALIGN_CENTER);
        Titre.addCell(cel);
       
        myDoc.add(Titre);
        /************************************************************************************************************/
        // Afficher la lsite originale des objets:
        PdfPTable Titre3 = new PdfPTable(1);
        Titre3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        Titre3.setWidthPercentage(100f); // Rendre la largeur de la table == la largeur du document
        Titre3.setSpacingBefore(8);
        Titre3.setSpacingAfter(10);
        Phrase titr3 =new Phrase(" La liste des objets qu'on possède :",new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.RED));
        PdfPCell cel3 = new PdfPCell(titr3);
        cel3.setBorderColor(BaseColor.WHITE);
        cel3.setHorizontalAlignment(Element.ALIGN_LEFT);
        Titre3.addCell(cel3);
       
        myDoc.add(Titre3);
        
        PdfPTable pdfTab3 = new PdfPTable(ob.size()+1);
        pdfTab3.setWidthPercentage(100f); // Rendre la largeur de la table == la largeur du document
        for(int i=0; i<=ob.size(); i++){ // ecrire les nom des colonne
           if(i==0)
           {
            Phrase p =new Phrase("Objet: ",new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.CYAN));
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.DARK_GRAY);
            pdfTab3.addCell(pdfcel);
           }
           else{
            Phrase p =new Phrase(""+ob.get(i-1).getId(),new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.RED));
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.WHITE);
            pdfTab3.addCell(pdfcel);
           }
        }
        for(int i=0; i<=ob.size(); i++){ // ecrire les nom des colonne
           if(i==0)
           {
            Phrase p =new Phrase("Poid: ",new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.CYAN));
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.DARK_GRAY);
            pdfTab3.addCell(pdfcel);
           }
           else{
            Phrase p =new Phrase(""+ob.get(i-1).getPoid(),new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.RED));
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.WHITE);
            pdfTab3.addCell(pdfcel);
           }
        }
       for(int i=0; i<=ob.size(); i++){ // ecrire les nom des colonne
           if(i==0)
           {
            Phrase p =new Phrase(" Valeur : ",new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.CYAN));
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.DARK_GRAY);
            pdfTab3.addCell(pdfcel);
           }
           else{
            Phrase p =new Phrase(""+ob.get(i-1).getGain(),new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.RED));
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.WHITE);
            pdfTab3.addCell(pdfcel);
           }
        }
        myDoc.add(pdfTab3);
        
        /************************************************************************************************************/
        // Ajouter le tableu des resultats
        PdfPTable Titre4 = new PdfPTable(1);
        Titre4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        Titre4.setWidthPercentage(100f); // Rendre la largeur de la table == la largeur du document
        Titre4.setSpacingBefore(8);
        Titre4.setSpacingAfter(10);
        Phrase titr4 =new Phrase(" Le tableau des résultats :",new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.RED));
        PdfPCell cel4 = new PdfPCell(titr4);
        cel4.setBorderColor(BaseColor.WHITE);
        cel4.setHorizontalAlignment(Element.ALIGN_LEFT);
        Titre4.addCell(cel4);
       
        myDoc.add(Titre4);
        
        PdfPTable pdfTab = new PdfPTable(nbCol);
        pdfTab.setWidthPercentage(100f); // Rendre la largeur de la table == la largeur du document
        
        Font font = new Font(Font.FontFamily.HELVETICA,10,Font.BOLD,BaseColor.CYAN);
        
        for(int i=0; i<nbCol; i++){ // ecrire les nom des colonne
            Phrase p =new Phrase(entete[i],font);
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.DARK_GRAY);
            pdfTab.addCell(pdfcel);
        }
        pdfTab.setHeaderRows(1); // Specifier que la premier ligne est un header pour qu'il soit repeté sur chaque page
        for(int[] data : table){   // ecrire tous les donnees
            
            for(int element : data){
                pdfTab.addCell(""+element);
            }
        }
        
        if(utilisation.equalsIgnoreCase("Gain")) 
        {
            Font f = new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.RED);
            PdfPCell temp = new PdfPCell(new Phrase("  ", f));
            temp.setBorderColorLeft(BaseColor.WHITE);
            temp.setBorderColorRight(BaseColor.WHITE);
            temp.setColspan(nbCol);
            pdfTab.addCell(temp);
            PdfPCell total = new PdfPCell(new Phrase(" Le gain optimal  ", new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.CYAN)));
            total.setBackgroundColor(BaseColor.DARK_GRAY);
            total.setColspan((int)nbCol/2);
            pdfTab.addCell(total);
            
            PdfPCell Tvalue = new PdfPCell(new Phrase(Float.toString(gainTotal), f));
            Tvalue.setColspan((int)nbCol/2);
            pdfTab.addCell(Tvalue);
            
            // Afficher le poid Optimale :
            PdfPCell tempO = new PdfPCell(new Phrase("  ", f));
            tempO.setBorderColorLeft(BaseColor.WHITE);
            tempO.setBorderColorRight(BaseColor.WHITE);
            tempO.setColspan(nbCol);
            pdfTab.addCell(tempO);
            PdfPCell totalO = new PdfPCell(new Phrase(" Le poid optimal  ", new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.CYAN)));
            totalO.setBackgroundColor(BaseColor.DARK_GRAY);
            totalO.setColspan((int)nbCol/2);
            pdfTab.addCell(totalO);
            
            PdfPCell TvalueO = new PdfPCell(new Phrase(Integer.toString(poidOptimal), f));
            TvalueO.setColspan((int)nbCol/2);
            pdfTab.addCell(TvalueO);
        }
        myDoc.add(pdfTab);
        
        // Afficher la liste des objets pris
        PdfPTable Titre2 = new PdfPTable(1);
        Titre2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        Titre2.setWidthPercentage(100f); // Rendre la largeur de la table == la largeur du document
        Titre2.setSpacingBefore(8);
        Titre2.setSpacingAfter(12);
        Phrase titr2 =new Phrase(" La liste des objets pris dans le sac à dos :",new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.RED));
        PdfPCell cel2 = new PdfPCell(titr2);
        cel2.setBorderColor(BaseColor.WHITE);
        cel2.setHorizontalAlignment(Element.ALIGN_LEFT);
        Titre2.addCell(cel2);
       
        myDoc.add(Titre2);
        
        PdfPTable pdfTab2 = new PdfPTable(ob.size()+1);
        pdfTab2.setWidthPercentage(100f); // Rendre la largeur de la table == la largeur du document
        int nbrCaseManque=0; // le nbr de case qui manque pour l'affichage car on odit remplir tt la table
        for(int i=0; i<=ob.size(); i++){ // ecrire les nom des colonne
           if(i==0)
           {
            Phrase p =new Phrase("Objet: ",new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.CYAN));
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.DARK_GRAY);
            pdfTab2.addCell(pdfcel);
           }
           else{
           if(ob.get(i-1).isPris()) // L'objet est pris
           {
            Phrase p =new Phrase(""+ob.get(i-1).getId(),new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.RED));
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.WHITE);
            pdfTab2.addCell(pdfcel);
           }
           else{
               nbrCaseManque++;
           }
           }
        }
        for(int i=0; i<=nbrCaseManque; i++){ // ecrire les nom des colonne
            Phrase p =new Phrase("",new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.RED));
            PdfPCell pdfcel = new PdfPCell(p);
            pdfcel.setBackgroundColor(BaseColor.WHITE);
            pdfTab2.addCell(pdfcel);
        }
        myDoc.add(pdfTab2);
        
    }
     
     
     
}
