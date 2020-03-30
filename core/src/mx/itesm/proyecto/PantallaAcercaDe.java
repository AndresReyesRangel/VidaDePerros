package mx.itesm.proyecto;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

//Falta botón de regresar

class PantallaAcercaDe extends Pantalla {

    private final Juego juego;

    private Texture texturaFondo;

    public PantallaAcercaDe(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        texturaFondo = new Texture("PantallaAcercaDe/FondoAcercaDe.png");

    }

    @Override
    public void render(float delta) {

        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.end();

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
