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
        texturaFondo = new Texture("PantallaConfiguracion/Pantalla_Config.png");
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
        final Texture texturaBtnVolumen = new Texture("PantallaConfiguracion/Sonido_Boton.png");
        TextureRegionDrawable trdVolumen = new TextureRegionDrawable(new TextureRegion(texturaBtnVolumen));
        //Imagen btnVolumenPushed
        Texture texturaBtnVolumenP = new Texture("Muted_Boton_Pushed.png");
        TextureRegionDrawable trdVolumenP = new TextureRegionDrawable(new TextureRegion(texturaBtnVolumenP));
        //Imagen btnVolumenOff
        Texture texturaBtnVolumenOff = new Texture("PantallaConfiguracion/Muted_Boton.png");
        TextureRegionDrawable trdVolumenOff = new TextureRegionDrawable(new TextureRegion(texturaBtnVolumenOff));
        //Imagen btnVolumenOffPushed
        Texture texturaBtnVolumenOffP = new Texture("Muted_Boton_Pushed.png");
        TextureRegionDrawable trdVolumenOffP = new TextureRegionDrawable(new TextureRegion(texturaBtnVolumenP));

        final ImageButton btnVolumen = new ImageButton(trdVolumen);
        final ImageButton btnVolumenOff = new ImageButton(trdVolumenOff);
        ImageButton btnRegresar = new ImageButton(trdRegresar, trdRegresarP);

        //Posiciones de los botones
        btnRegresar.setPosition(ANCHO / 2 - btnRegresar.getWidth() / 2, 2 * ALTO / 3 - 800);
        btnVolumen.setPosition(ANCHO / 2 - btnRegresar.getWidth() / 2, 2 * ALTO / 3 - 600);
        btnVolumenOff.setPosition(ANCHO / 2 - btnRegresar.getWidth() / 2, 2 * ALTO / 3 - 600);

        //Listeners de los botones
        btnRegresar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaMenu(juego));
            }
        });

        btnVolumen.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                escenaPantalla.addActor(btnVolumenOff);
                btnVolumen.remove();
            }
        });

        btnVolumenOff.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                escenaPantalla.addActor(btnVolumen);
                btnVolumenOff.remove();
            }
        });


        escenaPantalla.addActor(btnRegresar);
        escenaPantalla.addActor(btnVolumen);


        Gdx.input.setInputProcessor(escenaPantalla);
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

