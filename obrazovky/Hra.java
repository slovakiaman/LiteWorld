/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld.obrazovky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import sk.uniza.fri.liteworld.Hrac;
import sk.uniza.fri.liteworld.nezaradene.Smer;

/**
 *
 * @author Marek
 */
public class Hra extends AbstractObrazovka {

    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Hrac hrac;
    private float cas;
    private TiledMapTileLayer strechy;
    private TiledMapTileLayer ludia;
    private TiledMapTileLayer vypln;
    private TiledMapTileLayer mosty;
    private TiledMapTileLayer kolizie;
    private TiledMapTileLayer zaklady;
    public Hra() {
        this.cas = 0;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.interakcia();
        this.nastavKameru();
        //this.renderer.render();
        this.vykresli();
        
    }

    @Override
    public void resize(int width, int height
    ) {
        this.camera.viewportWidth = width;
        this.camera.viewportHeight = height;
        this.camera.update();
    }

    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        this.mapa = loader.load("maps/mapa.tmx");
        //rozkúskované vrstvy
        this.zaklady = (TiledMapTileLayer)this.mapa.getLayers().get(0);
        this.kolizie = (TiledMapTileLayer)this.mapa.getLayers().get(1);
        this.mosty = (TiledMapTileLayer)this.mapa.getLayers().get(2);
        this.vypln = (TiledMapTileLayer)this.mapa.getLayers().get(3);
        this.ludia = (TiledMapTileLayer)this.mapa.getLayers().get(4);
        this.strechy = (TiledMapTileLayer)this.mapa.getLayers().get(5);

        this.renderer = new OrthogonalTiledMapRenderer(this.mapa);
        this.camera = new OrthographicCamera();
        this.renderer.setView(this.camera); 
        this.hrac = new Hrac(new Sprite(new Texture("hrac/dole.png")), "JANO");
        this.hrac.setPosition(3 * 32, 60 * 32);
    }
    
    private void interakcia() {
        //TODO checknúť lepší spôsob brania inputu
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.hrac.posunSa(this.kolizie, Smer.VPRAVO);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.hrac.posunSa(this.kolizie, Smer.VLAVO);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.hrac.posunSa(this.kolizie, Smer.DOHORA);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.hrac.posunSa(this.kolizie, Smer.DODOLA);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            float sirkaBloku = this.kolizie.getTileWidth();
            float vyskaBloku = this.kolizie.getTileHeight();
            float hracX = this.hrac.getX();
            float hracY = this.hrac.getY();
            float vyskaHrac = this.hrac.getHeight();
            int podmienka1 = 0;
            int podmienka2 = 0;
            switch (this.hrac.getOtocenie()) {
                case VLAVO:
                    podmienka1 = (int)((hracX + vyskaHrac / 2) / sirkaBloku);
                    podmienka2 = (int)((hracY + vyskaHrac / 2) / vyskaBloku);
                    break;
                case VPRAVO:
                    podmienka1 = (int)((hracX + vyskaHrac) / sirkaBloku);
                    podmienka2 = (int)((hracY + vyskaHrac / 4) / vyskaBloku);
                    break;
                case DOHORA:
                    podmienka1 = (int)((hracX + vyskaHrac / 2) / sirkaBloku);
                    podmienka2 = (int)((hracY + vyskaHrac / 2) / vyskaBloku);
                    break;
                case DODOLA:
                    podmienka1 = (int)((hracX + vyskaHrac / 2) / sirkaBloku);
                    podmienka2 = (int)(hracY / vyskaBloku);
            }
            if (this.ludia.getCell(podmienka1, podmienka2) != null) {
                if (this.ludia.getCell(podmienka1, podmienka2).getTile().getProperties().containsKey("interactable")) {
                    System.out.println(this.ludia.getCell(podmienka1, podmienka2).getTile().getProperties().get("text"));
                }
            }
        }
    }

    private void nastavKameru() {
        this.camera.position.set(this.hrac.getX() + this.hrac.getWidth() / 2, this.hrac.getY() + this.hrac.getHeight() / 2, 0);
        this.camera.update();
        this.resize(32 * 14, 32 * 12);
        this.renderer.setView(this.camera);
    }

    private void vykresli() {
        this.renderer.getBatch().begin();
        this.renderer.renderTileLayer(this.zaklady);
        this.renderer.renderTileLayer(this.kolizie);
        this.renderer.renderTileLayer(this.mosty);
        this.hrac.draw(this.renderer.getBatch());
        this.renderer.renderTileLayer(this.vypln);
        this.renderer.renderTileLayer(this.ludia);
        if (this.strechy.getCell((int)((this.hrac.getX() + this.hrac.getHeight() / 2) / this.strechy.getTileWidth()), (int)((this.hrac.getY() + this.hrac.getHeight() / 2 ) / this.strechy.getTileWidth())) == null) {
            this.renderer.renderTileLayer(this.strechy);
        }
        this.renderer.getBatch().end();
    }

    @Override
    public void buildStage() {
    }
}
//TODO pozrieť actor a čo to robí
//TODO pozrieť InputProcessor a ako to funguje