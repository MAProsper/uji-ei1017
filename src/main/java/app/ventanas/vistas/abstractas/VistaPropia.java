package app.ventanas.vistas.abstractas;

import app.componentes.Accion;
import app.componentes.Table;
import app.componentes.Textbox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.*;

import static helpers.estaticos.Arguments.*;

abstract public class VistaPropia extends Vista {
    private final JFrame jFrame;
    protected final String title;
    protected final String info;
    protected final List<Table> table;
    private final JTable tableContent;
    private final Map<Textbox, JTextField> textboxesContent;
    protected final List<Textbox> textboxes;
    protected final List<Accion> accions;

    public VistaPropia(final String title, final String info, final List<Table> table, final List<Textbox> textboxes, final List<Accion> accions) {
        super();
        this.title = stringNotEmpty("Title", title);
        this.info = stringNotEmpty("Information", info);

        collectionWithoutNull("Table", table);
        this.table = Collections.unmodifiableList(new LinkedList<>(table));
        tableContent = new JTable();

        collectionWithoutNull("Textboxes", textboxes);
        this.textboxes = Collections.unmodifiableList(new LinkedList<>(textboxes));
        textboxesContent = new HashMap<>();

        collectionWithoutNull("Buttons", accions);
        validate("Buttons no puede estar vacia", !accions.isEmpty());
        this.accions = Collections.unmodifiableList(new LinkedList<>(accions));

        jFrame = panelWindow();
    }

    public VistaPropia(final String title, final String info, final Table[] table, final Textbox[] textboxes, final Accion[] accions) {
        this(title, info, Arrays.asList(referenceNotNull("Table", table)), Arrays.asList(referenceNotNull("Textboxes", textboxes)), Arrays.asList(referenceNotNull("Buttons", accions)));
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
        for (Accion accion : accions) {
            final JButton jbutton = new JButton(accion.getDescription());
            jbutton.addActionListener(e -> {
                hide();
                getControlador().gestionaAccion(accion);
            });
            panel.add(jbutton);
        }
        return panel;
    }

    @Override
    public void hide() {
        super.hide();
        jFrame.setVisible(false);
    }

    private JFrame panelWindow() {
        final JFrame window = new JFrame(this.title);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                getControlador().gestionaClose();
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

        // Los botones se pierden si reduces la ventana
        window.setMinimumSize(window.getSize());

        return window;
    }

    private Textbox validateTextbox(final Textbox name) {
        return validate("Textbox " + name + " no esta definida", referenceNotNull("Name", name), textboxes.contains(name));
    }

    final public void show() {
        super.show();
        jFrame.setVisible(true);
    }

    @Override
    public String toString() {
        return "VistaPropia{" +
                "title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", table=" + table +
                ", textboxes=" + textboxes +
                ", accions=" + accions +
                ", vista=" + super.toString() +
                "}";
    }

    private static class TableModel extends DefaultTableModel {
        private static final long serialVersionUID = -3918709434546037714L;

        public TableModel(List<List<String>> table, List<Table> header) {
            super(tableAdapter(validateTable(table, header.size())), headerAdapter(header));
        }

        private static Vector<Object> headerAdapter(final List<Table> header) {
            final Vector<Object> vHeader = new Vector<>();
            for (Table column : header) vHeader.add(column.getDescription());
            return vHeader;
        }

        private static List<List<String>> validateTable(final List<List<String>> table, int size) {
            collectionWithoutNull("Tabla", table);
            for (List<String> row : table) {
                collectionWithoutNull("Fila", row);
                validate("Fila no tiene un numero adecuado de columnas", row.size() == size);
            }
            return table;
        }

        private static Vector<Vector<Object>> tableAdapter(final List<List<String>> table) {
            Vector<Vector<Object>> vTable = new Vector<>();
            for (List<String> row : table) vTable.add(new Vector<>(row));
            return vTable;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
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

    final public Optional<List<String>> getSelectedRow() {
        final int selectedRow = this.tableContent.getSelectedRow();
        if (selectedRow >= 0) {
            final javax.swing.table.TableModel data = this.tableContent.getModel();
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

    final public List<Accion> getButtons() {
        return accions;
    }

    public void setTable(final String[][] table) {
        referenceNotNull("Table", table);
        List<List<String>> vTable = new LinkedList<>();
        for (String[] row : table) vTable.add(Arrays.asList(row));
        setTable(vTable);
    }

    public void setTable(final List<List<String>> table) {
        this.tableContent.setModel(new TableModel(table, this.table));
    }

    public void setTextbox(final Textbox name, final String content) {
        textboxesContent.get(validateTextbox(name)).setText(referenceNotNull("Content", content));
    }

    public void clearTextboxes() {
        for (Textbox name : textboxes) setTextbox(name, "");
    }
}
