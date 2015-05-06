package com.openplatform.udm.javamodel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openplatform.udm.common.UserArea;
import com.openplatform.udm.organization.OrganizationChart;
import com.openplatform.udm.organization.OrganizationUnit;
import com.openplatform.udm.organization.ParentOrganizationUnit;

/**
 * The Class OrganizationSchemaTest.
 */
public class OrganizationSchemaTest {

	/** The Constant filePathOrganization. */
	private static final String filePathOrganization = "./target/json-schemas/sample/organization.sample.json";
	
	/**
	 * Test organization schema.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testOrganizationSchema() throws IOException {
		//ASSIGN & ACT
		File file = new File(filePathOrganization);		
		OrganizationChart organizationSchema = Transformer.fromJSON(file, OrganizationChart.class);
		String outputJson = Transformer.toJSON(organizationSchema);
		ObjectMapper diffMapper = new ObjectMapper();
		JsonNode tree1 = diffMapper.readTree(file);
		JsonNode tree2 = diffMapper.readTree(outputJson);
		
		//ASSERT
		org.junit.Assert.assertTrue(tree1.equals(tree2));		
	}
	
	/**
	 * Populated java model to json.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void populatedJavaModelToJson() throws IOException {		
		//ASSIGN & ACT
		OrganizationChart organizationChart = new OrganizationChart();
		
		OrganizationUnit organizationUnit = new OrganizationUnit();
		
		ParentOrganizationUnit parentOrganizationUnit = new ParentOrganizationUnit();		
		parentOrganizationUnit.setOrganizationUnitCode("ER3032");
		UserArea parentOrganizationUserArea = new UserArea();
		parentOrganizationUserArea.setKey("organizationUnitOtherName");
		parentOrganizationUserArea.setValue("Lumesse Engineering Department");
		parentOrganizationUnit.setUserArea(Arrays.asList(new UserArea[]{parentOrganizationUserArea}));
		
		UserArea organizationUserArea = new UserArea();
		organizationUserArea.setKey("organizationUnitOtherName");
		organizationUserArea.setValue("Lumesse Openplatform Engineering Department");	
		organizationUnit.setOrganizationUnitCode("FZ1101");
		organizationUnit.setOrganizationUnitName("Lumesse Open Engineering");
		organizationUnit.setOrganizationUnitDescription("Lumesse Openplatform Engineering Department");
		organizationUnit.setParentOrganizationUnit(parentOrganizationUnit);
		organizationChart.setOrganizationUnit(organizationUnit);
		organizationUnit.setUserArea(Arrays.asList(new UserArea[]{organizationUserArea}));
		
	    File inputJson = new File(filePathOrganization);
	    String outputJson = Transformer.toJSON(organizationChart);
		
		ObjectMapper diffMapper = new ObjectMapper();
		JsonNode tree1 = diffMapper.readTree(inputJson);
		JsonNode tree2 = diffMapper.readTree(outputJson);
		
		//ASSERT
		org.junit.Assert.assertEquals(tree1, tree2);
	}
}
