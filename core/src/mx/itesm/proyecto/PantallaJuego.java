package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.security.AlgorithmConstraints;

class PantallaJuego extends Pantalla {

    private int cont;
    private int tiempo;
    public int puntos = cont;

    private final Juego juego;

    //Stage para el boton de pausa
    private Stage escenaPantalla;

    //Fondo
    private Texture texturaFondo;
    private Fondo fondo;

    //Perro
    private Perro perro;
    private Texture texturaPerro;
    private Movimiento movimiento = Movimiento.QUIETO;
    private float pasosPerro = 0;

    //Enemigos
    private Texture texturaOil;
    private Texture texturaCaja;
    private Texture texturaColadera;
    private Obstaculos oil;
    private Obstaculos caja;
    private Obstaculos coladera;

    //Marcador
    public Marcador marcador;


    //Pausa
    private EscenaPausa escenaPausa;
    private EstadoJuego estadoJuego = EstadoJuego.JUGANDO; // JUGANDO, PAUSA, GANÓ, PERDIO



    public PantallaJuego(Juego juego) {
        this.juego = juego;
    }

    public int getPuntos(){
        return puntos;
    }

    @Override
    public void show() {
        texturaFondo = new Texture("PantallaJuego/FondoJuego.png");
        crearFondo();
        cargarTexturas();
        crearPerro();
        crearObstaculos();
        crearMarcador();

        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }


    private void crearObstaculos() {
        oil = new Obstaculos(texturaOil, (ANCHO-texturaOil.getWidth())/2, ALTO - 100);
        caja = new Obstaculos(texturaCaja, 135-texturaCaja.getWidth()/2, ALTO*0.05f );
        coladera = new Obstaculos(texturaColadera, 520-texturaColadera.getWidth()/2, ALTO*0.05f);
    }

    private void crearMarcador() {
        texturaFondo = new Texture("PantallaJuego/FondoJuego.png");
        marcador = new Marcador(150,1250);
    }

    private void crearPerro() {

        perro = new Perro(texturaPerro, (ANCHO-texturaPerro.getWidth())/2, ALTO * 0.05f);


    }


