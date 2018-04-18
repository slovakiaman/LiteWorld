/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.obrazovky;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import sk.uniza.fri.liteworld.Hrac;
import sk.uniza.fri.liteworld.npc.Nepriatel;

/**
 *
 * @author Marek
 */
public class CombatObrazovka extends AbstractObrazovka {

    private static Hrac hrac;
    private static Nepriatel nepriatel;
    private static boolean ideHrac;
    
    public CombatObrazovka(Hra hra, final Nepriatel nepriatel) {
        hrac = hra.getHrac();
        float povX = hrac.getX();
        float povY = hrac.getY();
        hrac.setPosition(100, 100);
        
        
        ImageButton buttonUtoc = UIPrvky.vytvorButton(new Texture("buttons/btn_play.png"));
        addActor(buttonUtoc);
        buttonUtoc.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hrac.utoc(nepriatel);
                ideHrac = false;
                return false;
            }
        });/*
        ImageButton buttonNieco = UIPrvky.vytvorButton(new Texture("buttons/btn_play.png"));
        addActor(buttonUtoc);
        buttonUtoc.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hrac.utoc(nepriatel);
                return false;
            }
        });*/
        
        //true = hrac , false = nepriatel
        ideHrac = hrac.getRychlost() >= nepriatel.getRychlost();
        int rychlostHrac = hrac.getRychlost();
        int rychlostNepriatel = nepriatel.getRychlost();
        while (true) {
            //this.buildStage();
            hrac.draw(hra.getBatch());
            nepriatel.vykresliSa(hra.getBatch());
            buttonUtoc.setVisible(ideHrac);
            if (ideHrac) {
                //vyriešiť aby hráč najskôr musel spraviť interakciu, až potom sa to preplo -- DONE
                if (nepriatel.getHp() < 1) {
                    System.out.println("Nepriatel umrel");
                    break;
                }
            } else {
                nepriatel.interakcia(hrac);
                if (hrac.getHp() < 1) {
                    System.out.println("Umrel si");
                    break;
                }
                ideHrac = true;
            }
            //vypocitavanie kto ma ist na tah  --- PRIDAŤ
            //to treba lepšie premyslieť
            
            
        }
        
    }
    
    @Override
    public void buildStage() {
        
    }
    
}
