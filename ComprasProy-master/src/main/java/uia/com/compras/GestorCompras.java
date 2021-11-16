package uia.com.compras;
import java.io.*;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Fredy Mora
 * @version 2.0
 */
public class GestorCompras {
	private int opcion;
	//private ListaKClientes miReportname = "Cartucho Tóner"eNS;
    private ListaReportesNivelStock miReporteNS;
    private PeticionOrdenCompra miPeticionOC = new PeticionOrdenCompra();
    private SolicitudOrdenCompra misolicitudOC = new SolicitudOrdenCompra();

	public GestorCompras() {
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			miReporteNS = mapper.readValue(new FileInputStream("C:\\Users\\Hp\\Documents\\ComprasProy-master\\arregloItemsV1.json"), ListaReportesNivelStock.class );

        }catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (miReporteNS != null){
            miPeticionOC.agregaItems(miReporteNS);
            System.out.println("----- Items List -----");
            for(int i=0; i<miReporteNS.getItems().size(); i++) {
                InfoComprasUIA miNodo = miReporteNS.getItems().get(i);
                miNodo.print();
            }
            try {
                mapper.writeValue(new FileOutputStream("C:\\Users\\Hp\\Documents\\ComprasProy-master\\peticionOrdenCompraV2.json"), miPeticionOC);
            }catch (JsonParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (IOException e) {
                // TODO Auto-generated catch block              e.printStackTrace();
            }
        }
        if (miPeticionOC != null){
            misolicitudOC.agregaItems(miReporteNS);
            System.out.println("----- Items List -----");
            for(int i=0; i< miReporteNS.getItems().size(); i++) {
                InfoComprasUIA miNodo = miReporteNS.getItems().get(i);
                miNodo.print();
            }
            try {
                mapper.writeValue(new FileOutputStream("C:\\Users\\Hp\\Documents\\ComprasProy-master\\OrdenCompra3.json"), misolicitudOC);
            }catch (JsonParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (IOException e) {
                // TODO Auto-generated catch block              e.printStackTrace();
            }
        }
    }
    public void print(){
    }
}//end KardexListaKClientes