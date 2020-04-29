package app;

import java.nio.file.Paths;

public class Start {
    private Start() {
    }

    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        gestor.load(Paths.get("target/clientes2.bin"));
        gestor.show();
    }
}
