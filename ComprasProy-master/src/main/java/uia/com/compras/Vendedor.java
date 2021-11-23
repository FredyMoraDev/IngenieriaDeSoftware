package uia.com.compras;

import java.util.ArrayList;
import java.util.HashMap;

public class Vendedor {
    private int Precio = 0;

    protected HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>> proveedores = new HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>>();

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>> getProveedores() {
        return proveedores;
    }

    public void setProveedores(HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>> proveedores) {
        this.proveedores = proveedores;
    }

    public void hazSolicitudCotizacion(SolicitudOrdenCompra miSolicitudOC) {
        validaExistencia(miSolicitudOC);
    }

    private void validaExistencia(SolicitudOrdenCompra miSolicitudOC) {
        for (int i = 0; i < miSolicitudOC.getItems().size(); i++) {
            validaUso((SolicitudOrdenCompra) miSolicitudOC.getItems().get(i), i);
        }
    }

    private void validaUso(SolicitudOrdenCompra miSolicitudOC, int i) {
        switch (i % 3) {
            case 0:
                miSolicitudOC.setClasificacion(0);
                break;
            case 1:
                miSolicitudOC.setClasificacion(1);
                break;
            case 2:
                miSolicitudOC.setClasificacion(2);
                break;
        }
    }

    public SolicitudCotizacion buscaProveedor(SolicitudOrdenCompra miSolicitudOC) {

        SolicitudCotizacion miCotizacionOC = new SolicitudCotizacion();

        for (int i = 0; i < miSolicitudOC.getItems().size(); i++) {
            SolicitudCotizacion item;
            item = new SolicitudCotizacion((SolicitudOrdenCompra) miSolicitudOC.getItems().get(i));
            if (buscaProveedor((SolicitudCotizacion) item, i) >= 0) {
                if (miSolicitudOC.getItems() == null)
                    miSolicitudOC.setItems(new ArrayList<InfoComprasUIA>());
                miSolicitudOC.getItems().add(item);
            }
        }
        if (miSolicitudOC != null)
            return miCotizacionOC;
        else
            return null;

    }

    private int buscaProveedor(SolicitudOrdenCompra solicitudOC, int i) {
        switch (i % 3) {
            case 0:
                solicitudOC.setVendedor(0);
                return i;
            case 1:
                solicitudOC.setVendedor(1);
                return i;
            case 2:
                solicitudOC.setVendedor(2);
                return i;
        }
        return -1;
    }

    public void agrupaProveedores(SolicitudOrdenCompra proveedorOC) {
        SolicitudCotizacion newItem = null;
        int key = 0;
        int keyLista = -1;

        if (proveedores == null)
            proveedores = new HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>>();

        for (int i = 0; i < proveedorOC.getItems().size(); i++) {
            newItem = (SolicitudCotizacion) proveedorOC.getItems().get(i);
            key = newItem.getProveedor();
            keyLista = i % 3;

            if (proveedores.containsKey(key)) {
                if (proveedores.get(key).containsKey(keyLista)) {
                    proveedores.get(key).get(keyLista).add(newItem);
                } else {
                    ArrayList<InfoComprasUIA> newLista = new ArrayList<InfoComprasUIA>();
                    newLista.add(newItem);
                    HashMap<Integer, ArrayList<InfoComprasUIA>> nodo = new HashMap<Integer, ArrayList<InfoComprasUIA>>();
                    nodo.put(i, newLista);
                    proveedores.put(key, nodo);
                }
            } else {
                ArrayList<InfoComprasUIA> newLista = new ArrayList<InfoComprasUIA>();
                newLista.add(newItem);
                HashMap<Integer, ArrayList<InfoComprasUIA>> nodo = new HashMap<Integer, ArrayList<InfoComprasUIA>>();
                nodo.put(keyLista, newLista);
                proveedores.put(key, nodo);
            }
        }
    }
}