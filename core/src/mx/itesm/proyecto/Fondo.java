package mx.itesm.proyecto;

import com.badlogic.gdx.graphics.Texture;

public class Fondo extends Objeto {

    // Velocidad
    private int tiempoJuego;
    //private float vy =  // pixeles por segundo

    //Constructor del fondo
    public Fondo(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    //Método get que utilizamos para conocer la posición del fondo en el ejer Y

    public float getY(){
        return sprite.getY();
    }

    //Método set que utilizamos para poner el fondo en una cordenada específica en y
    public void setY(float y){
        sprite.setY(y);
    }

    //Método para mover el fondo utilizando el delta del método render para irse moviendo
    public void mover(float dt){
        float dy = tiempoJuego * dt;
        sprite.setY(sprite.getY() - dy);

    }

    //Método para actualizar el tiempo dentro de esta clase
    public void actualizarTiempo(int tiempo) {
       this.tiempoJuego = tiempo;
    }

}
