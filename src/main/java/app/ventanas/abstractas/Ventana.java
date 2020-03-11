package app.ventanas.abstractas;

import app.Gestor;
import app.ventanas.interfaces.Button;
import app.ventanas.interfaces.Textbox;
import com.google.common.base.Strings;
import com.google.common.collect.Range;

import java.util.*;

import static helpers.estaticos.Arguments.*;


abstract public class Ventana {
    private final static Scanner scanner = new Scanner(System.in);
    private final String title;
    private final String info;
    private final List<Textbox> textboxes;
    private final Map<Textbox, String> textboxesContent;
    private final List<Button> buttons;
    private final List<String> list;
    private static Gestor gestor;

    public Ventana(final String title, final String info, final boolean list, final List<Textbox> textboxes, final List<Button> buttons) {
        this.title = stringNotEmpty("header", title);
        this.info = stringNotEmpty("info", info);
        this.list = list ? new LinkedList<>() : null;
        this.textboxes = new LinkedList<>(collectionWithoutNull("textboxes", textboxes));
        this.buttons = new LinkedList<>(collectionWithoutNull("buttons", buttons));

        textboxesContent = new HashMap<>();
        if (!textboxes.isEmpty()) clearTextboxes();
    }

    public Ventana(final String title, final String info, final boolean list, final Textbox[] textboxes, final Button[] buttons) {
        this(title, info, list, Arrays.asList(referenceNotNull("textboxes", textboxes)), Arrays.asList(referenceNotNull("buttons", buttons)));
    }

    protected abstract void update();

    protected abstract Ventana handle(final Button button);

    private List<String> validateList() {
        return validate("list no esta definida", list, list != null);
    }


    private Textbox validateTextbox(final Textbox name) {
        return validate("textbox " + name + " no esta definida", referenceNotNull("name", name), textboxes.contains(name));
    }

    public static Gestor getGestor() {
        return validate("gestor no esta asignado", gestor, gestor != null);
    }

    public static boolean hasGestor() {
        return gestor != null;
    }

    public static void setGestor(final Gestor gestor) {
        Ventana.gestor = gestor;
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
        final List<String> ignored = validateList();  // Warning: result ignored
        collectionWithoutNull("list", list);
        this.list.clear();
        this.list.addAll(list);
    }

    final public List<Textbox> getTextboxes() {
        return Collections.unmodifiableList(textboxes);
    }

    final public String getTextbox(final Textbox name) {
        return textboxesContent.get(validateTextbox(name));
    }

    public void setTextbox(final Textbox name, final String content) {
        textboxesContent.put(validateTextbox(name), referenceNotNull("content", content));
    }

    public void clearTextboxes() {
        validate("textboxes no esta definido", !textboxes.isEmpty());
        for (Textbox name : textboxes) setTextbox(name, "");
    }

    final public List<Button> getButtons() {
        return Collections.unmodifiableList(buttons);
    }

    private void renderWindow() {
        int index = 0;
        final StringBuilder window = new StringBuilder();

        window.append("\n\n");
        window.append(title).append("\n");
        window.append(Strings.repeat("-", title.length())).append("\n\n");
        window.append(info).append("\n\n");

        if (list != null) {
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
        textboxesContent.put(name, scanner.nextLine());
    }

    public Ventana run() {
        update();
        final int separator = textboxes.size();

        while (true) {
            int option = dialogSelect();
            if (option < separator) dialogTextbox(textboxes.get(option));
            else return handle(buttons.get(option - separator));
        }
    }

    @Override
    public String toString() {
        return "Ventana{" +
                "title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", list=" + (list != null) +
                ", textboxes=" + textboxes +
                ", buttons=" + buttons +
                '}';
    }
}
