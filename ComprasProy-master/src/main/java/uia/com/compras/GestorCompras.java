package uia.com.compras;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Fredy
 * @version 3.0
 * @created 20-nov.-2021
 */
public class GestorCompras {
    private int opcion;
    private ListaReportesNivelStock miReporteNS;
    private PeticionOrdenCompra miPeticionOC = new PeticionOrdenCompra();
    private PeticionOrdenCompra miSolicituOC ;
    private SolicitudCotizacion miSolicitudCot = new SolicitudCotizacion();
    private Comprador miComprador = new Comprador();
    private Vendedor miVendedor = new Vendedor();

    public GestorCompras() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            miReporteNS = mapper.readValue(new FileInputStream("C:\\Users\\Hp\\Documents\\IngenieriaSoftware\\ComprasProy-master\\arregloItemsV1.json"), ListaReportesNivelStock.class );

        }
        catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (miReporteNS != null)
        {
            miPeticionOC.agregaItems(miReporteNS);

            System.out.println("----- Items List -----");

            for(int i=0; i<miReporteNS.getItems().size(); i++) {
                InfoComprasUIA miNodo = miReporteNS.getItems().get(i);
                miNodo.print();
            }

            miComprador.hazSolicitudOrdenCompra(miPeticionOC);
        }

        miSolicituOC=miComprador.buscaVendedor(miPeticionOC);
        miComprador.agrupaVendedores(miSolicituOC);

        for (Entry<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>> item : miComprador.getVendedores().entrySet())
        {
            int iVendedor = item.getKey();
            HashMap<Integer, ArrayList<InfoComprasUIA>> nodo = item.getValue();
            mapper.writeValue(new File("C:\\Users\\Hp\\Documents\\IngenieriaSoftware\\ComprasProy-master\\SolicitudOrdenCompra-Vendedor-"+iVendedor+".json"), nodo);
        }
        if (miReporteNS != null)
        {
            miSolicituOC.agregaItems(miReporteNS);

            System.out.println("----- Items List -----");

            for(int i=0; i<miReporteNS.getItems().size(); i++) {
                InfoComprasUIA miNodo = miReporteNS.getItems().get(i);
                miNodo.print();
            }

            miVendedor.hazSolicitudCotizacion( miSolicitudCot);
        }

        miPeticionOC=miVendedor.buscaProveedor(miSolicitudCot);
        miVendedor.agrupaProveedores((SolicitudOrdenCompra) miPeticionOC);

        for (Entry<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>> item : miVendedor.getProveedores().entrySet())
        {
            int iProveedor = item.getKey();
            HashMap<Integer, ArrayList<InfoComprasUIA>> nodo = item.getValue();
            mapper.writeValue(new File("C:\\Users\\Hp\\Documents\\IngenieriaSoftware\\ComprasProy-master\\SolicitudCotizacion-"+iProveedor+".json"), nodo);
        }


    }


    public void print()
    {

    }
}//end KardexListaKClientes