package app;

import app.ventanas.abstractas.Vista;
import app.ventanas.vistas.VistaPrincipal;

public class Start {
    private Start() {
    }

    public static void main(String[] args) {
        final Manejador manejador = new Manejador();

        final Modelo modelo = new Modelo();
        final Vista vista = new VistaPrincipal();
        manejador.connectar(modelo, vista);

        manejador.show(vista);
    }
}
