package mx.itesm.proyecto;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Objeto {

    public Texture textura;

    protected Sprite sprite;


    public Objeto(Texture textura, float x, float y){
        sprite = new Sprite(textura);
        sprite.setPosition(x,y);
        this.textura = textura;
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }


}
