package com.openintegration.udm;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfigurationBuilder;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonSchemaFactoryBuilder;

/**
 * The Class VerifySampleDataTest.
 */
public class VerifySampleDataTest {

	/** The json schema factory. */
	private JsonSchemaFactory jsonSchemaFactory;
	
	/** The Constant BASE_URI. */
	private final static String BASE_URI = "http://openplatform.lumesse.com";
	
	/** The Constant ADDRESS_RESOURCE. */
	private final static String ADDRESS_RESOURCE = "/schemas/address.json";
	
	/** The Constant ADDRESS_SCHEMA_URI. */
	private final static String ADDRESS_SCHEMA_URI = BASE_URI + ADDRESS_RESOURCE;
	
	/** The Constant CANDIDATE_RESOURCE. */
	private final static String CANDIDATE_RESOURCE = "/schemas/candidate.json";
	
	/** The Constant CANDIDATE_SCHEMA_URI. */
	private final static String CANDIDATE_SCHEMA_URI = BASE_URI + CANDIDATE_RESOURCE;

	/** The Constant CODE_RESOURCE. */
	private final static String CODE_RESOURCE = "/schemas/code.json";
	
	/** The Constant CODE_SCHEMA_URI. */
	private final static String CODE_SCHEMA_URI = BASE_URI + CODE_RESOURCE;
	
	/** The Constant COUNTRY_CODE_RESOURCE. */
	private final static String COUNTRY_CODE_RESOURCE = "/schemas/countrycode.json";
	
	/** The Constant COUNTRY_CODE_SCHEMA_URI. */
	private final static String COUNTRY_CODE_SCHEMA_URI = BASE_URI + COUNTRY_CODE_RESOURCE;
	
	/** The Constant INDUSTRY_CODE_RESOURCE. */
	private final static String INDUSTRY_CODE_RESOURCE = "/schemas/industrycode.json";
	
	/** The Constant INDUSTRY_CODE_SCHEMA_URI. */
	private final static String INDUSTRY_CODE_SCHEMA_URI = BASE_URI + INDUSTRY_CODE_RESOURCE;
	
	/** The Constant JOB_CODE_RESOURCE. */
	private final static String JOB_CODE_RESOURCE = "/schemas/jobcode.json";
	
	/** The Constant JOB_CODE_SCHEMA_URI. */
	private final static String JOB_CODE_SCHEMA_URI = BASE_URI + JOB_CODE_RESOURCE;
	
	/** The Constant LANGUAGE_CODE_RESOURCE. */
	private final static String LANGUAGE_CODE_RESOURCE = "/schemas/languagecode.json";
	
	/** The Constant LANGUAGE_CODE_SCHEMA_URI. */
	private final static String LANGUAGE_CODE_SCHEMA_URI = BASE_URI + LANGUAGE_CODE_RESOURCE;
	
	/** The Constant REGION_CODE_RESOURCE. */
	private final static String REGION_CODE_RESOURCE = "/schemas/regioncode.json";
	
	/** The Constant REGION_CODE_SCHEMA_URI. */
	private final static String REGION_CODE_SCHEMA_URI = BASE_URI + REGION_CODE_RESOURCE;
	
	/** The Constant EMAIL_RESOURCE. */
	private final static String EMAIL_RESOURCE = "/schemas/email.json";
	
	/** The Constant EMAIL_SCHEMA_URI. */
	private final static String EMAIL_SCHEMA_URI = BASE_URI + EMAIL_RESOURCE;
	
	/** The Constant EMPLOYEE_RESOURCE. */
	private final static String EMPLOYEE_RESOURCE = "/schemas/employee.json";
	
	/** The Constant EMPLOYEE_SCHEMA_URI. */
	private final static String EMPLOYEE_SCHEMA_URI = BASE_URI + EMPLOYEE_RESOURCE;
	
	/** The Constant ID_RESOURCE. */
	private final static String ID_RESOURCE = "/schemas/id.json";
	
	/** The Constant ID_SCHEMA_URI. */
	private final static String ID_SCHEMA_URI = BASE_URI + ID_RESOURCE;
	
	/** The Constant PERSON_RESOURCE. */
	private final static String PERSON_RESOURCE = "/schemas/person.json";
	
	/** The Constant PERSON_SCHEMA_URI. */
	private final static String PERSON_SCHEMA_URI = BASE_URI + PERSON_RESOURCE;
	
