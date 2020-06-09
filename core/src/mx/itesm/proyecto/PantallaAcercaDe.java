package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

//Falta botón de regresar

class PantallaAcercaDe extends Pantalla {

    private final Juego juego;

    private Texture texturaFondo;

    private Stage escenaPantalla;

    private AssetManager managerEfecto;
    private Music efecto;

    public PantallaAcercaDe(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        texturaFondo = new Texture("PantallaAcercaDe/FondoAcercaDe.png");
        crearPantalla();
    }

    private void reproducirEfecto(){
        managerEfecto = new AssetManager();
        managerEfecto.load("SoundEffects/buton.mp3", Music.class);
        managerEfecto.finishLoading();
        efecto = managerEfecto.get("SoundEffects/buton.mp3");
        efecto.setVolume(0.2f);
        efecto.play();
    }

    private void crearPantalla() {
        escenaPantalla = new Stage(vista);
        Texture texturaBtnRegresar = new Texture("PantallaAcercaDe/btnRegresar.png");
        TextureRegionDrawable trdRegresar = new TextureRegionDrawable(new TextureRegion(texturaBtnRegresar));
        //Imagen btn presionado
        Texture texturaBtnRegresarP = new Texture("PantallaAcercaDe/btnRegresarP.png");
        TextureRegionDrawable trdRegresarP = new TextureRegionDrawable(new TextureRegion(texturaBtnRegresarP));

        ImageButton btnRegresar = new ImageButton(trdRegresar, trdRegresarP);

        btnRegresar.setPosition(ANCHO- btnRegresar.getWidth() - 100 , ALTO-btnRegresar.getHeight() - 1000 );
        //Listener
        btnRegresar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaMenu(juego));
            }
        });

        escenaPantalla.addActor(btnRegresar);

        Gdx.input.setInputProcessor(escenaPantalla);

        btnRegresar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                reproducirEfecto();
                juego.setScreen(new PantallaMenu(juego));

            }
        });
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
