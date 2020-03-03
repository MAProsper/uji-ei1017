package App.Ventanas;

import App.Gestor;
import com.google.common.base.Strings;
import com.google.common.collect.Range;

import java.util.*;

import static Helpers.ValidatorArguments.*;


abstract public class Ventana {
    private final static Scanner scanner = new Scanner(System.in);
    private final String title;
    private final String info;
    private final List<String> textboxes;
    private final Map<String, String> textboxesContent;
    private final List<String> buttons;
    private final List<String> list;
    private static Gestor gestor;

    public Ventana(final String title, final String info, final boolean list, final List<String> textboxes, final List<String> buttons) {
        this.title = stringNotEmpty("header", title);
        this.info = stringNotEmpty("info", info);
        this.list = list ? new LinkedList<>() : null;
        this.textboxes = collectionWithoutNull("textboxes", textboxes);

        collectionWithoutNull("buttons", buttons);
        validate("buttons no puede estar vacia", !buttons.isEmpty());
        this.buttons = buttons;

        textboxesContent = new HashMap<>();
        if (!textboxes.isEmpty()) clearTextboxes();
    }

    abstract public void update();

    abstract public Ventana handle(final String button);

    private List<String> validateList() {
        return validate("list no esta definida", list, list != null);
    }


    private String validateTextbox(final String name) {
        return validate("textbox " + name + " no esta definida", referenceNotNull("name", name), textboxes.contains(name));
    }

    final public Gestor getGestor() {
        return validate("gestor no esta asignado", gestor, gestor != null);
    }

    final public void setGestor(final Gestor gestor) {
        Ventana.gestor = referenceNotNull("gestor", gestor);
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


    final public List<String> getTextboxes() {
        return Collections.unmodifiableList(textboxes);
    }

    final public String getTextbox(final String name) {
        return textboxesContent.get(validateTextbox(name));
    }

    public void setTextbox(final String name, final String content) {
        textboxesContent.put(validateTextbox(name), referenceNotNull("content", content));
    }

    public void clearTextboxes() {
        validate("textboxes no esta definido", !textboxes.isEmpty());
        for (String name : textboxes) setTextbox(name, "");
    }

    final public List<String> getButtons() {
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
            for (String name : textboxes)
                window.append(String.format("(%d) %s: %s\n", index++, name, textboxesContent.get(name)));
            window.append("\n");
        }

        for (String button : buttons) window.append(String.format("(%d) %s\n", index++, button));
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

    private void dialogTextbox(final String name) {
        renderWindow();
        System.out.format("Contenido de %s: ", name);
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
