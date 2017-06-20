/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GestionExportation.PDF;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;


public class AjoutParagraph { // ajouter du text plus son alignement : un seul alignement à la fois , par defaut c'est l'alignement gauche.
      public Paragraph NewParagraph(String text, boolean alignCentre, boolean alignLeft, boolean alignRight){
          
        Paragraph myPara =new Paragraph();
        
        if((alignCentre) && (!alignLeft) && (!alignRight)){
            
            myPara.setAlignment(Element.ALIGN_CENTER);
            
        }
        else{
            if(((!alignCentre) && (alignLeft) && (!alignRight)) || ((!alignCentre) && (!alignLeft) && (!alignRight)) ){
            myPara.setAlignment(Element.ALIGN_LEFT);
            }
            else{
                
                 if( (!alignCentre) && (!alignLeft) && (alignRight)){
                 myPara.setAlignment(Element.ALIGN_RIGHT);
                 }
                 else{
                     System.out.println("il faut mettre un seul alignement a la fois!");
                 }
            }
        }
        myPara.add(text);
        return myPara;
    }
}
