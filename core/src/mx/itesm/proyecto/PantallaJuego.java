package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.security.AlgorithmConstraints;

class PantallaJuego extends Pantalla {

    private int cont = 0;
    private int tiempo = 0;

    private final Juego juego;

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
    private Marcador marcador;




    public PantallaJuego(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        texturaFondo = new Texture("PantallaJuego/FondoJuego.png");
        crearFondo();
        cargarTexturas();
        crearPerro();
        crearMarcador();
        crearObstaculos();


        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void crearObstaculos() {
        oil = new Obstaculos(texturaOil, (ANCHO-texturaOil.getWidth())/2, ALTO - 100);
        caja = new Obstaculos(texturaCaja, 135-texturaCaja.getWidth()/2, ALTO*0.05f );
        coladera = new Obstaculos(texturaColadera, 520-texturaColadera.getWidth()/2, ALTO*0.05f);
    }

    private void crearMarcador() {texturaFondo = new Texture("PantallaJuego/FondoJuego.png");
        marcador = new Marcador(150,1250);
    }

    private void crearPerro() {
        perro = new Perro(texturaPerro, (ANCHO-texturaPerro.getWidth())/2, ALTO * 0.05f);
    }

    private void cargarTexturas() {
        texturaOil = new Texture("Obstáculos/oil.png");
        texturaCaja = new Texture("Obstáculos/Apple_box.png");
        texturaColadera = new Texture("Obstáculos/Coladera.png");
        texturaPerro = new Texture("Perro/perro_nuevo.png");
    }


    @Override
    public void render(float delta) {
        borrarPantalla();
        actualizar(delta);

        batch.setProjectionMatrix(camara.combined);
        batch.begin();

        fondo.render(batch);
        perro.render(batch);

        // marcador
        marcador.render(batch);
        oil.render(batch);
        coladera.render(batch);
        caja.render(batch);
        batch.end();

        marcador.marcar(cont/60);
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
            juego.setScreen(new PantallaPerder(juego));

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

                /*Pausar el juego
                estadoJuego = EstadoJuego.PAUSADO;
                if(escenaPausa == null){
                    escenaPausa = new EscenaPausa(vista, batch);*/
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

    //Arriba el boquita papá me la pela roman
}
