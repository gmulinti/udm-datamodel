package com.openplatform.udm.javamodel;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openplatform.udm.position.PositionOpening;

/**
 * The Class PositionSchemaTest.
 */
public class PositionSchemaTest {

	/** The Constant filePathPosition. */
	private static final String filePathPosition = "./target/json-schemas/sample/position.sample.json";	
	
	/**
	 * Test position schema.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testPositionSchema() throws IOException {
		//ASSIGN & ACT
		File file = new File(filePathPosition);		
		PositionOpening positionSchema = Transformer.fromJSON(file, PositionOpening.class);
		String outputJson = Transformer.toJSON(positionSchema);
		ObjectMapper diffMapper = new ObjectMapper();
		JsonNode tree1 = diffMapper.readTree(file);
		JsonNode tree2 = diffMapper.readTree(outputJson);
		
		//ASSERT
		org.junit.Assert.assertTrue(tree1.equals(tree2));		
	}
}
