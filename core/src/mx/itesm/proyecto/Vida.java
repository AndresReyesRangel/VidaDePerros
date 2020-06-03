package mx.itesm.proyecto;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.omg.PortableInterceptor.ObjectReferenceFactoryHelper;

public class Vida {

    private int contVidas;
    private float x;
    private float y;

    private Texto texto; // Desplegar el texto "Puntos: 250"

    //Constructor
    public Vida(float x, float y){
        this.x = x;
        this.y = y;
        texto = new Texto("Fuentes/minkler.fnt"); //Fuente
    }

    public void marcarVida(int cont){
        this.contVidas = cont;
    }

    public void render(SpriteBatch batch){
        String mensaje = " = "+ contVidas;
        texto.render(batch, mensaje, x, y);
    }

    public int getContVidas(){

        return contVidas;
    }

}
