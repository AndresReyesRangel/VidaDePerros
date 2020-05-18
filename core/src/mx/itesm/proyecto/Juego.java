package mx.itesm.proyecto;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;


public class Juego extends Game {

	//Musica
	AssetManager manager;
	private Music audioFondo;


	@Override
	public void create() {
		cargarMusica();
		setScreen(new PantallaMenu(this));

	}

	public void reproducirMusica(int n) {
		if(!audioFondo.isPlaying()){
			audioFondo.play();
		}
	}

	public void pausarMusica(int n) {
		if(audioFondo.isPlaying()) {
			audioFondo.pause();
		}
	}

	private void cargarMusica() {
		manager = new AssetManager();
		manager.load("Music/MainMenu.mp3", Music.class);
		manager.finishLoading();
		audioFondo = manager.get("Music/MainMenu.mp3");
		audioFondo.setLooping(true);
		audioFondo.setVolume(0.2f);
		audioFondo.play();
	}
}


