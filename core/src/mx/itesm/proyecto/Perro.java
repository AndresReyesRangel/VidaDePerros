package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.TextureRegion;

public class Perro extends Objeto {

    //Animacion
    private Animation animacion;
    private float timerAnimacion; //frames en tiempos definidos
    //private float x,y;

    private EstadoMovimiento estado = EstadoMovimiento.CAMINANDO;    // QUIETO, CAMINANDO

    //Método constructor del sprite del perro
    public Perro(Texture textura, float x, float y) {
        super(textura, x, y);
        TextureRegion region = new TextureRegion(textura);
        TextureRegion [][] texturaPerro = region.split(96, 280);

        animacion = new Animation(0.25f, texturaPerro[0][0], texturaPerro[0][1],
                                    texturaPerro[0][2], texturaPerro[0][3],
                                    texturaPerro[0][4], texturaPerro[0][5]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;
        System.out.println("Aqui se hace la animacion de la imagen");


        sprite = new Sprite(texturaPerro[0][0]);

        //this.x = x;
        //this.y = y;
    }

    public void setEstado(EstadoMovimiento estado) {
        this.estado = estado;
    }

    public void render(SpriteBatch batch) {
        if (estado==EstadoMovimiento.QUIETO) {
            sprite.draw(batch);
        } else {
            timerAnimacion += Gdx.graphics.getDeltaTime();
            TextureRegion region = (TextureRegion)animacion.getKeyFrame(timerAnimacion);
            batch.draw(region, sprite.getX(),sprite.getY());
        }
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

    public enum EstadoMovimiento {
        QUIETO,
        CAMINANDO
    }
}
