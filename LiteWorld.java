package sk.uniza.fri.liteworld;

import sk.uniza.fri.liteworld.nezaradene.Obrazovka;
import sk.uniza.fri.liteworld.obrazovky.ManazerObrazoviek;
import com.badlogic.gdx.Game;

/**
 *
 * @author Marek
 */
public class LiteWorld extends Game {
    
    @Override
    public void create() {
        ManazerObrazoviek.getInstancia().inicializuj(this);
        ManazerObrazoviek.getInstancia().zobrazObrazovku(Obrazovka.MENU);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        
    }
}