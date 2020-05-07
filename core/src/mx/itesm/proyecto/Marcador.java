package mx.itesm.proyecto;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Marcador {

    private int cont;
    private float x;
    private float y;

    private Texto texto; // Desplegar el texto "Puntos: 250"

    //Constructor
    public Marcador(float x, float y){
        this.x = x;
        this.y = y;
        texto = new Texto("Fuentes/minkler.fnt"); //Fuente


    }


    //Agrega puntos
    public void marcar(int cont){
        this.cont = cont;
    }


    public void render(SpriteBatch batch){
        String mensaje = "Puntos: "+ cont;
        texto.render(batch, mensaje, x, y);
    }

    public void render2(SpriteBatch batch){
        String mensaje = ""+ cont;
        texto.render(batch, mensaje, x, y);
    }

    public int getCont() {
        return cont;
    }

}
