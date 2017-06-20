/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_tpgo_sac_a_dos;

import Calcul.traitement;
import Entity.Objet;
import com.itextpdf.text.DocumentException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;



/**
 *
 * @author walid y
 */
public class FenetrePrincipaleController implements Initializable {
    
  @FXML  int IDObjet=1;
  @FXML  ObservableList<Objet> data = FXCollections.observableArrayList();
  @FXML  boolean objet_ajoutes=false,nbr_max_objet_saisie=false,contenace_saisie=false;
    
  @FXML  TextField nbr_objets,poid_max,poid_objet,valuer_objet;
  @FXML  TableView Objet_Table;
  @FXML  TableColumn num_objet_col,poid_objet_col,valeur_objet_col;
   
  @FXML  boolean TextIsInteger(String text) {
        boolean verif=false;
        
        try{
            int i = new Integer(text); // On convertit la chaine en entier
            verif=true;
        }catch(NumberFormatException e)
        {
            verif=false;
        }
        return verif;
    }
    
   @FXML void afficheMessageErreur (String text){
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Message d'Erreur");
        alert.setHeaderText("");
        alert.setContentText(text);
        alert.show();
    }
    
    @FXML void afficheMessageConfirmation (String text){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Message de confirmation");
        alert.setHeaderText("");
        alert.setContentText(text);
        alert.show();
    }
     
    @FXML private void ClikCreerTableObjet(KeyEvent event) {
        
        KeyCode code= event.getCode();
        boolean verif;
        if (code== KeyCode.ENTER) 
        {
            verif = TextIsInteger(nbr_objets.getText());
            
            if(verif)
            {
                int in = Integer.parseInt(nbr_objets.getText());
                if (in>0)
                {
                    TP_TPGO_SAC_A_DOS.nbr_objet = in;
                    TP_TPGO_SAC_A_DOS.nbr_objet_ajoute = 0;
                    
                     num_objet_col.setCellValueFactory(new PropertyValueFactory<Objet,Integer>("id"));
                     poid_objet_col.setCellValueFactory(new PropertyValueFactory<Objet,Integer>("poid"));
                     valeur_objet_col.setCellValueFactory(new PropertyValueFactory<Objet,Integer>("gain"));
                     Objet_Table.setItems(data);
                     afficheMessageConfirmation("Le nombre d'objet maximum est ajouté ");
                     nbr_max_objet_saisie = true;
                }
                else
                {
                     nbr_max_objet_saisie = false;
                    afficheMessageErreur(" Le chmap ''nombre d'objet'' doit être un entier strictement positif !! ");
                }
                
            }
            else
            {
                 nbr_max_objet_saisie = false;
                afficheMessageErreur(" Le champ ''nombre d'objet'' doit être un entier !! ");
            }
            
        } 
       
    }
    
@FXML    private void ClikSetContenace (KeyEvent event)
    {
      if(event.getCode() == KeyCode.ENTER) {
          
            boolean verif_poid_max = TextIsInteger(poid_max.getText());
            if(verif_poid_max){
                
                int p = Integer.parseInt(poid_max.getText());
                if (p>0) {
                 
                    TP_TPGO_SAC_A_DOS.max_poid = p;
                    contenace_saisie = true;
                    afficheMessageConfirmation("Contenance maximale du Sac à dos sauvegardée ");
                }
                else {
                    contenace_saisie = false;
                    afficheMessageErreur(" Le champ ''contenance maximale'' doit être un entier strictement positif !!");
                }   
            }
            else {
                contenace_saisie=false;
                afficheMessageErreur(" Le champ ''contenance maximale'' doit être un entier !! ");
            }
      }
      
    }
    
    
   @FXML private void ClickAjouterObjet (ActionEvent event){
     
     objet_ajoutes=false;
     if(TP_TPGO_SAC_A_DOS.nbr_objet_ajoute < TP_TPGO_SAC_A_DOS.nbr_objet) {
          
        Objet ob = new Objet();
        boolean ok_poid=false,ok_valeur=false;
        boolean Verif_poid = TextIsInteger(poid_objet.getText());
        if(Verif_poid)
        {
            int poid = Integer.parseInt(poid_objet.getText());
            if (poid >=0)
            {
                ob.setPoid(poid);
                ok_poid=true;
            }
            else{
                afficheMessageErreur("Le poid de l'objet doit être un entier positif !!");
            }
        }else
        {
            afficheMessageErreur(" Le poid de l'objet doit être un entier !! ");
        }
        
        boolean verif_valeur = TextIsInteger(valuer_objet.getText());
        
        if(verif_valeur)
        {
            int valeur = Integer.parseInt(valuer_objet.getText());
            if (valeur>=0) {
                ob.setGain(valeur);
                ok_valeur = true;
            }
            else{
                afficheMessageErreur(" La valeur attribuée à l'objet doit être positive !!");
            }
        }else {
            afficheMessageErreur("La valeur de l'objet doit être un entier !!");
        }
        
       if (ok_poid && ok_valeur){  // Tous est OK
           
           ob.setId(IDObjet); // On attribut le num de l'objet
           IDObjet++;
           data.add(ob);
           TP_TPGO_SAC_A_DOS.listObjet.add(ob);
           TP_TPGO_SAC_A_DOS.nbr_objet_ajoute ++ ; // On inc de 1 le nombre d'objet rajoutés
           poid_objet.setText(null);
           valuer_objet.setText(null);
       }
     }else
     {
         objet_ajoutes=true;
         afficheMessageErreur(" Vous ne pouvez plus rajouter d'objet \n Vous avez atteint le nombre limite d'objet");
     }
    }
 
