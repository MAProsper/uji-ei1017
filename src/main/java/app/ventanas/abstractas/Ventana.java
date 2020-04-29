package app.ventanas.abstractas;

import app.Gestor;
import app.ventanas.interfaces.Button;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.Semaphore;

import static helpers.estaticos.Arguments.*;

abstract public class Ventana {
    protected final List<Table> table;
    private final JFrame jFrame;
    private final static Semaphore running = new Semaphore(1);

    protected final String title;
    protected final String info;
    private final DefaultTableModel tableContent;
    private final Map<Textbox, JTextField> textboxesContent;
    protected final List<Textbox> textboxes;
    private Gestor gestor;
    protected final List<Button> buttons;

    public Ventana(final String title, final String info, final List<Table> table, final List<Textbox> textboxes, final List<Button> buttons) {
        this.title = stringNotEmpty("Title", title);
        this.info = stringNotEmpty("Information", info);

        collectionWithoutNull("Table", table);
        this.table = Collections.unmodifiableList(new LinkedList<>(table));
        tableContent = new DefaultTableModel(new String[][]{}, table.toArray());

        collectionWithoutNull("Textboxes", textboxes);
        this.textboxes = Collections.unmodifiableList(new LinkedList<>(textboxes));
        textboxesContent = new HashMap<>();

        collectionWithoutNull("Buttons", buttons);
        validate("Buttons no puede estar vacia", !buttons.isEmpty());
        this.buttons = Collections.unmodifiableList(new LinkedList<>(buttons));

        jFrame = panelWindow();
    }

    public Ventana(final String title, final String info, final Table[] table, final Textbox[] textboxes, final Button[] buttons) {
        this(title, info, Arrays.asList(referenceNotNull("Table", table)), Arrays.asList(referenceNotNull("Textboxes", textboxes)), Arrays.asList(referenceNotNull("Buttons", buttons)));
    }

    private Component panelTable() {
        final JTable table = new JTable(tableContent);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);

        final Dimension size = table.getPreferredScrollableViewportSize();
        final int height = Math.min(size.height, table.getRowHeight() * 10);
        table.setPreferredScrollableViewportSize(new Dimension(size.width, height));

        return new JScrollPane(table);
    }

    private Component panelTextboxes() {
        final JPanel panel = new JPanel(new GridLayout(this.textboxes.size(), 2));
        for (Textbox textbox : this.textboxes) {
            panel.add(new JLabel(textbox.getDescription()));
            final JTextField jtextbox = new JTextField(20);
            textboxesContent.put(textbox, jtextbox);
            panel.add(jtextbox);
        }
        return panel;
    }

    private Component panelButton() {
        final JPanel panel = new JPanel();
        for (Button button : buttons) {
            final JButton jbutton = new JButton(button.getDescription());
            jbutton.addActionListener(e -> {
                jFrame.setVisible(false);
                running.release();
                getGestor().showNext(pressButton(button).orElse(null));
            });
            panel.add(jbutton);
        }
        return panel;
    }

    private JFrame panelWindow() {
        final JFrame window = new JFrame(this.title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Container panelContent = window.getContentPane();
        final JPanel panelCenter = new JPanel(new BorderLayout());
        panelContent.add(panelCenter);

        panelContent.add(new JLabel(this.info), BorderLayout.NORTH);
        panelCenter.add(panelTable());
        panelCenter.add(panelTextboxes(), BorderLayout.SOUTH);
        panelContent.add(panelButton(), BorderLayout.SOUTH);

        clearTextboxes();
        window.pack();

        //Los botones se pierden si reduces la ventana
        window.setMinimumSize(window.getSize());

        return window;
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

    private String[][] validateTable(final String[][] table) {
        collectionWithoutNull("Tabla", table);

        for (String[] row : table) {
            collectionWithoutNull("Fila", row);
            validate("Fila no tiene un numero adecuado de columnas", row.length == this.table.size());
        }

        return table;
    }

    private Textbox validateTextbox(final Textbox name) {
        return validate("Textbox " + name + " no esta definida", referenceNotNull("Name", name), textboxes.contains(name));
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

    final public List<Table> getTable() {
        return table;
    }

    public void setTable(final String[][] table) {
        this.tableContent.setDataVector(validateTable(table), this.table.toArray());
    }

    public void setTable(final List<List<String>> table) {
        referenceNotNull("Table", table);
        String[][] vTable = new String[table.size()][this.table.size()];
        for (int i = 0; i < table.size(); i++)
            vTable[i] = referenceNotNull("Fila", table.get(i)).toArray(new String[0]);
        setTable(vTable);
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
        } else {
            update();
            jFrame.setVisible(true);
        }
    }

    @Override
    public String toString() {
        return "Ventana{" +
                "title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", table=" + table +
                ", textboxes=" + textboxes +
                ", buttons=" + buttons +
                '}';
    }

    public static class OverlappingVentanaException extends IllegalStateException {
        private static final long serialVersionUID = -4234327239149123858L;
    }
}
