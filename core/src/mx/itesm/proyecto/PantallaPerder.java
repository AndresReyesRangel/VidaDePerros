package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

//Falta botón de regresar

class PantallaPerder extends Pantalla {

    private final Juego juego;

    private Texture texturaFondo;

    private Stage escenaPerder;

    //Puntaje
    private Marcador marcador;
    private int puntos;


    public PantallaPerder(Juego juego, int puntos) {
        this.juego = juego;
        this.puntos = puntos;
    }

    @Override
    public void show() {
        texturaFondo = new Texture("Pantalla GameOVer/pantalla_GameOver.png");
        crearPantalla();
        crearMarcador();
    }

    private void crearMarcador() {

        marcador = new Marcador(150,1250);
        marcador.marcar(puntos);
    }


    private void crearPantalla() {
        escenaPerder = new Stage(vista);
        Texture texturaBtnRegresar = new Texture("PantallaAcercaDe/btnRegresar.png");
        TextureRegionDrawable trdRegresar = new TextureRegionDrawable(new TextureRegion(texturaBtnRegresar));
        //Imagen btn presionado
        Texture texturaBtnRegresarP = new Texture("PantallaAcercaDe/btnRegresarP.png");
        TextureRegionDrawable trdRegresarP = new TextureRegionDrawable(new TextureRegion(texturaBtnRegresarP));

        ImageButton btnRegresar = new ImageButton(trdRegresar, trdRegresarP);

        btnRegresar.setPosition(ANCHO- btnRegresar.getWidth() , ALTO-btnRegresar.getHeight() );
        //Listener
        btnRegresar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaMenu(juego));
            }
        });

        escenaPerder.addActor(btnRegresar);

        Gdx.input.setInputProcessor(escenaPerder);
    }

    @Override
    public void render(float delta) {

        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        marcador.render(batch);
        batch.end();

        escenaPerder.draw();

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
