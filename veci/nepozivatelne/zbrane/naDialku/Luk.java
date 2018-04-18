/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.veci.nepozivatelne.zbrane.naDialku;

import sk.uniza.fri.liteworld.Hrac;
import sk.uniza.fri.liteworld.veci.nepozivatelne.zbrane.INaDialku;

/**
 *
 * @author Marek
 */
public class Luk implements INaDialku {
    private final int damage;
    private final int speed;
    private final int range;
    private int naboje;
    private int kapacita;
    private boolean pouzivany;
    
    public Luk() {
        this.damage = 14;
        this.speed = 5;
        this.range = 7;
        this.kapacita = 10;
        this.naboje = this.kapacita;
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
        System.out.println("Luk: damage: " + this.damage + " range: " + this.range + " speed: " + this.speed);
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

    @Override
    public int getDosah() {
        return this.range;
    }

    @Override
    public int getNaboje() {
        return this.naboje;
    }

    @Override
    public int getMaximumNabojov() {
        return this.kapacita;
    }
}
