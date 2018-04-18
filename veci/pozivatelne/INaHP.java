/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.veci.pozivatelne;

import sk.uniza.fri.liteworld.Hrac;
import sk.uniza.fri.liteworld.veci.IPozivatelne;

/**
 *
 * @author Marek
 */
public interface INaHP extends IPozivatelne {
    void vratHP(Hrac hrac);
}
