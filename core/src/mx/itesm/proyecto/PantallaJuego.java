package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.Random;

import java.security.AlgorithmConstraints;

class PantallaJuego extends Pantalla implements GestureDetector.GestureListener {

    private int cont;
    private int tiempo;
    private int variableJeje;
    private boolean booleano = false;

    Random oilRan = new Random();
    Random coladeraRan = new Random();
    Random cajaRan = new Random();
    Random oilRanPosicion = new Random();
    Random coladeraRanPosicion = new Random();
    Random cajaRanPosicion = new Random();


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


    @Override
    public void show() {
        texturaFondo = new Texture("PantallaJuego/FondoJuego.png");
        crearFondo();
        cargarTexturas();
        crearPerro();
        crearObstaculos();
        crearMarcador();
    }


    private void crearObstaculos() {

        oil = new Obstaculos(texturaOil, (ANCHO - texturaOil.getWidth()) / 2, ALTO - 100);
        caja = new Obstaculos(texturaCaja, 135 - texturaCaja.getWidth() / 2, ALTO +100f);
        coladera = new Obstaculos(texturaColadera, 520 - texturaColadera.getWidth()/2, ALTO * 0.05f);


    }

    private void crearMarcador() {
        texturaFondo = new Texture("PantallaJuego/FondoJuego.png");
        marcador = new Marcador(150,1250);
    }

    private void crearPerro() {
        perro = new Perro(texturaPerro, ANCHO, ALTO * 0.05f);
        perro.setX((ANCHO-perro.sprite.getWidth())/2);
    }


    private void cargarTexturas() {

        escenaPantalla = new Stage(vista);

        texturaOil = new Texture("Obstáculos/oil.png");
        texturaCaja = new Texture("Obstáculos/Apple_box.png");
        texturaColadera = new Texture("Obstáculos/Coladera.png");
        texturaPerro = new Texture("Perro/perro_nuevo_mov.png");

        //Botón pausa
        Texture texturaBtnPausa = new Texture("PantallaJuego/Pausa_Boton.png");
        TextureRegionDrawable trdBtnPausa = new TextureRegionDrawable(new TextureRegion(texturaBtnPausa));

        //Botón pausa presionado
        Texture texturaBtnPausaP = new Texture("PantallaJuego/Pausa_Boton_pushed.png");
        TextureRegionDrawable trdBtnPausaP = new TextureRegionDrawable(new TextureRegion(texturaBtnPausaP));

        ImageButton botonPausa = new ImageButton(trdBtnPausa, trdBtnPausaP);
        botonPausa.setPosition((ANCHO - texturaBtnPausa.getWidth()) / 2, ALTO - 90);

        botonPausa.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                estadoJuego = EstadoJuego.PAUSADO;
                if (escenaPausa == null) {
                    escenaPausa = new EscenaPausa(vista, batch);
                } else {
                    escenaPausa = null;
                    estadoJuego = EstadoJuego.JUGANDO;
                }
                booleano = true;

            }
        });

        escenaPantalla.addActor(botonPausa);
        // no estoy seguro

        if (botonPausa.isPressed()){
            Gdx.input.setInputProcessor(escenaPantalla);
            System.out.println("Sí estoy jalando :D");
        } else {
            Gdx.input.setInputProcessor(new GestureDetector(this));
            System.out.println("Sí estoy jalando pero con otro input :D");
        }
    }

    //Comentario para el push x2



    @Override
    public void render(float delta) {
        borrarPantalla();



        if(estadoJuego==EstadoJuego.JUGANDO) {
                actualizar(delta);
        }

        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        fondo.render(batch);

        perro.renderP(batch, perro.sprite.getX(),perro.sprite.getY());

        //obstaculos

        oil.render(batch);


        coladera.render(batch);
        caja.render(batch);

        // marcador
        marcador.render(batch);

        batch.end();

        if(cont/60 == (variableJeje) ){
            variableJeje+=10;
        }

        marcador.marcar(cont/60);
        escenaPantalla.draw();
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




        oil.mover(delta*variableJeje/2);
        caja.mover(delta*variableJeje/2);
        coladera.mover(delta*variableJeje/2);

        int randomOil = 1280+oilRan.nextInt(1500);
        int randomCaja = 1280+cajaRan.nextInt(1500);
        int randomColadera = 1280+coladeraRan.nextInt(1500);

        int randomOilPosicion = oilRanPosicion.nextInt(3);
        int randomCajaPosicion = cajaRanPosicion.nextInt(3);
        int ramdomColaderaPosicion = coladeraRanPosicion.nextInt(3);


        if (oil.getY() < 0) {
            oil.setY(randomOil);
            if(randomOilPosicion == 0){
                //Carril izquierda
                oil.setX(135 - texturaOil.getWidth()+100);
            }if(randomOilPosicion == 2){
                //Carril en medio
                oil.setX(520 - texturaOil.getWidth()-100 );
            }if(randomOilPosicion == 1){
                //Carril derecha
                oil.setX(ANCHO - texturaOil.getWidth()-90);
            }


        }

        if (caja.getY() < 0) {
            caja.setY(randomCaja);
            if(randomCajaPosicion == 0){

                caja.setX(135 - texturaCaja.getWidth() +50);
            }if(randomCajaPosicion == 2){

                caja.setX(520 - texturaCaja.getWidth()-60);
            }if(randomCajaPosicion == 1){

                caja.setX(ANCHO - texturaCaja.getWidth() -100);
            }

        }

        if (coladera.getY() < 0) {
            coladera.setY(randomColadera);
            if(ramdomColaderaPosicion == 0){

                coladera.setX(135 - texturaCaja.getWidth() +50 );
            }if(ramdomColaderaPosicion == 2){

                coladera.setX(520 - texturaColadera.getWidth() -70 );
            }if(ramdomColaderaPosicion == 1){

                coladera.setX(ANCHO - texturaOil.getWidth() -80);
            }
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

    // Revisar el movimiento.
    private void moverPerro() {

        pasosPerro = perro.sprite.getX();
        //Movimiento para pasar del carril de la izquierda al carril de en medio
        if(movimiento == Movimiento.DERECHA && (0<perro.sprite.getX() && perro.sprite.getX()<240)){

            perro.sprite.setPosition(360-(perro.sprite.getWidth()/2), perro.sprite.getY());
            movimiento = Movimiento.QUIETO;

            //Para pasar del carril de en medio al de la derecha
        }else if(movimiento == Movimiento.DERECHA && (240<perro.sprite.getX() && perro.sprite.getX()<480)){

            perro.sprite.setPosition(520, perro.sprite.getY());

            //Para pasar del carril de en medio al de la izquierda
        }else if(movimiento == Movimiento.IZQUIERDA && (240<perro.sprite.getX() && perro.sprite.getX()<480)){

            perro.sprite.setX(135);

            //Para pasar del carril de la derecha al de en medio
        }else if(movimiento == Movimiento.IZQUIERDA && (480<perro.sprite.getX() && perro.sprite.getX()<720)){

            perro.sprite.setX(360-(perro.sprite.getWidth())/2);
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

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if(velocityX<0){
            movimiento = Movimiento.IZQUIERDA;
        }else if(velocityX>0){
            movimiento = Movimiento.DERECHA;
        }
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

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

            Texture texturaCirculo = new Texture("PantallaJuego/Juego_pausa.png");

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
