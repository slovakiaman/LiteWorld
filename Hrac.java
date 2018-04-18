/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.liteworld;

import sk.uniza.fri.liteworld.nezaradene.Smer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import sk.uniza.fri.liteworld.veci.INepozivatelne;
import sk.uniza.fri.liteworld.veci.IVec;
import sk.uniza.fri.liteworld.veci.nepozivatelne.IArmor;
import sk.uniza.fri.liteworld.veci.nepozivatelne.IZbrane;
import sk.uniza.fri.liteworld.veci.nepozivatelne.armor.Brnenie;
import sk.uniza.fri.liteworld.veci.nepozivatelne.armor.Helma;
import sk.uniza.fri.liteworld.veci.nepozivatelne.armor.Nohavice;
import sk.uniza.fri.liteworld.veci.nepozivatelne.armor.Topanky;
import sk.uniza.fri.liteworld.nezaradene.Staty;
import sk.uniza.fri.liteworld.obrazovky.Inventar;


/**
 *
 * @author Marek
 */
public class Hrac extends Sprite {
    private static final Sprite DOLE = new Sprite(new Texture("hrac/dole.png"));
    private static final Sprite HORE = new Sprite(new Texture("hrac/hore.png"));
    private static final Sprite LAVE = new Sprite(new Texture("hrac/dolava.png"));
    private static final Sprite PRAVE = new Sprite(new Texture("hrac/doprava.png"));
    
    private Smer otocenie;
    private float x;
    private float y;
    private final String meno;
    private int level;
    private int hp;
    private Staty staty;
    private Inventar inventar;
    private Helma helma;
    private Brnenie brnenie;
    private Nohavice rukavice;
    private Topanky topanky;
    private IZbrane zbran;

    
    public Hrac(Sprite sprite, String meno) {
        super(sprite);
        this.otocenie = Smer.DODOLA;
        this.x = 3 * 32;
        this.y = 60 * 32;
        this.update(0);
        this.meno = meno;
        this.hp = Staty.MAXHP.getHodnota();
        this.level = 1;
        this.inventar = new Inventar();
        //TODO vyrieĹˇiĹĄ prepojenie s inventĂˇrom na equipovanie vecĂ­

    }
    
    public void draw(SpriteBatch spriteBatch) {
        this.update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }
    
    public void update(float delta) {
        setPosition(this.x, this.y);
    }
    
    public void posunSa(TiledMapTileLayer kolizie, Smer smer) {
        this.update(0);
        this.otocenie = smer;
        //this.otocSa();  prepisuje súradnice
        float sirkaBloku = kolizie.getTileWidth();
        float vyskaBloku = kolizie.getTileHeight();
        float vyskaHrac = getHeight();
        int podmienka1;
        int podmienka2;
        switch (smer) {
            case VPRAVO:
                set(PRAVE);
                podmienka1 = (int)((this.x + vyskaHrac) / sirkaBloku);
                podmienka2 = (int)((this.y + vyskaHrac / 4) / vyskaBloku);
                if (kolizie.getCell(podmienka1, podmienka2) == null) {
                    this.x += 6;
                }
                
                break;
            case VLAVO:
                set(LAVE);
                podmienka1 = (int)(this.x / sirkaBloku);
                podmienka2 = (int)((this.y + vyskaHrac / 4) / vyskaBloku);
                if (kolizie.getCell(podmienka1, podmienka2) == null) {
                    this.x -= 6;
                }
                break;
            case DOHORA:
                set(HORE);
                podmienka1 = (int)((this.x + vyskaHrac / 2) / sirkaBloku);
                podmienka2 = (int)((this.y + vyskaHrac / 2) / vyskaBloku);
                if (kolizie.getCell(podmienka1, podmienka2) == null) {
                    this.y += 6;
                }
                break;
            case DODOLA:
                set(DOLE);
                podmienka1 = (int)((this.x + vyskaHrac / 2) / sirkaBloku);
                podmienka2 = (int)(this.y / vyskaBloku);
                if (kolizie.getCell(podmienka1, podmienka2) == null) {
                    this.y -= 6;
                }
        }
        this.update(0);
    }
    
