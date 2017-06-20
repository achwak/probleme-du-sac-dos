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
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExportationPDF {
    private Document myDocument = new Document();
    
    
    //tableau contien les données en 2d , et entete contient les noms des colonnes.
    public void ExportaterPDF(String pathFichierPDF, int[][] tableau, String[] entete,ArrayList<Objet> ob, int nbColonne,String TitreTable,String utilisation,float totalValeur,int poidOptimal) throws FileNotFoundException, DocumentException{
        
        if (utilisation.equalsIgnoreCase("Gain") ) myDocument= new Document(PageSize.LETTER.rotate()); // Afficher en Paysage
        
        PdfWriter.getInstance(this.myDocument, new FileOutputStream(new FilePDF(pathFichierPDF)));
        this.myDocument.open();
        
        
        addMetaData(" PDF DOC", "AZOUD / YOUNES", "AAZOUD / YOUNES", "etat", "pdf , etat, Resultat_Sac_A_dos");
        
        
      //  addToPage(new AjoutParagraph().NewParagraph("ENIEM", true, false, false));
      //  addToPage(new AjoutParagraph().NewParagraph("UNITE FROID", false, true, false));
      //  addToPage(new AjoutParagraph().NewParagraph("DEPARTEMENT METHODES ET QUALITE", false, true, false));
        // addToPage(new AjoutParagraph().NewParagraph("ETAT MENSUEL DES REBUTS", true, true, true));
        
        addHeaderTable(myDocument, LocalDate.now().toString(),0); // Ajouter un header 
        
        // saut de ligne
        this.addToPage(new AjoutParagraph().NewParagraph("  ", true, true, true));
        
       // ajouter notre tableau on appelant la classe ajoutTableau
        AjoutTableau aj =  new AjoutTableau();
        aj.addTable(myDocument,nbColonne, tableau, entete,ob,TitreTable,utilisation,totalValeur,poidOptimal); 
        
        
        //creer une nouvelle page
        this.NewPage(); 
        
        this.myDocument.close();
    }
    
    // pour ajouter des information sue le fichier pdf
    public void addMetaData(String titre, String auteur, String Createur, String sujet,String motCle){
        this.myDocument.addAuthor(auteur);
        this.myDocument.addTitle(titre);
        this.myDocument.addCreator(Createur);
        this.myDocument.addSubject(sujet);
        this.myDocument.addKeywords(motCle);
    }
    
    public void NewPage(){
        this.myDocument.newPage();
    }
    //***************ajouter un element : paragraphe ou section ou phrase ...//
    public void addToPage(Element element) throws DocumentException{
        this.myDocument.add(element);
    }
    
     public float addHeaderTable(Document document, String day, int page) throws DocumentException {
        
        PdfPTable header = new PdfPTable(3);
        header.setWidthPercentage(100);
        header.getDefaultCell().setBackgroundColor(BaseColor.BLACK);
        
        Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
        Font font2 = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.CYAN);
        
        Phrase p = new Phrase("Ecole : ", font);
        header.addCell(p);
        
        header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        p = new Phrase("ESI", font2);
        header.addCell(p);
        
        header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        p = new Phrase(day, font);
        header.addCell(p);
        
        /* // Ajouter le numero de la page 
        header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        p = new Phrase(String.format("page %d", page), font);
        header.addCell(p);
        */
        header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        p = new Phrase("Module : ", font);
        header.addCell(p);
        header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        p = new Phrase(" TPGO ", font2);
        header.addCell(p);
        
        p = new Phrase("  ", font);
        header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        header.addCell(p);
        
        header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        p = new Phrase(" Anée : ", font);
        header.addCell(p);
        
        // il faut completer la ligne sinon elle ne sera pas ajoutée  avec  header.completeRow();  ou :
        header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        p = new Phrase(" 2CS  ", font2);
        header.addCell(p);
        
        header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        p = new Phrase(" TP N°01 ", font);
        header.addCell(p);
        
        document.add(header);
        return header.getTotalHeight();
    }

    
}