	/** The Constant PHONE_RESOURCE. */
	private final static String PHONE_RESOURCE = "/schemas/phone.json";
	
	/** The Constant PHONE_SCHEMA_URI. */
	private final static String PHONE_SCHEMA_URI = BASE_URI + PHONE_RESOURCE;
	
	/** The Constant REFERENCELOCATION_RESOURCE. */
	private final static String REFERENCELOCATION_RESOURCE = "/schemas/referencelocation.json";
	
	/** The Constant REFERENCELOCATION_SCHEMA_URI. */
	private final static String REFERENCELOCATION_SCHEMA_URI = BASE_URI + REFERENCELOCATION_RESOURCE;
	
	/** The Constant WEB_RESOURCE. */
	private final static String WEB_RESOURCE = "/schemas/web.json";
	
	/** The Constant WEB_SCHEMA_URI. */
	private final static String WEB_SCHEMA_URI = BASE_URI + WEB_RESOURCE;
	
	/** The Constant POSITION_RESOURCE. */
	private final static String POSITION_RESOURCE = "/schemas/positionopening.json";
	
	/** The Constant POSITION_SCHEMA_URI. */
	private final static String POSITION_SCHEMA_URI = BASE_URI + POSITION_RESOURCE;
	
	/** The Constant CREDENTIAL_RESOURCE. */
	private final static String CREDENTIAL_RESOURCE = "/schemas/accesscredential.json";
	
	/** The Constant CREDENTIAL_SCHEMA_URI. */
	private final static String CREDENTIAL_SCHEMA_URI = BASE_URI + CREDENTIAL_RESOURCE;
	
	/** The Constant ATTACHMENT_RESOURCE. */
	private final static String ATTACHMENT_RESOURCE = "/schemas/attachment.json";
	
	/** The Constant ATTACHMENT_SCHEMA_URI. */
	private final static String ATTACHMENT_SCHEMA_URI = BASE_URI + ATTACHMENT_RESOURCE;
	
	/** The Constant USERAREA_RESOURCE. */
	private final static String USERAREA_RESOURCE = "/schemas/userarea.json";
	
	/** The Constant USERAREA_SCHEMA_URI. */
	private final static String USERAREA_SCHEMA_URI = BASE_URI + USERAREA_RESOURCE;
	
	/** The Constant ORGANIZATION_RESOURCE. */
	private final static String ORGANIZATION_RESOURCE = "/schemas/organization.json";
	
	/** The Constant ORGANIZATION_SCHEMA_URI. */
	private final static String ORGANIZATION_SCHEMA_URI = BASE_URI + ORGANIZATION_RESOURCE;
	
