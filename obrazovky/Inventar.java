/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.obrazovky;

import sk.uniza.fri.liteworld.veci.IVec;
import sk.uniza.fri.liteworld.veci.nepozivatelne.armor.Brnenie;
import sk.uniza.fri.liteworld.veci.nepozivatelne.zbrane.naBlizko.Mec;

/**
 *
 * @author Marek
 */
public class Inventar extends AbstractObrazovka {
    private IVec[] inventar;

    public Inventar() {
        this.inventar = new IVec[20];
        this.inventar[0] = new Mec();
        this.inventar[1] = new Brnenie();
    }

    public void odstran(IVec vec) {
        int i = 0;
        for (IVec aktualna : this.inventar) {
            if (aktualna == vec) {
                break;
            }
            i++;
        }  //prerobiť - vymaže prvý item toho typu a nie ten konkrétny - a možno ani nie
        this.inventar[i] = null;
    }
    
    public void pridaj(IVec vec) {
        if (!this.jePlny()) {
            int slot = this.najdiVolnySlot();
            this.inventar[slot] = vec;
        } else {
            System.out.println("Máš plný inventár");
        }
    }
    
    private int najdiVolnySlot() {
        int i = 0;
        for (IVec iVec : this.inventar) {
            if (iVec == null) {
                return i;
            } else {
                i++;
            }
        }
        return i;
    }
    
    private boolean jePlny() {
        for (IVec aktualna : this.inventar) {
            if (aktualna == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void buildStage() {
    }
    
    
}
