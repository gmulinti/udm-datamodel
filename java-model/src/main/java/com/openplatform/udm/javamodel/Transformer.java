package com.openplatform.udm.javamodel;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * The Class Transformer.
 */
public class Transformer {
	
	/**
	 * Converts the JSON text contained in a file into a UDM Object.
	 *
	 * @param <T> the generic type
	 * @param file the file containing the JSON text
	 * @param clazz The UDM class that this object represents
	 * @return An instance of the UDM object
	 * @throws IOException if any deserialization errors occur
	 */
	public static <T> T fromJSON(File file,Class<T> clazz ) throws IOException {		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(file, clazz);		
	}
	
	/**
	 * Convert this UDM Object to a JSON string.
	 *
	 * @param obj the object to be converted
	 * @return a String representation of the Object in JSON
	 * @throws IOException if any serialization errors occur
	 */
	public static String toJSON(Object obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);		
		String jsonText = mapper.writeValueAsString(obj);		
		return jsonText;		
	}
}
