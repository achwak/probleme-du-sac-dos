/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Reika Lovely
 */
public class Objet {
    int id;
    int poid;
    int gain;
    boolean pris;

    public Objet(int id, int poid, int gain, boolean pris) {
        this.id = id;
        this.poid = poid;
        this.gain = gain;
        this.pris = pris;
    }
     
    public Objet() {
        this.pris = false; // par default il est non pris
    }

    public boolean isPris() {
        return pris;
    }

    public void setPris(boolean pris) {
        this.pris = pris;
    }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public int getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }
    
    
}
