package App;

public interface Textbox {
    static Textbox[] empty() {
        return new Textbox[]{};
    }

    String getDescripcion();
}
