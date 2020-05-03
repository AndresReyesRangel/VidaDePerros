package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PantallaConfiguracion extends Pantalla {
    private final Juego juego;

    private Texture texturaFondo;

    private Stage escenaPantalla;

    public PantallaConfiguracion(Juego juego){
        this.juego = juego;
    }
    @Override
    public void show() {
        texturaFondo = new Texture("PantallaInstrucciones/fondoInstrucciones.png");
        crearPantalla();
    }

    private void crearPantalla() {
        escenaPantalla = new Stage(vista);
        //Imagen btnRegresar
        Texture texturaBtnRegresar = new Texture("PantallaConfiguracion/btnRegresar.png");
        TextureRegionDrawable trdRegresar = new TextureRegionDrawable(new TextureRegion(texturaBtnRegresar));
        //Imagen btnRegresar presionado
        Texture texturaBtnRegresarP = new Texture("PantallaConfiguracion/btnRegresarP.png");
        TextureRegionDrawable trdRegresarP = new TextureRegionDrawable(new TextureRegion(texturaBtnRegresarP));
        //Imagen btnVolumen
        Texture texturaBtnVolumen = new Texture("PantallaConfiguracion/.png");
        TextureRegionDrawable trdVolumen = new TextureRegionDrawable(new TextureRegion(texturaBtnVolumen));
        //Imagen btnVolumenOff
        Texture texturaBtnVolumenOff = new Texture("PantallaConfiguracion/.png");
        TextureRegionDrawable trdVolumenOff = new TextureRegionDrawable(new TextureRegion(texturaBtnVolumenOff));

        ImageButton btnVolumen = new ImageButton(trdVolumen);
        ImageButton btnVolumenOff = new ImageButton(trdVolumenOff);
        ImageButton btnRegresar = new ImageButton(trdRegresar, trdRegresarP);


        btnRegresar.setPosition(ANCHO / 2 - btnRegresar.getWidth() / 2, 2 * ALTO / 3 - 800);
        btnVolumen.setPosition(ANCHO / 2 - btnRegresar.getWidth() / 2, 2 * ALTO / 3 - 600);
        btnVolumenOff.setPosition(ANCHO / 2 - btnRegresar.getWidth() / 2, 2 * ALTO / 3 - 600);

    }



    @Override
    public void render(float delta) {
        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.end();

        escenaPantalla.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        texturaFondo.dispose();
    }
}


