/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.npc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.Random;
import sk.uniza.fri.liteworld.Hrac;

/**
 *
 * @author Marek
 */
public /*abstract*/ class Nepriatel implements INpc {
    private int hp;
    private int damage;
    private int rychlost;
    private Texture textura;

    public Nepriatel(Texture textura, int hp, int damage, int rychlost) {
        this.hp = hp;
        this.damage = damage;
        this.rychlost = rychlost;
        this.textura = textura;
    }
    
    public void zautoc(Hrac hrac) {
        System.out.println("Hrac dostal poskodenie " + this.damage);
    }
    
    public int getRychlost() {
        return this.rychlost;
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public void dostanDamage(int kolko) {
        this.hp -= kolko;
    }
    
    
    public boolean zije() {
        return this.hp > 0;
    }

    @Override
    public void interakcia(Hrac hrac) {
        this.rozhodniSa(hrac);
    }
    
    private void rozhodniSa(Hrac hrac) {
        if (this.hp < 20) {
            Random rand = new Random();
            int nahoda = rand.nextInt(10);
            if (nahoda > 6) {
                this.obnovSiZdravie();
            }
        } else {
            this.zautoc(hrac);
        }
    }
    
    private void obnovSiZdravie() {
        this.hp += 20; 
        System.out.println("Nepriatel si obnovil zdravie");
    }
    
    public void vykresliSa(Batch batch) {
        batch.draw(this.textura, 200, 100);
    }
}
