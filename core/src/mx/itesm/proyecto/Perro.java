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
    protected Animation animacionS;
    protected float timerAnimacion; //frames en tiempos definidos
    protected float timerAnimacionS;


    public TextureRegion region;
    public TextureRegion regionS;



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


    public void renderP(SpriteBatch batch,float x, float y, PantallaJuego.estadoLomito lomito ) {



        if(lomito == PantallaJuego.estadoLomito.estampado){
            timerAnimacionS += Gdx.graphics.getDeltaTime();
            regionS = (TextureRegion) animacionS.getKeyFrame(timerAnimacionS);
            batch.draw(regionS, sprite.getX(), sprite.getY());

        }else{
            timerAnimacion += Gdx.graphics.getDeltaTime();
            region = (TextureRegion) animacion.getKeyFrame(timerAnimacion);
            batch.draw(region, sprite.getX(), sprite.getY());
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

    public void setY(float dy){
        sprite.setY(dy);
    }

    public void setTexturaDogo(Texture texture, float x, float y){

        super.textura = texture;
        regionS = new TextureRegion(texture);
        TextureRegion [][] texturaPerroS = regionS.split(96,277);

        animacionS = new Animation(0.07f, texturaPerroS[0][0],
                texturaPerroS[0][1],
                texturaPerroS[0][2], texturaPerroS[0][3],
                texturaPerroS[0][4], texturaPerroS[0][5], texturaPerroS[0][6],
                texturaPerroS[0][7],
                texturaPerroS[0][8],texturaPerroS[0][9],
                texturaPerroS[0][10],texturaPerroS[0][11], texturaPerroS[0][12], texturaPerroS[0][13],texturaPerroS[0][14],
                texturaPerroS[0][15],texturaPerroS[0][16]);

        animacionS.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacionS = 0;

        super.sprite = new Sprite(texturaPerroS[0][0]);
        super.sprite.setPosition(x,y);


    }

    public float getY(){
        return sprite.getY();
    }

}
