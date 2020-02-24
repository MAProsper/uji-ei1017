package Clientes;

import Direcciones.Direccion;

import java.util.Date;

public class ClienteEmpresa extends Cliente {
    public ClienteEmpresa(String nombre, String NIF, Direccion direccion, String correo, Date fechaAlta) {
        super(nombre, NIF, direccion, correo, fechaAlta);
    }
}
