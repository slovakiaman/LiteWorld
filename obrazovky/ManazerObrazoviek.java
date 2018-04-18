/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.obrazovky;

import sk.uniza.fri.liteworld.nezaradene.Obrazovka;
import sk.uniza.fri.liteworld.obrazovky.AbstractObrazovka;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 *
 * @author Marek
 */
public class ManazerObrazoviek {

    private static ManazerObrazoviek instancia;
    private Game game;

    private ManazerObrazoviek() {
    }

    public static ManazerObrazoviek getInstancia() {
        if (ManazerObrazoviek.instancia == null) {
            ManazerObrazoviek.instancia = new ManazerObrazoviek();
        }
        return ManazerObrazoviek.instancia;
    }

    public void inicializuj(Game game) {
        this.game = game;
    }

    public void zobrazObrazovku(Obrazovka obrazovka) {
        // Get current screen to dispose it
        Screen aktualnaObrazovka = this.game.getScreen();

        // Show new screen
        AbstractObrazovka novaObrazovka = obrazovka.getObrazovka();
        novaObrazovka.buildStage();  //
        this.game.setScreen(novaObrazovka);

        // Dispose previous screen
        if (aktualnaObrazovka != null) {
            aktualnaObrazovka.dispose();
        }
    }

}
