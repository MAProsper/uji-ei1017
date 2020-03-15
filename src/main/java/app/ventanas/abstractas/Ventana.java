package app.ventanas.abstractas;

import app.Gestor;
import app.ventanas.interfaces.Button;
import app.ventanas.interfaces.Textbox;
import com.google.common.base.Strings;
import com.google.common.collect.Range;

import java.util.*;
import java.util.concurrent.Semaphore;

import static helpers.estaticos.Arguments.*;


abstract public class Ventana {
    private final static Scanner scanner = new Scanner(System.in);
    private final static Semaphore running = new Semaphore(1);
    protected final String title;
    protected final String info;
    protected final List<Textbox> textboxes;
    protected final List<Button> buttons;
    private final Map<Textbox, String> textboxesContent;
    private List<String> list;
    private Gestor gestor;

    public Ventana(final String title, final String info, final boolean list, final List<Textbox> textboxes, final List<Button> buttons) {
        this.title = stringNotEmpty("Title", title);
        this.info = stringNotEmpty("Information", info);
        this.list = list ? new LinkedList<>() : null;

        collectionWithoutNull("Textboxes", textboxes);
        this.textboxes = Collections.unmodifiableList(new LinkedList<>(textboxes));

        collectionWithoutNull("Buttons", buttons);
        validate("Buttons no puede estar vacia", !buttons.isEmpty());
        this.buttons = Collections.unmodifiableList(new LinkedList<>(buttons));

        textboxesContent = new HashMap<>();
        clearTextboxes();
    }

    public Ventana(final String title, final String info, final boolean list, final Textbox[] textboxes, final Button[] buttons) {
        this(title, info, list, Arrays.asList(referenceNotNull("Textboxes", textboxes)), Arrays.asList(referenceNotNull("Buttons", buttons)));
    }

    protected void update() {
    }

    public Optional<Ventana> pressButton(final Button button) {
        referenceNotNull("Button", button);
        return Optional.empty();
    }

    public final Gestor getGestor() {
        return validate("Gestor no esta asignado", gestor, hasGestor());
    }

    public void setGestor(final Gestor gestor) {
        this.gestor = gestor;
    }

    private List<String> validateList() {
        return validate("List no esta definida", list, hasList());
    }

    private Textbox validateTextbox(final Textbox name) {
        return validate("Textbox " + name + " no esta definida", referenceNotNull("Name", name), textboxes.contains(name));
    }

    final public boolean hasList() {
        return list != null;
    }

    final public boolean hasGestor() {
        return gestor != null;
    }

    final public String getTitle() {
        return title;
    }

    final public String getInfo() {
        return info;
    }

    final public List<String> getList() {
        return Collections.unmodifiableList(validateList());
    }

    public void setList(final List<String> list) {
        validateList();
        collectionWithoutNull("List", list);
        this.list = new LinkedList<>(list);
    }

    final public List<Textbox> getTextboxes() {
        return textboxes;
    }

    final public String getTextbox(final Textbox name) {
        return textboxesContent.get(validateTextbox(name));
    }

    public void setTextbox(final Textbox name, final String content) {
        textboxesContent.put(validateTextbox(name), referenceNotNull("Content", content));
    }

    public void clearTextboxes() {
        for (Textbox name : textboxes) setTextbox(name, "");
    }

    final public List<Button> getButtons() {
        return buttons;
    }

    private void renderWindow() {
        int index = 0;
        final StringBuilder window = new StringBuilder();

        window.append("\n\n");
        window.append(title).append("\n");
        window.append(Strings.repeat("-", title.length())).append("\n\n");
        window.append(info).append("\n\n");

        if (hasList()) {
            for (String line : list) window.append(line).append("\n");
            window.append("\n");
        }

        if (textboxes.size() != 0) {
            for (Textbox name : textboxes)
                window.append(String.format("(%d) %s: %s\n", index++, name.getDescription(), textboxesContent.get(name)));
            window.append("\n");
        }

        for (Button button : buttons) window.append(String.format("(%d) %s\n", index++, button.getDescription()));
        window.append("\n\n");

        System.out.print(window);
    }

    private int dialogSelect() {
        int option = -1;
        final Range<Integer> range = Range.closedOpen(0, textboxes.size() + buttons.size());

        while (!range.contains(option)) {
            renderWindow();
            System.out.print("Seleciona opcion: ");
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                option = -1;
            }
            scanner.nextLine();
        }

        return option;
    }

    private void dialogTextbox(final Textbox name) {
        renderWindow();
        System.out.format("Contenido de %s: ", name.getDescription());
        setTextbox(name, scanner.nextLine());
    }

    private Optional<Ventana> dialog() {
        update();
        int option;
        final int separator = textboxes.size();

        while (true) {
            option = dialogSelect();
            if (option >= separator) break;
            else dialogTextbox(textboxes.get(option));
        }

        return pressButton(buttons.get(option - separator));
    }

    final public Optional<Ventana> show() {
        if (!running.tryAcquire()) {
            throw new OverlappingVentanaException();
        } else try {
            return dialog();
        } finally {
            running.release();
        }
    }

    @Override
    public String toString() {
        return "Ventana{" +
                "title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", list=" + hasList() +
                ", textboxes=" + textboxes +
                ", buttons=" + buttons +
                '}';
    }

    public static class OverlappingVentanaException extends IllegalStateException {
        private static final long serialVersionUID = -4234327239149123858L;
    }
}
