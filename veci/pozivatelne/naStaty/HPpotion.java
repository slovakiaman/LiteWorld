/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.veci.pozivatelne.naStaty;

import sk.uniza.fri.liteworld.Hrac;
import sk.uniza.fri.liteworld.nezaradene.Staty;
import sk.uniza.fri.liteworld.veci.pozivatelne.INaStaty;

/**
 *
 * @author Marek
 */
public class HPpotion implements INaStaty {
    private final int kolko;
    
    public HPpotion() {
        this.kolko = 1;
        //random
    }
    
    @Override
    public void zvysStat(Hrac hrac) {
        hrac.zvysStat(Staty.MAXHP, this.kolko);
    }

    @Override
    public void skonzumuj(Hrac hrac) {
        this.zvysStat(hrac);
    }

    @Override
    public void pouzi(Hrac hrac) {
        System.out.println("MaxHPpotion: zvysi: " + Staty.MAXHP.toString() + " o " + this.kolko);
    }

    @Override
    public void odstran(Hrac hrac) {
        hrac.odstran(this);
    }
}
