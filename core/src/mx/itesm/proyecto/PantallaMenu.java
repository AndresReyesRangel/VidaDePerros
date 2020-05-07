package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

class PantallaMenu extends Pantalla {

    //fondo
    private final Juego juego;
    private Texture texturaFondo;

    //Musica
    private Music audioFondo;

    //MENU
    private Stage escenaMenu;

    public PantallaMenu(Juego juego) {

        this.juego = juego;
    }

    @Override
    public void show() {
        texturaFondo = new Texture("Menu/fondoMenu.png");
        crearMenu();
        cargarMusica();
    }

    private void cargarMusica() {


        AssetManager manager = new AssetManager();
        manager.load("Music/MainMenu.mp3", Music.class);
        manager.finishLoading();
        audioFondo = manager.get("Music/MainMenu.mp3");
        audioFondo.setLooping(true);
        audioFondo.play();

    }

    private void crearMenu() {
        escenaMenu = new Stage(vista);

        //Botones cargando imagenes
        Texture texturaBtnJugar = new Texture("Menu/btnJugar.png");
        TextureRegionDrawable trdJugar = new TextureRegionDrawable(new TextureRegion(texturaBtnJugar));
        //Imagen btn presionado
        Texture texturaBtnJugarP = new Texture("Menu/btnJugarP.png");
        TextureRegionDrawable trdJugarP = new TextureRegionDrawable(new TextureRegion(texturaBtnJugarP));

        Texture texturaBtnAcercaDe = new Texture("Menu/btnAcercaDe.png");
        TextureRegionDrawable trdAcercaDe = new TextureRegionDrawable(new TextureRegion(texturaBtnAcercaDe));
        Texture texturaBtnAcercaDeP = new Texture("Menu/btnAcercaDeP.png");
        TextureRegionDrawable trdAcercaDeP = new TextureRegionDrawable(new TextureRegion(texturaBtnAcercaDeP));

        Texture texturaBtnInstrucciones = new Texture("Menu/btnInstrucciones.png");
        TextureRegionDrawable trdInstrucciones = new TextureRegionDrawable(new TextureRegion(texturaBtnInstrucciones));
        Texture texturaBtnInstruccionesP = new Texture("Menu/btnInstruccionesP.png");
        TextureRegionDrawable trdInstruccionesP = new TextureRegionDrawable(new TextureRegion(texturaBtnInstruccionesP));

        Texture texturaBtnConfiguracion = new Texture("Menu/btnConfig.png");
        TextureRegionDrawable trdConfiguracion = new TextureRegionDrawable(new TextureRegion(texturaBtnConfiguracion));
        Texture texturaBtnConfiguracionP = new Texture("Menu/btnConfigP.png");
        TextureRegionDrawable trdConfiguracionP = new TextureRegionDrawable(new TextureRegion(texturaBtnConfiguracionP));

        //Cargando los botones

        ImageButton btnJugar = new ImageButton(trdJugar, trdJugarP);
        ImageButton btnAcercaDe = new ImageButton(trdAcercaDe, trdAcercaDeP);
        ImageButton btnInstrucciones = new ImageButton(trdInstrucciones, trdInstruccionesP);
        ImageButton btnConfiguracion = new ImageButton(trdConfiguracion, trdConfiguracionP);

        btnJugar.setPosition(ANCHO/2-btnJugar.getWidth()/2 , 2*ALTO/3-300);
        btnAcercaDe.setPosition(ANCHO/2-btnJugar.getWidth()/2, ALTO/3-200);
        btnInstrucciones.setPosition(ANCHO/2-btnInstrucciones.getWidth()/2, ALTO/3);
        btnConfiguracion.setPosition(ANCHO/2-btnInstrucciones.getWidth()/2, ALTO/3-90);

        //Listener
        btnJugar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaJuego(juego));
            }
        });

        btnAcercaDe.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaAcercaDe(juego));
            }
        });

        btnInstrucciones.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaInstrucciones(juego));
            }
        });

        btnConfiguracion.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaConfiguracion(juego));
            }
        });

        escenaMenu.addActor(btnJugar);
        escenaMenu.addActor(btnAcercaDe);
        escenaMenu.addActor(btnInstrucciones);
        escenaMenu.addActor(btnConfiguracion);

        Gdx.input.setInputProcessor(escenaMenu);
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.end();

        escenaMenu.draw();
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
