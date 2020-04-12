package mx.itesm.proyecto;

import com.badlogic.gdx.graphics.Texture;

public class Fondo extends Objeto {

    // Velocidad
    private float vy = 360; // pixeles por segundo

    public Fondo(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public void mover(float dt){
        float dy = vy * dt;
        sprite.setY(sprite.getY() - dy);
    }
}
