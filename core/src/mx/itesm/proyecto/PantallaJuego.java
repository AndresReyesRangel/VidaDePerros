package mx.itesm.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

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


    public PantallaJuego(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        texturaFondo = new Texture("PantallaJuego/FondoJuego.png");
        crearFondo();
        cargarTexturas();
        crearPerro();

        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void crearPerro() {
        perro = new Perro(texturaPerro, ANCHO / 2, ALTO * 0.05f);

    }

    private void cargarTexturas() {
        texturaPerro = new Texture("PantallaJuego/perro.png");
    }
    @Override
    public void render(float delta) {
        borrarPantalla();
        actualizar(delta);

        batch.setProjectionMatrix(camara.combined);
        batch.begin();


        fondo.render(batch);
        perro.render(batch);
        batch.end();

    }

    private void actualizar(float delta) {
        moverPerro();
        moverFondo(delta);
        cont++;
        tiempo = cont;
        fondo.actualizarTiempo(tiempo);

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
        switch (movimiento) {
            case DERECHA:
                perro.mover(10);
                break;
            case IZQUIERDA:
                perro.mover(-10);
                break;
            default:
                break;
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
                movimiento = Movimiento.DERECHA;
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
}
