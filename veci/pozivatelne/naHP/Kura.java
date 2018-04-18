/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.veci.pozivatelne.naHP;

import sk.uniza.fri.liteworld.Hrac;
import sk.uniza.fri.liteworld.veci.pozivatelne.INaHP;

/**
 *
 * @author Marek
 */
public class Kura implements INaHP {
    private final int hp;

    public Kura() {
        this.hp = 50;
    }
    
    @Override
    public void vratHP(Hrac hrac) {
        hrac.vratHP(this.hp);
    }

    @Override
    public void skonzumuj(Hrac hrac) {
        this.vratHP(hrac);
    }

    @Override
    public void pouzi(Hrac hrac) {
        System.out.println("Kura: vrati: " + this.hp + " hp");
    }

    @Override
    public void odstran(Hrac hrac) {
        hrac.odstran(this);
    }
}