	/**
	 * Sets the up.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void setUp() throws IOException {				
		LoadingConfigurationBuilder loaderBuilder = LoadingConfiguration.newBuilder();		
		loaderBuilder.preloadSchema(ADDRESS_SCHEMA_URI,JsonLoader.fromResource(ADDRESS_RESOURCE));
		loaderBuilder.preloadSchema(CANDIDATE_SCHEMA_URI,JsonLoader.fromResource(CANDIDATE_RESOURCE));
		loaderBuilder.preloadSchema(EMAIL_SCHEMA_URI,JsonLoader.fromResource(EMAIL_RESOURCE));
		loaderBuilder.preloadSchema(EMPLOYEE_SCHEMA_URI,JsonLoader.fromResource(EMPLOYEE_RESOURCE));
		loaderBuilder.preloadSchema(ID_SCHEMA_URI,JsonLoader.fromResource(ID_RESOURCE));
		loaderBuilder.preloadSchema(PERSON_SCHEMA_URI,JsonLoader.fromResource(PERSON_RESOURCE));		
		loaderBuilder.preloadSchema(PHONE_SCHEMA_URI,JsonLoader.fromResource(PHONE_RESOURCE));
		loaderBuilder.preloadSchema(REFERENCELOCATION_SCHEMA_URI,JsonLoader.fromResource(REFERENCELOCATION_RESOURCE));
		loaderBuilder.preloadSchema(WEB_SCHEMA_URI,JsonLoader.fromResource(WEB_RESOURCE));		
		loaderBuilder.preloadSchema(CODE_SCHEMA_URI,JsonLoader.fromResource(CODE_RESOURCE));
		loaderBuilder.preloadSchema(COUNTRY_CODE_SCHEMA_URI,JsonLoader.fromResource(COUNTRY_CODE_RESOURCE));
		loaderBuilder.preloadSchema(INDUSTRY_CODE_SCHEMA_URI,JsonLoader.fromResource(INDUSTRY_CODE_RESOURCE));
		loaderBuilder.preloadSchema(JOB_CODE_SCHEMA_URI,JsonLoader.fromResource(JOB_CODE_RESOURCE));
		loaderBuilder.preloadSchema(LANGUAGE_CODE_SCHEMA_URI,JsonLoader.fromResource(LANGUAGE_CODE_RESOURCE));
		loaderBuilder.preloadSchema(REGION_CODE_SCHEMA_URI,JsonLoader.fromResource(REGION_CODE_RESOURCE));		
		loaderBuilder.preloadSchema(POSITION_SCHEMA_URI,JsonLoader.fromResource(POSITION_RESOURCE));
		loaderBuilder.preloadSchema(CREDENTIAL_SCHEMA_URI,JsonLoader.fromResource(CREDENTIAL_RESOURCE));
		loaderBuilder.preloadSchema(ATTACHMENT_SCHEMA_URI,JsonLoader.fromResource(ATTACHMENT_RESOURCE));
		loaderBuilder.preloadSchema(USERAREA_SCHEMA_URI,JsonLoader.fromResource(USERAREA_RESOURCE));			
		loaderBuilder.preloadSchema(ORGANIZATION_SCHEMA_URI,JsonLoader.fromResource(ORGANIZATION_RESOURCE));			
		JsonSchemaFactoryBuilder schemaFactoryBuilder = JsonSchemaFactory.newBuilder().setLoadingConfiguration(loaderBuilder.freeze());		
		jsonSchemaFactory = schemaFactoryBuilder.freeze();							
	}
		
	/**
	 * Verify position.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ProcessingException the processing exception
	 */
	@Test
	public void verifyPosition() throws IOException, ProcessingException {				
		JsonNode sampleResource = JsonLoader.fromResource("/sample/position.sample.json");		
		JsonSchema schema = jsonSchemaFactory.getJsonSchema(POSITION_SCHEMA_URI);		
		ProcessingReport validate = schema.validate(sampleResource);		
 		if (!validate.isSuccess()) {
 			System.out.println(validate.toString());
 			Assert.fail("Failed to validate the sample data against the schema");
 		}		
	}	
	
	/**
	 * Verify organization.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ProcessingException the processing exception
	 */
	@Test
	public void verifyOrganization() throws IOException, ProcessingException {				
		JsonNode sampleResource = JsonLoader.fromResource("/sample/organization.sample.json");		
		JsonSchema schema = jsonSchemaFactory.getJsonSchema(ORGANIZATION_SCHEMA_URI);		
		ProcessingReport validate = schema.validate(sampleResource);		
 		if (!validate.isSuccess()) {
 			System.out.println(validate.toString());
 			Assert.fail("Failed to validate the sample data against the schema");
 		}		
	}	
	
	/**
	 * Verify person.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ProcessingException the processing exception
	 */
	@Test
	public void verifyPerson() throws IOException, ProcessingException {		
		JsonNode sampleResource = JsonLoader.fromResource("/sample/person.sample.json");		
		JsonSchema schema = jsonSchemaFactory.getJsonSchema(PERSON_SCHEMA_URI);		
		ProcessingReport validate = schema.validate(sampleResource);
 		if (!validate.isSuccess()) {
 			System.out.println(validate.toString());
 			Assert.fail("Failed to validate the sample data against the schema");
 		}		
	}
	
	/**
	 * Verify candidate.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ProcessingException the processing exception
	 */
	@Test
	public void verifyCandidate() throws IOException, ProcessingException {				
		JsonNode sampleResource = JsonLoader.fromResource("/sample/candidate.sample.json");		
		JsonSchema schema = jsonSchemaFactory.getJsonSchema(CANDIDATE_SCHEMA_URI);		
		ProcessingReport validate = schema.validate(sampleResource);
 		if (!validate.isSuccess()) {
 			for (ProcessingMessage processingMessage : validate) {
 				if (processingMessage.getLogLevel().equals(LogLevel.ERROR))
 					System.err.println("Processing Error:" +  processingMessage.getMessage());
 			}
 			System.out.println(validate.toString());
 			Assert.fail("Failed to validate the sample data against the schema");
 		}		
	}	
}
