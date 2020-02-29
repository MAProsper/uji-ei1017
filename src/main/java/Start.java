import App.Interfaz;

import java.util.Arrays;

public class Start {
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz(
                "Titulo",
                "Linea de informacion",
                Arrays.asList("Primer dato a informativo", "Segundo dato a informativo"),
                Arrays.asList("Primer dato a rellenar", "Segundo dato a rellenar"),
                Arrays.asList("Primer botton", "Segundo botton"));

        String button = interfaz.nextButton();

        System.out.println("\n\n------------- EXIT --------------");
        System.out.println(button);
        System.out.println(interfaz.getForm());
    }
}
