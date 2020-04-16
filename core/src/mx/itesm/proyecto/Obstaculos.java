package mx.itesm.proyecto;

import com.badlogic.gdx.graphics.Texture;

public class Obstaculos extends Objeto {

    public Obstaculos(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public void setX(float dx){
        sprite.setX(dx);
    }

    public float getX(){
        return sprite.getX();
    }
}
