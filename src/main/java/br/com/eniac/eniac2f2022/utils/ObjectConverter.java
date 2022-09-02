package br.com.eniac.eniac2f2022.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.eniac.eniac2f2022.enums.OutputTypes;
import br.com.eniac.eniac2f2022.objects.DemoObject;

public class ObjectConverter {

	public static String writeAsString(OutputTypes type, Object object) throws Exception {
		if(type == OutputTypes.csv || type == OutputTypes.CSV) {
			CsvMapper csvMapper = new CsvMapper();
			try {
				CsvSchema schema = csvMapper.schemaFor(DemoObject.class);
				ObjectWriter writer = csvMapper.writerFor(DemoObject.class).with(schema);
				return writer.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		if(type == OutputTypes.xml || type == OutputTypes.XML) {
			XmlMapper xmlMapper = new XmlMapper();
			try {
				return xmlMapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(type == OutputTypes.json || type == OutputTypes.JSON) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {return objectMapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		throw new Exception("Error while converting");
		
	}
	
}
