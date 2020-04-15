package mx.itesm.proyecto;

import com.badlogic.gdx.graphics.Texture;

public class Perro extends Objeto {
    public Perro(Texture textura, float x, float y) {

        super(textura, x, y);
    }

    public void mover(float dx){

        sprite.setX(sprite.getX()+dx);
    }

    public void setX(float dx){
        sprite.setX(dx);
    }

    public float getX(){
        return sprite.getX();
    }
}
