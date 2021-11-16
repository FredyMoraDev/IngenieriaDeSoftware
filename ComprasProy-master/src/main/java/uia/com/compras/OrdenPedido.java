package uia.com.compras;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class OrdenPedido extends InfoComprasUIA  {
    private int cantidad;
    private String unidad="";
    private String codigo="";


    @JsonCreator
    public OrdenPedido(@JsonProperty("id")int id, @JsonProperty("name")String name,
                       @JsonProperty("codigo")String codigo, @JsonProperty("unidad")String unidad,
                       @JsonProperty("cantidad")int cantidad, String tipo)
    {
        super(id, name);
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.codigo = codigo;
        this.setType(tipo);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public OrdenPedido() {
        super(-1, "");
    }

    public void agregaItems(ListaReportesNivelStock miReporteNS)
    {
        OrdenPedido nodo;
        for(int i=0; i<miReporteNS.getItems().size(); i++)
        {
            InfoComprasUIA miNodo = miReporteNS.getItems().get(i);
            List<InfoComprasUIA> miLista;
            if(miNodo.getPedidoProveedor() > 0)
            {
                nodo = new OrdenPedido(miNodo.getId(), miNodo.getName(), miNodo.getDescripcion(),
                        "PZA", miNodo.getPedidoProveedor(), "itemOP");
                if(this.getItems() == null)
                {
                    miLista = new ArrayList<InfoComprasUIA>();
                    this.setItems((List<InfoComprasUIA>) miLista);
                }
                this.getItems().add(nodo);
            }
            miNodo.print();
        }

    }
}
