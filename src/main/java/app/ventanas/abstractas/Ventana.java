package app.ventanas.abstractas;

import app.Gestor;
import app.ventanas.interfaces.Button;
import app.ventanas.interfaces.Textbox;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.Semaphore;

import static helpers.estaticos.Arguments.*;


abstract public class Ventana {
    private final static Semaphore running = new Semaphore(1);
    protected final String title;
    protected final String info;
    protected final List<Textbox> textboxes;
    protected final List<Button> buttons;
    private final Map<Textbox, JTextField> textboxesContent;
    private List<String> list;
    private final JList<String> jlist;
    private Gestor gestor;

    private final JFrame frame;

    public Ventana(final String title, final String info, final boolean list, final List<Textbox> textboxes, final List<Button> buttons) {
        this.title = stringNotEmpty("Title", title);

        frame = new JFrame(this.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Container pane = frame.getContentPane();

        this.info = stringNotEmpty("Information", info);
        pane.add(new JLabel(this.info), BorderLayout.NORTH);

        if (list) {
            this.list = new LinkedList<>();
            jlist = new JList<>();
            pane.add(jlist);
        } else {
            this.list = null;
            jlist = null;
        }

        collectionWithoutNull("Textboxes", textboxes);
        this.textboxes = Collections.unmodifiableList(new LinkedList<>(textboxes));
        textboxesContent = new HashMap<>();

        //if (!this.textboxes.isEmpty()) {
        final JPanel ptextboxs = new JPanel(new GridLayout(this.textboxes.size(), 2));
        for (Textbox textbox : this.textboxes) {
            ptextboxs.add(new JLabel(textbox.getDescription()));
            final JTextField jtextbox = new JTextField(20);
            textboxesContent.put(textbox, jtextbox);
            ptextboxs.add(jtextbox);
        }
        pane.add(ptextboxs);
        //}

        collectionWithoutNull("Buttons", buttons);
        validate("Buttons no puede estar vacia", !buttons.isEmpty());
        this.buttons = Collections.unmodifiableList(new LinkedList<>(buttons));

        final JPanel pbuttons = new JPanel(new FlowLayout());
        for (Button button : buttons) {
            final JButton jbutton = new JButton(button.getDescription());
            jbutton.addActionListener(e -> {
                final Optional<Ventana> next = pressButton(button);
                frame.setVisible(false);
                getGestor().show(next.orElse(null));
            });
            pbuttons.add(jbutton);
        }
        pane.add(pbuttons, BorderLayout.SOUTH);

        clearTextboxes();

        frame.pack();
    }

    public Ventana(final String title, final String info, final boolean list, final Textbox[] textboxes, final Button[] buttons) {
        this(title, info, list, Arrays.asList(referenceNotNull("Textboxes", textboxes)), Arrays.asList(referenceNotNull("Buttons", buttons)));
    }

    protected void update() {
    }

    public abstract Optional<Ventana> pressButton(final Button button);

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
        return frame.getTitle();
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
        jlist.setListData(this.list.toArray(new String[0]));
    }

    final public List<Textbox> getTextboxes() {
        return textboxes;
    }

    final public String getTextbox(final Textbox name) {
        return textboxesContent.get(validateTextbox(name)).getText();
    }

    public void setTextbox(final Textbox name, final String content) {
        textboxesContent.get(validateTextbox(name)).setText(referenceNotNull("Content", content));
    }

    public void clearTextboxes() {
        for (Textbox name : textboxes) setTextbox(name, "");
    }

    final public List<Button> getButtons() {
        return buttons;
    }

    final public void show() {
        if (!running.tryAcquire()) {
            throw new OverlappingVentanaException();
        } else try {
            frame.setVisible(true);
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
