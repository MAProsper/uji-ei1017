package App;

import App.Ventanas.VentanaPrincipal;

public class Start {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        gestor.run(new VentanaPrincipal());
    }
}