    public Smer getOtocenie() {
        return this.otocenie;
    }

    private void otocSa() {
        switch (this.otocenie) {
            case VPRAVO:
                set(Hrac.PRAVE);
                return;
            case VLAVO:
                set(Hrac.LAVE);
                return;
            case DOHORA:
                set(Hrac.HORE);
                return;
            case DODOLA:
                set(Hrac.DOLE);
        }
    }
    @Override
    public float getX() {
        return this.x;
    }
    @Override
    public float getY() {
        return this.y;
    }
    
    public boolean zije() {
        return this.hp > 0;
    }
    
    public void pouzi(IVec vec) {
        if (vec != null) {
            vec.pouzi(this);
        }
    }

    public void vratHP(int hp) {
        if (Staty.MAXHP.getHodnota() > this.hp + hp) {
            this.hp = Staty.MAXHP.getHodnota();
        } else {
            this.hp += hp;
        }
    }

    public void odstran(IVec vec) {
        this.inventar.odstran(vec);
    }

    public boolean dajSiNaSeba(INepozivatelne vec) {
        if (vec instanceof IArmor) {
            if (vec instanceof Nohavice) {
                if (this.rukavice != null) {
                    this.dajSiZoSeba(this.rukavice);
                }
                this.rukavice = (Nohavice)vec;
                return true;
            }
            if (vec instanceof Brnenie) {
                if (this.brnenie != null) {
                    this.dajSiZoSeba(this.brnenie);
                }
                this.brnenie = (Brnenie)vec;
                return true;
            }
            if (vec instanceof Helma) {
                if (this.helma != null) {
                    this.dajSiZoSeba(this.helma);
                }
                this.helma = (Helma)vec;
                return true;
            }
            if (vec instanceof Topanky) {
                if (this.brnenie != null) {
                    this.dajSiZoSeba(this.topanky);
                }
                this.topanky = (Topanky)vec;
                return true;
            }
        }
        if (vec instanceof IZbrane) {
            if (this.zbran != null) {
                this.dajSiZoSeba(this.zbran);
            }
            this.zbran = (IZbrane)vec;
            return true;
        }
        return false;
    }

    public boolean dajSiZoSeba(INepozivatelne vec) {
        if (vec instanceof IArmor) {
            if (vec instanceof Nohavice) {
                if (this.rukavice == null) {
                    System.out.println("NemĂˇĹˇ si ÄŤo daĹĄ dole.");
                } else {
                    this.rukavice.dajSiZoSeba(this);
                    this.rukavice = null;
                }
                return true;
            }
            if (vec instanceof Brnenie) {
                if (this.brnenie == null) {
                    System.out.println("NemĂˇĹˇ si ÄŤo daĹĄ dole.");
                } else {
                    this.brnenie.dajSiZoSeba(this);
                    this.brnenie = null;
                }
                return true;
            }
            if (vec instanceof Helma) {
                if (this.helma == null) {
                    System.out.println("NemĂˇĹˇ si ÄŤo daĹĄ dole.");
                } else {
                    this.helma.dajSiZoSeba(this);
                    this.helma = null;
                }
                return true;
            }
            if (vec instanceof Topanky) {
                if (this.brnenie == null) {
                    System.out.println("NemĂˇĹˇ si ÄŤo daĹĄ dole.");
                } else {
                    this.topanky.dajSiZoSeba(this);
                    this.topanky = null;
                }
                return true;
            }
        }
        if (vec instanceof IZbrane) {
            if (this.zbran == null) {
                System.out.println("NemĂˇĹˇ na sebe zbraĹ");
            } else {
                vec.dajSiZoSeba(this);
                this.zbran = null;
            }
            return true;
        }
        return false;
    }

    public void zvysStat(Staty stat, int kolko) {
        stat.zvysSa(kolko);
    }
    
    public void zoberVec(IVec vec) {
        this.inventar.pridaj(vec);
    }

}
