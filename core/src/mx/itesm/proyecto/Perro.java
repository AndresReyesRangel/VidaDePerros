package mx.itesm.proyecto;

import com.badlogic.gdx.graphics.Texture;

public class Perro extends Objeto {

    //Método constructor del sprite del perro
    public Perro(Texture textura, float x, float y) {

        super(textura, x, y);
    }

    //Método para mover el perro una cantidad determinada de pasos
    public void mover(float dx){

        sprite.setX(sprite.getX()+dx);
    }

    //Método para determinar la posición del sprite del perro dentro del eje de las X
    public void setX(float dx){
        sprite.setX(dx);
    }

    //Método para conocer la posición del perro dentro del eje de las X.
    public float getX(){
        return sprite.getX();
    }
}
