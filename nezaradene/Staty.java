/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.nezaradene;

/**
 *
 * @author Marek
 */
public enum Staty {
    MAXHP(100),
    DEX(5),
    STR(5);
    
    private int hodnota;

    Staty(int hodnota) {
        this.hodnota = hodnota;
    }
    
    public int getHodnota() {
        return this.hodnota;
    }
    
    public void zvysSa(int oKolko) {
        this.hodnota += oKolko;
    }
    
    
}