 @FXML private void ClickAjouterObjetKey (KeyEvent event){
     
   if(event.getCode()==KeyCode.ENTER) 
    {
     objet_ajoutes=false;
     if(TP_TPGO_SAC_A_DOS.nbr_objet_ajoute < TP_TPGO_SAC_A_DOS.nbr_objet) {
          
        Objet ob = new Objet();
        boolean ok_poid=false,ok_valeur=false;
        boolean Verif_poid = TextIsInteger(poid_objet.getText());
        if(Verif_poid)
        {
            int poid = Integer.parseInt(poid_objet.getText());
            if (poid >=0)
            {
                ob.setPoid(poid);
                ok_poid=true;
            }
            else{
                afficheMessageErreur("Le poid de l'objet doit être un entier positif !!");
            }
        }else
        {
            afficheMessageErreur(" Le poid de l'objet doit être un entier !! ");
        }
        
        boolean verif_valeur = TextIsInteger(valuer_objet.getText());
        
        if(verif_valeur)
        {
            int valeur = Integer.parseInt(valuer_objet.getText());
            if (valeur>=0) {
                ob.setGain(valeur);
                ok_valeur = true;
            }
            else{
                afficheMessageErreur(" La valeur attribuée à l'objet doit être positive !!");
            }
        }else {
            afficheMessageErreur("La valeur de l'objet doit être un entier !!");
        }
        
       if (ok_poid && ok_valeur){  // Tous est OK
           
           ob.setId(IDObjet); // On attribut le num de l'objet
           IDObjet++;
           data.add(ob);
           TP_TPGO_SAC_A_DOS.listObjet.add(ob);
           TP_TPGO_SAC_A_DOS.nbr_objet_ajoute ++ ; // On inc de 1 le nombre d'objet rajoutés
           poid_objet.setText(null);
           valuer_objet.setText(null);
       }
     }else
     {
         objet_ajoutes=true;
         afficheMessageErreur(" Vous ne pouvez plus rajouter d'objet \n Vous avez atteint le nombre limite d'objet");
     }
   }
          
    }
      
      
 @FXML   private void ClickAfficherResultat(ActionEvent event) throws IOException, FileNotFoundException, DocumentException
    {
        if(contenace_saisie && nbr_max_objet_saisie && (TP_TPGO_SAC_A_DOS.nbr_objet_ajoute== TP_TPGO_SAC_A_DOS.nbr_objet)) // On a remplie les champs
        {
          // On crée le tableau à 2 dimension des Pij
          int[][] Tableau = traitement.remplirMatrice(TP_TPGO_SAC_A_DOS.listObjet,TP_TPGO_SAC_A_DOS.max_poid);
            ArrayList<Objet> Objets = new ArrayList<Objet>();
            Objets=traitement.recupererObjetPris(Tableau,TP_TPGO_SAC_A_DOS.listObjet, TP_TPGO_SAC_A_DOS.max_poid);
         
            // On crée l'Entete
        String[] entete = new String[TP_TPGO_SAC_A_DOS.max_poid+2];
        int i=0;
        while(i<TP_TPGO_SAC_A_DOS.max_poid+2)
                {
                    if(i==0)entete[i] = "Objet\\Poid";
                    else entete[i] = ""+(i-1);
                    i++;
                }
                // nom des colonnes
        // On sauvegarde la table article
        File f=Outil.OutilSavePDF.SaveFichierPDF("Sauvegarder les résultats ", Tableau, entete,Objets,"Solution du problèmes du Sac à Dos ","Gain",Tableau[TP_TPGO_SAC_A_DOS.nbr_objet][TP_TPGO_SAC_A_DOS.max_poid+1],traitement.poidOptimal);
        if (f!=null) Desktop.getDesktop().open(f);
        }else {
            
            if(!contenace_saisie) afficheMessageErreur(" Veuillez saisir la valeur de la contenance maximale !!");
            if(!nbr_max_objet_saisie) afficheMessageErreur(" Veuillez saisir le nobre d'objet !!");
            if(TP_TPGO_SAC_A_DOS.nbr_objet_ajoute != TP_TPGO_SAC_A_DOS.nbr_objet) afficheMessageErreur(" Veuillez remplir le tableau des objet avant de continuer !!");
           
            
        }
    }
    
 @FXML private void ClickSupprimerObjet(ActionEvent event) {
     
     Objet obj = (Objet) Objet_Table.getSelectionModel().getSelectedItem();
     data.removeAll(obj);
     // Màj les valeurs des ids des objets restant
     int id = obj.getId();
     ObservableList<Objet> newData = FXCollections.observableArrayList();
     Iterator<Objet> it =data.iterator();
     while(it.hasNext())
     {
         Objet ob = it.next();
         if(ob.getId()>id) ob.setId(ob.getId()-1);
         newData.add(ob);
     }
     data.clear();
     data.addAll(newData);
     TP_TPGO_SAC_A_DOS.listObjet = TransforerObservableList_To_ArrayList(data);
     if(TP_TPGO_SAC_A_DOS.nbr_objet_ajoute>0){
         TP_TPGO_SAC_A_DOS.nbr_objet_ajoute--;
         IDObjet--; // On diminu l'id car on a couriger les autres qui exister dans la list
     }
     
 }
 
 private ArrayList<Objet> TransforerObservableList_To_ArrayList(ObservableList<Objet> list){
     
     ArrayList<Objet> NL = new ArrayList<Objet>();
     Iterator<Objet> it = list.iterator();
     while(it.hasNext())
     {
         Objet ob = it.next();
         NL.add(ob);
     }
     
     return NL;
 }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
