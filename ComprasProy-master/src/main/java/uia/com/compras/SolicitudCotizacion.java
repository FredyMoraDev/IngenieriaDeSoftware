package uia.com.compras;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitudCotizacion extends SolicitudOrdenCompra {
    private int proveedor = -1;

    @JsonCreator
    public SolicitudCotizacion(@JsonProperty("id")int id,@JsonProperty("name")String name,
                               @JsonProperty("codigo")String codigo,@JsonProperty("unidad")String unidad,
                               @JsonProperty("cantidad")int cantidad,@JsonProperty("vendedor")int vendedor,
                               @JsonProperty("clasificacionProveedor")int clasificacionVendedor,
                               @JsonProperty("cotizacion")int proveedor){
        super(id, name, codigo, unidad, cantidad, vendedor, clasificacionVendedor);
        this.proveedor = proveedor;
    }

    public SolicitudCotizacion(SolicitudOrdenCompra info)
    {
        super();
        this.setClasificacion(info.getClasificacion());
    }

    public SolicitudCotizacion(){ }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor){
        this.proveedor = proveedor;
    }

}



