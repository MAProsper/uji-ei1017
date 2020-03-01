package App;

import App.Ventanas.*;
import Clientes.Cliente;
import Clientes.ClienteEmpresa;
import Clientes.ClientePaticular;
import Helpers.Factura;
import Helpers.Llamada;

import java.util.*;

import static Helpers.ValidatorArguments.referenceNotNull;
import static Helpers.ValidatorArguments.validate;

public class Gestor {
    final Stack<Ventana> stack;
    final HashMap<String, Cliente> clientes;
    final HashMap<Integer, Cliente> facturas;
    Cliente clienteSelecionado;

    public Gestor() {
        this.stack = new Stack<>();
        this.clientes = new HashMap<>();
        this.facturas = new HashMap<>();
        clienteSelecionado = null;
    }

    public List<Cliente> getClientes() {
        return Collections.unmodifiableList(new LinkedList<>(clientes.values()));
    }

    public Cliente getCliente(final String NIF) {
        return clientes.get(NIF);
    }

    public Cliente getCliente(final int codigo) {
        return facturas.get(codigo);
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(final Cliente cliente) {
        this.clienteSelecionado = cliente;
    }

    public void addCliente(final Cliente cliente) {
        referenceNotNull("cliente", cliente);
        clientes.put(cliente.getNIF(), cliente);

        final Cliente selecionado = getClienteSelecionado();
        setClienteSelecionado(cliente);
        for (Factura factura : cliente.getFacturas()) addFactura(factura);
        setClienteSelecionado(selecionado);
    }

    public void addLlamada(final Llamada llamada) {
        referenceNotNull("clienteSelecionado", clienteSelecionado);
        clienteSelecionado.addLlamada(referenceNotNull("llamada", llamada));
    }

    public void addFactura(final Factura factura) {
        referenceNotNull("clienteSelecionado", clienteSelecionado);
        clienteSelecionado.addFactura(referenceNotNull("factura", factura));
        facturas.put(factura.getCodigo(), clienteSelecionado);
    }

    public Ventana getVisor(final Cliente cliente) {
        referenceNotNull("cliente", cliente);
        if (cliente instanceof ClientePaticular) return new VentanaClienteParticular();
        else if (cliente instanceof ClienteEmpresa) return new VentanaClienteEmpresa();
        else return new VentanaCliente();
    }

    public void removeCliente() {
        final Cliente cliente = getClienteSelecionado();
        clientes.remove(cliente.getNIF());
        for (Factura factura : cliente.getFacturas()) facturas.remove(factura.getCodigo());
        clienteSelecionado = null;
    }

    public void run() {
        validate("gestor ya esta en uso", stack.empty());

        stack.push(new VentanaPrincipal());
        while (!stack.empty()) {
            Ventana result = stack.peek().run(this);
            if (result == null) stack.pop();
            else stack.push(result);
        }
    }

    @Override
    public String toString() {
        return "Gestor{" +
                "clientes=" + clientes.keySet() +
                '}';
    }
}
