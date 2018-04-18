/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.veci;

import sk.uniza.fri.liteworld.Hrac;



/**
 *
 * @author Marek
 */
public interface INepozivatelne extends IVec {
    void dajSiNaSeba(Hrac hrac);
    void dajSiZoSeba(Hrac hrac);
    boolean pouzivany(Hrac hrac);  //tj. má ho hráč na sebe
}
