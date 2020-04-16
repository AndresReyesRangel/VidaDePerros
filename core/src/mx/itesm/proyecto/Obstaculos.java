package mx.itesm.proyecto;

import com.badlogic.gdx.graphics.Texture;

public class Obstaculos extends Objeto {

    private float vy = 100;

    public Obstaculos(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public void setX(float dx){
        sprite.setX(dx);
    }

    public float getX(){
        return sprite.getX();
    }

    public void mover(float dt){
        float dy = vy * dt;
        sprite.setY(sprite.getY() - dy);
    }
}
