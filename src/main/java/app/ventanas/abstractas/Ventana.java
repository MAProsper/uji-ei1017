package app.ventanas.abstractas;

import app.ventanas.interfaces.Button;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.*;

import static helpers.estaticos.Arguments.*;

// Vista (abstracta para ventanas propias)
abstract public class Ventana extends Gestionable {
    protected final List<Table> table;

    protected final String title;
    protected final String info;
    private final JFrame jFrame;
    private final JTable tableContent;
    private final Map<Textbox, JTextField> textboxesContent;
    protected final List<Textbox> textboxes;
    protected final List<Button> buttons;

    public Ventana(final String title, final String info, final List<Table> table, final List<Textbox> textboxes, final List<Button> buttons) {
        super();
        this.title = stringNotEmpty("Title", title);
        this.info = stringNotEmpty("Information", info);

        collectionWithoutNull("Table", table);
        this.table = Collections.unmodifiableList(new LinkedList<>(table));
        tableContent = new JTable();

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
        tableContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableContent.setAutoCreateRowSorter(true);
        return new JScrollPane(tableContent);
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

                //Controlador.pressButton (1. avisar de acción del usuario)
                showNext(pressButton(button).orElse(null));
            });
            panel.add(jbutton);
        }
        return panel;
    }

    private JFrame panelWindow() {
        final JFrame window = new JFrame(this.title);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                getGestor().getCloseOperation().run();
            }
        });

        final Container panelContent = window.getContentPane();
        final JPanel panelNorth = new JPanel(new FlowLayout());
        final JPanel panelCenter = new JPanel(new BorderLayout());
        panelContent.add(panelNorth, BorderLayout.NORTH);
        panelContent.add(panelCenter);

        panelNorth.add(new JLabel(this.info));
        if (!table.isEmpty()) panelCenter.add(panelTable());
        panelCenter.add(panelTextboxes(), BorderLayout.SOUTH);
        panelContent.add(panelButton(), BorderLayout.SOUTH);

        clearTextboxes();
        window.pack();

        //Los botones se pierden si reduces la ventana
        window.setMinimumSize(window.getSize());

        return window;
    }

    private List<List<String>> validateTable(final List<List<String>> table) {
        collectionWithoutNull("Tabla", table);

        for (List<String> row : table) {
            collectionWithoutNull("Fila", row);
            validate("Fila no tiene un numero adecuado de columnas", row.size() == this.table.size());
        }

        return table;
    }

    private Textbox validateTextbox(final Textbox name) {
        return validate("Textbox " + name + " no esta definida", referenceNotNull("Name", name), textboxes.contains(name));
    }

    final public void show() { // Gestiona la notificacion del modelo
        super.show();
        update();
        jFrame.setVisible(true);
    }

    protected void update() {
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

    // Metodos para el Controlador (informase de la vista)
    public abstract Optional<Gestionable> pressButton(final Button button); // Gestiona la accion del usuario

    final public String getTitle() {
        return title;
    }

    final public String getInfo() {
        return info;
    }

    final public List<Table> getTable() {
        return table;
    }

    final public Optional<List<String>> getSelectedRow() {
        final int selectedRow = this.tableContent.getSelectedRow();
        if (selectedRow >= 0) {
            final TableModel data = this.tableContent.getModel();
            final List<String> row = new LinkedList<>();
            for (int i = 0; i < table.size(); i++)
                row.add((String) data.getValueAt(selectedRow, i));
            return Optional.of(row);
        } else {
            return Optional.empty();
        }
    }

    final public List<Textbox> getTextboxes() {
        return textboxes;
    }

    final public String getTextbox(final Textbox name) {
        return textboxesContent.get(validateTextbox(name)).getText();
    }

    final public List<Button> getButtons() {
        return buttons;
    }

    // Metodos para el Modelo (informar de cambios a la vista)
    public void setTable(final String[][] table) {
        referenceNotNull("Table", table);
        List<List<String>> vTable = new LinkedList<>();
        for (String[] row : table) vTable.add(Arrays.asList(row));
        setTable(vTable);
    }

    public void setTable(final List<List<String>> table) {
        referenceNotNull("Table", table);
        Vector<Vector<Object>> vTable = new Vector<>();
        for (List<String> row : table) vTable.add(new Vector<>(row));
        this.tableContent.setModel(new DefaultTableModel(vTable, new Vector<>(this.table)));
    }

    public void setTextbox(final Textbox name, final String content) {
        textboxesContent.get(validateTextbox(name)).setText(referenceNotNull("Content", content));
    }

    public void clearTextboxes() {
        for (Textbox name : textboxes) setTextbox(name, "");
    }
}
