/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.veci.nepozivatelne.zbrane.naBlizko;

import sk.uniza.fri.liteworld.Hrac;
import sk.uniza.fri.liteworld.veci.nepozivatelne.zbrane.INaBlizko;

/**
 *
 * @author Marek
 */
public class Mec implements INaBlizko {
    private final int damage;
    private final int speed;
    private boolean pouzivany;

    public Mec() {
        this.damage = 5;
        this.speed = 5;
        //bude random
        this.pouzivany = false;
    }
    
    @Override
    public void dajSiNaSeba(Hrac hrac) {
        this.pouzivany = hrac.dajSiNaSeba(this);
    }

    @Override
    public void dajSiZoSeba(Hrac hrac) {
        this.pouzivany = !hrac.dajSiNaSeba(this);
    }

    @Override
    public void pouzi(Hrac hrac) {
        System.out.println("Mec, damage: " + this.damage + ", speed: " + this.speed);
    }

    @Override
    public void odstran(Hrac hrac) {
        hrac.odstran(this);
    }

    @Override
    public boolean pouzivany(Hrac hrac) {
        return this.pouzivany;
    }

    @Override
    public int getRychlost() {
        return this.speed;
    }

    @Override
    public int getUtok() {
        return this.damage;
    }
    
}
