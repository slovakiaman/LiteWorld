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
public class Pistol9mm implements INaDialku {
    private final int damage;
    private final int speed;
    private final int range;
    private int naboje;
    private boolean pouzivany;
    
    public Pistol9mm() {
        this.damage = 10;
        this.speed = 8;
        this.range = 5;
        this.naboje = 15;
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
        System.out.println("Pistol: damage: " + this.damage + " range: " + this.range + " speed: " + this.speed);
    }

    @Override
    public void odstran(Hrac hrac) {
        hrac.odstran(this);
    }

    @Override
    public boolean pouzivany(Hrac hrac) {
        return this.pouzivany;
    }
}