    private void cargarTexturas() {

        escenaPantalla = new Stage(vista);

        texturaOil = new Texture("Obstáculos/oil.png");
        texturaCaja = new Texture("Obstáculos/Apple_box.png");
        texturaColadera = new Texture("Obstáculos/Coladera.png");
        texturaPerro = new Texture("Perro/perro_nuevo.png");

        //Botón pausa
        Texture texturaBtnPausa = new Texture("PantallaJuego/Pausa_Boton.png");
        TextureRegionDrawable trdBtnPausa = new TextureRegionDrawable(new TextureRegion(texturaBtnPausa));

        //Botón pausa presionado
        Texture texturaBtnPausaP = new Texture("PantallaJuego/Pausa_Boton_Pushed.png");
        TextureRegionDrawable trdBtnPausaP = new TextureRegionDrawable(new TextureRegion(texturaBtnPausaP));

        ImageButton botonPausa = new ImageButton(trdBtnPausa, trdBtnPausaP);
        botonPausa.setPosition(ANCHO/2 , ALTO/2);

        botonPausa.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                estadoJuego = EstadoJuego.PAUSADO;
                if(escenaPausa == null) {
                    escenaPausa = new EscenaPausa(vista, batch);
                }else{
                    escenaPausa = null;
                    estadoJuego = EstadoJuego.JUGANDO;
                }

            }
        });

        escenaPantalla.addActor(botonPausa);
        Gdx.input.setInputProcessor(escenaPantalla);

    }

    //Comentario para el push



    @Override
    public void render(float delta) {
        borrarPantalla();
        if(estadoJuego==EstadoJuego.JUGANDO) {
            actualizar(delta);
        }

        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        fondo.render(batch);
        perro.render(batch);


        //obstaculos
        oil.render(batch);
        coladera.render(batch);
        caja.render(batch);

        // marcador
        marcador.render(batch);

        batch.end();

        marcador.marcar(cont/60);
        if(estadoJuego==EstadoJuego.PAUSADO) {
            escenaPausa.draw();
        }
    }



    private void actualizar(float delta) {
        moverPerro();
        moverFondo(delta);
        moverObstaculo(delta);
        cont++;
        tiempo = cont;
        fondo.actualizarTiempo(tiempo);
        probarColisiones();

    }

    private void moverObstaculo(float delta) {

        oil.mover(delta*1.5f);
        caja.mover(delta);
        coladera.mover(delta);

        if(oil.getY() < 0){
            oil.setY(ALTO);
        }

        if(caja.getY() < 0){
            caja.setY(ALTO);
        }

        if(coladera.getY() < 0){
            coladera.setY(ALTO);
        }
    }

    public void crearFondo(){

        fondo = new Fondo(texturaFondo, 0, 0);
    }

    private void moverFondo(float delta) {
        fondo.mover(delta);
        if(fondo.getY() <= -ALTO){
            fondo.setY(0);
        }

    }

    private void moverPerro() {

        pasosPerro = perro.getX();
        //Movimiento para pasar del carril de la izquierda al carril de en medio
        if(movimiento == Movimiento.DERECHA && (0<perro.getX() && perro.getX()<240)){

            perro.setX(360-(texturaPerro.getWidth()/2));
            movimiento = Movimiento.QUIETO;

            //Para pasar del carril de en medio al de la derecha
        }else if(movimiento == Movimiento.DERECHA && (240<perro.getX() && perro.getX()<480)){

            perro.setX(520);

            //Para pasar del carril de en medio al de la izquierda
        }else if(movimiento == Movimiento.IZQUIERDA && (240<perro.getX() && perro.getX()<480)){

            perro.setX(135);

            //Para pasar del carril de la derecha al de en medio
        }else if(movimiento == Movimiento.IZQUIERDA && (480<perro.getX() && perro.getX()<720)){

            perro.setX(360-(texturaPerro.getWidth())/2);
            movimiento = Movimiento.QUIETO;
        }

    }

    private void probarColisiones() {

        Rectangle rectOil = oil.sprite.getBoundingRectangle();
        Rectangle rectCaja = caja.sprite.getBoundingRectangle();
        Rectangle rectColadera = coladera.sprite.getBoundingRectangle();
        Rectangle rectPerro = perro.sprite.getBoundingRectangle();

        if(rectCaja.overlaps(rectPerro) || rectOil.overlaps(rectPerro) || rectColadera.overlaps(rectPerro)){
            int puntos = marcador.getCont();
            juego.setScreen(new PantallaPerder(juego, puntos));
        }

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        texturaPerro.dispose();
    }

    private class ProcesadorEntrada implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);

            if (v.x >= ANCHO / 2) {
                //DERECHA
                if(perro.getX()<ANCHO-texturaPerro.getWidth()){
                    movimiento = Movimiento.DERECHA;
                }


            } else {
                //IZQUIERDA
                movimiento = Movimiento.IZQUIERDA;

                //Pausar el juego
                /*

                estadoJuego = EstadoJuego.PAUSADO;
                if(escenaPausa == null) {
                    escenaPausa = new EscenaPausa(vista, batch);
                }else{
                    escenaPausa = null;
                    estadoJuego = EstadoJuego.JUGANDO;
                }
                */


                }

            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            movimiento = Movimiento.QUIETO;
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }

    //Movimiento
    public enum Movimiento {
        DERECHA,
        IZQUIERDA,
        QUIETO
    }

    class EscenaPausa extends Stage {

        public EscenaPausa(Viewport vista, SpriteBatch batch){
            super(vista, batch);

            Texture texturaCirculo = new Texture("PantallaJuego/Juego_Pausa.png");

            Image imagenCirculo = new Image(texturaCirculo);
            imagenCirculo.setPosition((ANCHO - texturaCirculo.getWidth())/2 , ALTO/2);
            this.addActor(imagenCirculo);
        }
    }

    private enum EstadoJuego {
        JUGANDO,
        PAUSADO,
        GANO,
        PERDIO
    }

    //Arriba el boquita PAPÁ!!!!
}
