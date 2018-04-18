/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.obrazovky;

import sk.uniza.fri.liteworld.nezaradene.Obrazovka;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;

/**
 *
 * @author Marek
 */
public class HlavneMenu extends AbstractObrazovka {

    private Texture pozadie;
    private Texture hrat;
    private Texture exit;

    public HlavneMenu() {
        super();
        this.pozadie = new Texture("buttons/main_menu_bg.png");
        this.hrat = new Texture("buttons/btn_play.png");
        this.exit = new Texture("buttons/btn_exit.png");
    }

    @Override
    public void buildStage() {

        // Adding actors
        Image bg = new Image(this.pozadie);
        addActor(bg);

        ImageButton buttonHrat = UIPrvky.vytvorButton(this.hrat);
        buttonHrat.setPosition(getWidth() / 2, 120.f, Align.center);
        addActor(buttonHrat);

        ImageButton buttonExit = UIPrvky.vytvorButton(this.exit);
        buttonExit.setPosition(getWidth() / 2, 60.f, Align.center);
        addActor(buttonExit);

        //nastavenie listenerov
        buttonHrat.addListener(UIPrvky.listenerNaPreChodObrazovky(Obrazovka.HRA));
        buttonExit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return false;
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        this.pozadie.dispose();
        this.hrat.dispose();
        this.exit.dispose();
    }

}
