/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.obrazovky;

import sk.uniza.fri.liteworld.nezaradene.Obrazovka;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Marek
 */
public class UIPrvky {

    public static ImageButton vytvorButton(Texture textura) {
        return new ImageButton(new TextureRegionDrawable(new TextureRegion(textura)));
    }

    public static InputListener vytvorListener(final Obrazovka cielovaObrazovka) {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ManazerObrazoviek.getInstancia().zobrazObrazovku(cielovaObrazovka);
                return false;
            }
        };
    }
    
    //tu by bolo fajn vymyslieť ten dialóg a okno k nemu
}
