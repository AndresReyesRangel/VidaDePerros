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
    protected Animation animacion;
    protected float timerAnimacion; //frames en tiempos definidos

    public TextureRegion region;



    //Método constructor del sprite del perro
    public Perro(Texture textura, float x, float y) {
        super(textura, x, y);
        TextureRegion region = new TextureRegion(textura);
        TextureRegion [][] texturaPerro = region.split(96, 277);

        animacion = new Animation(0.07f, texturaPerro[0][0], texturaPerro[0][1],
                                    texturaPerro[0][2], texturaPerro[0][3],
                                    texturaPerro[0][4], texturaPerro[0][5]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;



        sprite = new Sprite(texturaPerro[0][0]);
        sprite.setPosition(x,y);


    }



    public void renderP(SpriteBatch batch,float x, float y) {

        timerAnimacion += Gdx.graphics.getDeltaTime();
        region = (TextureRegion) animacion.getKeyFrame(timerAnimacion);
        batch.draw(region, sprite.getX(), sprite.getY());

    }



    //Método para determinar la posición del sprite del perro dentro del eje de las X
    public void setX(float dx){
        sprite.setX(dx);
    }

    //Método para conocer la posición del perro dentro del eje de las X.
    public float getX(){
        return sprite.getX();
    }

    public void setY(float dy){
        sprite.setY(dy);
    }

    public float getY(){
        return sprite.getY();
    }

}
