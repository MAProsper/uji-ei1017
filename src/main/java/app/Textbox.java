package app;

public interface Textbox {
    static Textbox[] empty() {
        return new Textbox[]{};
    }

    String getDescripcion();
}
