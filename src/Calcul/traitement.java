/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calcul;
import Entity.*;
import java.util.ArrayList;

/**
 *
 * @author Reika Lovely
 */
public class traitement {
    
    public static int poidOptimal = 0;
    
    public static int Max(int a,int b)
    {
        if(a>b) return a;
        else return b;
    }
    public static int[][] remplirMatrice(ArrayList<Objet> objets,int poidsMax)
    {
         int nbObjet=objets.size();
        int M[][]= new int[nbObjet+1][poidsMax+2] ;
       
        for(int j=0;j<=poidsMax;j++)
        {
            M[0][j]=0;
        }
        for(int i=0;i<=nbObjet;i++)
        {
            M[i][0]=i;
        }
        for(int i=0;i<nbObjet;i++)
        {
            M[i][1]=0;
        }
        for (int i=1;i<=nbObjet;i++)
            for(int j=2;j<=poidsMax+1;j++)
            {
                if (objets.get(i-1).getPoid()>j-1)
                {
                    M[i][j]=M[i-1][j];
                }
                else
                {
                    M[i][j]=Max(M[i-1][j],objets.get(i-1).getGain()+M[i-1][j-objets.get(i-1).getPoid()]);
                }
            }
        return M;  
    }
    public static ArrayList<Objet>  recupererObjetPris(int[][] M, ArrayList<Objet> objets,int poidsMax)
    {
        
        int i=objets.size(),j=poidsMax+1;
        
        while(M[i][j] == M[i][j-1] && (j>1) ){
            j--;
        }
        poidOptimal=j-1;
        while (j>1)
        {
            while ((i>0) && (M[i][j] == M[i-1][j]))
            {
                i--;
            }
            j=j-objets.get(i-1).getPoid();
            if (j>=1)
            {
                objets.get(i-1).setPris(true);
                System.out.println(" LLLLL " + objets.get(i-1).getId());
            }
            i--;
        }
        return objets;
    }
    
}
