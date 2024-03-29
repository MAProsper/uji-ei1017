package clientes;

import helpers.clases.Direccion;
import helpers.clases.Factura;
import helpers.clases.Llamada;
import helpers.interfaces.Cronologico;
import tarifas.Tarifa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import static helpers.estaticos.Arguments.*;

public class Cliente implements Cronologico, Serializable {
    private static final long serialVersionUID = -9007760874300103710L;
    private final static Set<String> NIFs = new HashSet<>();
    protected final String NIF;
    protected final String nombre;
    protected final Direccion direccion;
    protected final String correo;
    protected final LocalDateTime fechaAlta;
    protected final List<Llamada> llamadas;
    protected final List<Factura> facturas;
    protected Tarifa tarifa;

    public Cliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
        validate("NIF del cliente repetido", !NIFs.contains(NIF));
        this.NIF = stringMatchesPattern("NIF", NIF, "\\d+[TRWAGMYFPDXBNJZSQVHLCKE]");
        this.nombre = stringNotEmpty("Nombre", nombre);
        this.direccion = referenceNotNull("Direccion", direccion);
        this.correo = stringMatchesPattern("Correo", correo, "[\\w.]+@[\\w.]{2,}");
        this.fechaAlta = referenceNotNull("Fecha de alta", fechaAlta);
        setTarifa(tarifa);
        llamadas = new LinkedList<>();
        facturas = new LinkedList<>();
        NIFs.add(NIF);
    }

    final public String getNombre() {
        return nombre;
    }

    final public String getNIF() {
        return NIF;
    }

    final public Direccion getDireccion() {
        return direccion;
    }

    final public String getCorreo() {
        return correo;
    }

    final public LocalDateTime getFecha() {
        return fechaAlta;
    }

    final public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(final Tarifa tarifa) {
        this.tarifa = referenceNotNull("Tarifa", tarifa);
    }

    final public List<Llamada> getLlamadas() {
        return Collections.unmodifiableList(llamadas);
    }

    public void addLlamada(final Llamada llamada) {
        referenceNotNull("Llamada", llamada);
        validate("Llamada repetida en el cliente", !llamadas.contains(llamada));
        llamadas.add(llamada);
    }

    final public List<Factura> getFacturas() {
        return Collections.unmodifiableList(facturas);
    }

    public void addFactura(final Factura factura) {
        referenceNotNull("Factura", factura);
        validate("Factura repetida en el cliente", !facturas.contains(factura));
        facturas.add(factura);
    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(NIF, cliente.NIF);
    }

    @Override
    final public int hashCode() {
        return Objects.hash(NIF);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "NIF='" + getNIF() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", direccion=" + getDireccion() +
                ", correo='" + getCorreo() + '\'' +
                ", fechaAlta=" + getFecha() +
                ", tarifa=" + getTarifa() +
                ", llamadas=" + getLlamadas() +
                ", facturas=" + getFacturas() +
                '}';
    }
}
