package mx.itesm.proyecto;

import com.badlogic.gdx.graphics.Texture;

public class Fondo extends Objeto {

    // Velocidad
    private int tiempoJuego;
    //private float vy =  // pixeles por segundo


    public Fondo(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public float getY(){
        return sprite.getY();
    }

    public void setY(float y){
        sprite.setY(y);
    }

    public void mover(float dt){
        float dy = tiempoJuego * dt;
        sprite.setY(sprite.getY() - dy);

    }

    public void actualizarTiempo(int tiempo) {
       this.tiempoJuego = tiempo;
    }

}
