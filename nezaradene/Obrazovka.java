/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.nezaradene;

import sk.uniza.fri.liteworld.obrazovky.AbstractObrazovka;
import sk.uniza.fri.liteworld.obrazovky.CombatObrazovka;
import sk.uniza.fri.liteworld.obrazovky.HlavneMenu;
import sk.uniza.fri.liteworld.obrazovky.Hra;
import sk.uniza.fri.liteworld.obrazovky.Inventar;

/**
 *
 * @author Marek
 */
public enum Obrazovka {
    MENU {
        public AbstractObrazovka getObrazovka() {
            return new HlavneMenu();
        }
    },
    COMBAT {
        public AbstractObrazovka getObrazovka() {
            return new CombatObrazovka();
        }
    },
    INVENTAR {
        public AbstractObrazovka getObrazovka() {
            return new Inventar();
        }
    },
    HRA {
        public AbstractObrazovka getObrazovka() {
            return new Hra();
        }
    };

    public abstract AbstractObrazovka getObrazovka();
}
