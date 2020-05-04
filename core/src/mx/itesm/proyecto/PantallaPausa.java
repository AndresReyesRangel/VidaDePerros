package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PantallaPausa extends Pantalla{

    private final Juego juego;

    private Texture texturaFondo;

    private Stage escenaPantalla;

    public PantallaPausa(Juego juego){
        this.juego = juego;
    }


    @Override
    public void show() {
        texturaFondo = new Texture("");
        crearPantalla();


    }

    private void crearPantalla() {

        escenaPantalla = new Stage(vista);

        //Botón regresar a juego
        Texture texturaBtnRegresarJuego= new Texture("PantallaPausa/Play_Boton.png");
        TextureRegionDrawable trdRegresarJuego = new TextureRegionDrawable(new TextureRegion(texturaBtnRegresarJuego));

        //Botón regresar juego presionado
        Texture texturaBtnRegresarJuegoP = new Texture("PantallaPausa/Play_Boton_pushed.png");
        TextureRegionDrawable trdRegresarJuegoP = new TextureRegionDrawable(new TextureRegion(texturaBtnRegresarJuegoP));

        //Botón salir del juego
        Texture texturaBtnSalirJuego = new Texture("PantallaPausa/Salir_Boton.png");
        TextureRegionDrawable trdSalirJuego = new TextureRegionDrawable(new TextureRegion(texturaBtnSalirJuego));

        //Botón salir del juego presionado
        Texture texturaBtnSalirJuegoP = new Texture("PantallaPausa/Salir_Boton_Pushed");
        TextureRegionDrawable trdSalirJuegoP = new TextureRegionDrawable(new TextureRegion(texturaBtnSalirJuegoP));

        //Acciones de los botones
        ImageButton btnRegresarJuego = new ImageButton(trdRegresarJuego, trdRegresarJuegoP);
        ImageButton btnSalirJuego = new ImageButton(trdSalirJuego, trdSalirJuegoP);

        //Posiciones de los botones
        btnRegresarJuego.setPosition(ANCHO- btnRegresarJuego.getWidth() , ALTO-btnRegresarJuego.getHeight());
        btnSalirJuego.setPosition(ANCHO- btnSalirJuego.getWidth() , ALTO-btnSalirJuego.getHeight()*3);

        //Listeners botones
        btnRegresarJuego.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaJuego(juego));
            }
        });
        btnSalirJuego.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaMenu(juego));
            }
        });

        //Actores botones
        escenaPantalla.addActor(btnRegresarJuego);
        escenaPantalla.addActor(btnSalirJuego);

        Gdx.input.setInputProcessor(escenaPantalla);

    }

    @Override
    public void render(float delta) {

        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo, 0, 0);
        batch.end();
        escenaPantalla.draw();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    //Comentario para commit

    @Override
    public void dispose() { texturaFondo.dispose();}
}
