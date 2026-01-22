package com.example.ProyectoSpringboot;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSpringboot.modelo.Centros;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("/centros")
public class ControladorCentros {

	@GetMapping
	public List<Centros> obtenerCentros() {

	    List<Centros> listaCentros = new ArrayList<>();

	    try {
	    	InputStream is = getClass().getResourceAsStream("/centros.json");
	    	JsonArray array = JsonParser.parseReader(new InputStreamReader(is))
	    	                            .getAsJsonObject()
	    	                            .getAsJsonArray("CENTROS");


	        for (JsonElement elemento : array) {

	            JsonObject o = elemento.getAsJsonObject();
	            Centros c = new Centros();

	            c.setCCEN(safeInt(o, "CCEN"));
	            c.setNOM(o.get("NOM").getAsString());
	            c.setNOME(o.get("NOME").getAsString());
	            c.setDGENRC(o.get("DGENRC").getAsString());
	            c.setDGENRE(o.get("DGENRE").getAsString());
	            c.setGENR(o.get("GENR").getAsString());
	            c.setMUNI(safeInt(o, "MUNI"));
	            c.setDMUNIC(o.get("DMUNIC").getAsString());
	            c.setDMUNIE(o.get("DMUNIE").getAsString());
	            c.setDTERRC(o.get("DTERRC").getAsString());
	            c.setDTERRE(o.get("DTERRE").getAsString());
	            c.setDEPE(safeInt(o, "DEPE"));
	            c.setDTITUC(o.get("DTITUC").getAsString());
	            c.setDTITUE(o.get("DTITUE").getAsString());
	            c.setDOMI(o.get("DOMI").getAsString());
	            c.setCPOS(safeInt(o, "CPOS"));
	            c.setTEL1(safeLong(o, "TEL1"));
	            c.setTFAX(safeLong(o, "TFAX"));
	            c.setEMAIL(o.get("EMAIL").getAsString());
	            c.setPAGINA(o.get("PAGINA").getAsString());
	            c.setCOOR_X(o.get("COOR_X").getAsString());
	            c.setCOOR_Y(o.get("COOR_Y").getAsString());
	            c.setLATITUD(safeDouble(o, "LATITUD"));
	            c.setLONGITUD(safeDouble(o, "LONGITUD"));

	            listaCentros.add(c);
	        }


	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return listaCentros;
	}
	
	
	//ALGUNOS CAMPOS ESTAN VACIOS Y EN EL PARSEO DA ERROR
	//ESTOS METODOS SIRVEN PARA COMPROBAR SI EL CAMPO ESTA VACIO
	private int safeInt(JsonObject objeto, String atributo) {
		int valor = 0;
	    try {
	        if (!objeto.has(atributo) || objeto.get(atributo).isJsonNull()) {
	        	return valor;
	        }
	        String value = objeto.get(atributo).getAsString().trim();
	        if (value.isEmpty()) {
	        	return valor;
	        }
	        return Integer.parseInt(value);
	    } catch (Exception e) {
	        return valor;
	    }
	}

	private long safeLong(JsonObject objeto, String atributo) {
		long valor = 0L;
	    try {
	        if (!objeto.has(atributo) || objeto.get(atributo).isJsonNull()) {
	        	return valor;
	        }
	        String value = objeto.get(atributo).getAsString().trim();
	        if (value.isEmpty()) {
	        	return valor;
	        }
	        return Long.parseLong(value);
	    } catch (Exception e) {
	        return valor;
	    }
	}

	private double safeDouble(JsonObject objeto, String atributo) {
		double valor = 0.0;
	    try {
	        if (!objeto.has(atributo) || objeto.get(atributo).isJsonNull()) {
	        	return valor;
	        }
	        String value = objeto.get(atributo).getAsString().trim();
	        if (value.isEmpty()) {
	        	return valor;
	        }
	        return Double.parseDouble(value);
	    } catch (Exception e) {
	        return valor;
	    }
	}

	

}
