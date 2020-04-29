package app;

public class Start {
    private Start() {
    }

    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        //gestor.load(Paths.get("target/clientes3.bin"));
        gestor.show();
    }
}
