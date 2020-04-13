package mx.itesm.proyecto;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

class PantallaJuego extends Pantalla {

    private int cont = 0;
    private int tiempo = 0;

    private final Juego juego;

    //Fondo
    private Texture texturaFondo;
    private Fondo fondo;

    public PantallaJuego(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        texturaFondo = new Texture("PantallaJuego/FondoJuego.png");
        crearFondo();
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        actualizar(delta);

        batch.setProjectionMatrix(camara.combined);
        batch.begin();


        fondo.render(batch);

        batch.end();

    }

    private void actualizar(float delta) {
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

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
