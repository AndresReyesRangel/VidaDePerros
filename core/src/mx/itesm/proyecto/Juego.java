package mx.itesm.proyecto;

import com.badlogic.gdx.Game;



public class Juego extends Game {

		@Override
		public void create() {
			setScreen(new PantallaMenu(this));
		}
	}


