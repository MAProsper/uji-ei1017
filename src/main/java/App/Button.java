package App;

public interface Button {
    static Button[] empty() {
        return new Button[]{};
    }

    String getDescripcion();
}
