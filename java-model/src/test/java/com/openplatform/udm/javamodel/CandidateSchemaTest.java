/**
 * Copyright Â© 2010-2014 Nokia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.openplatform.udm.javamodel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openplatform.udm.Candidate;
import com.openplatform.udm.Certification;
import com.openplatform.udm.EmploymentReference;
import com.openplatform.udm.License;
import com.openplatform.udm.PersonCompetency;
import com.openplatform.udm.PositionHistory;
import com.openplatform.udm.WorkEligibility;
import com.openplatform.udm.common.Address;
import com.openplatform.udm.common.Attachment;
import com.openplatform.udm.common.Code;
import com.openplatform.udm.common.CountryCode;
import com.openplatform.udm.common.Person;

/**
 * The Class CandidateSchemaTest.
 */
public class CandidateSchemaTest {

	/** The Constant filePathCandidate. */
	private static final String filePathCandidate = "./target/json-schemas/sample/candidate.sample.json";
	
	/** The Constant filePathAddress. */
	private static final String filePathAddress = "./target/json-schemas/sample/address.sample.json";
	
	/** The Constant filePathPerson. */
	private static final String filePathPerson = "./target/json-schemas/sample/person.sample.json";
	
	/** The Constant filePathCandidatePartial. */
	private static final String filePathCandidatePartial = "./target/json-schemas/sample/candidate.partial.sample.json";

	/**
	 * Creates an ObjectMapper object that reads in sample candidate JSON data and populates
	 * the candidate java model with it. The data is then read from the populated java model.
	 * The input and output JSON data from the java model (before deserialisation and after 
	 * serialisation, respectively) is constructed into JSON trees and tested for equality.
	 * This is different from comparing their string representation; object and property order
	 * does not affect the validity of the JSON data against the defined schema
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	
	@Test
	public void testCandidateSchema() throws IOException {
		//ASSIGN & ACT
		File file = new File(filePathCandidate);
		Candidate candidateSchema = Transformer.fromJSON(file, Candidate.class);
		String outputJson = Transformer.toJSON(candidateSchema);
		ObjectMapper diffMapper = new ObjectMapper();
		JsonNode tree1 = diffMapper.readTree(file);
		JsonNode tree2 = diffMapper.readTree(outputJson);	
		
		//ASSERT
		org.junit.Assert.assertTrue(tree1.equals(tree2));		
	}
	
	/**
	 * Test address schema.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testAddressSchema() throws IOException {
		//ASSIGN & ACT
		File file = new File(filePathAddress);				
		Address addressSchema = Transformer.fromJSON(file, Address.class);		
		String outputJson = Transformer.toJSON(addressSchema);
		ObjectMapper diffMapper = new ObjectMapper();
		JsonNode tree1 = diffMapper.readTree(file);
		JsonNode tree2 = diffMapper.readTree(outputJson);
		
		//ASSERT
		org.junit.Assert.assertTrue(tree1.equals(tree2));		
	}
	
	/**
	 * Test person schema.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testPersonSchema() throws IOException {
		//ASSIGN & ACT
		File file = new File(filePathPerson);		
		Person personSchema = Transformer.fromJSON(file, Person.class);
		String outputJson = Transformer.toJSON(personSchema);
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
		Candidate candidate = new Candidate();
		
	    Person person = new Person();
	    person.setGivenName("James");
	    person.setFamilyName("McGregor");
	    
	    List<PersonCompetency> personCompetencies = new ArrayList<PersonCompetency>();
	    PersonCompetency personCompetency = new PersonCompetency();
	    personCompetency.setCompetencyID("IPS");
	    personCompetency.setCompetencyName("Interpersonal skills");
	    PersonCompetency personCompetency2 = new PersonCompetency();
	    personCompetency2.setCompetencyID("CAR");
	    personCompetency2.setCompetencyName("Consistency and Reliability");
	    personCompetency2.setCompetencyLevel("High");
	    personCompetencies.add(personCompetency);
	    personCompetencies.add(personCompetency2);
	    
	    List<WorkEligibility> workEligibilities = new ArrayList<WorkEligibility>();
	    WorkEligibility workEligibility = new WorkEligibility();
	    CountryCode wecc = new CountryCode();
	    wecc.setName("United Kingdom");
	    wecc.setCode("UK");
	    workEligibility.setCountryCode(wecc);
	    workEligibility.setPermanent(true);
	    workEligibilities.add(workEligibility);
	    
	    List<PositionHistory> positionHistories = new ArrayList<PositionHistory>();
	    PositionHistory positionHistory = new PositionHistory();
	    positionHistory.setEmployer("Credit Suisse");
	    positionHistory.setPositionTitle("Business Analyst");
	    positionHistory.setStartDate("1980-02-01");
	    positionHistories.add(positionHistory);
	    
	    List<Certification> certifications = new ArrayList<Certification>();
	    Certification certification = new Certification();
	    Code ctcCode = new Code();
	    ctcCode.setName("Oracle Java Certification Associate");
	    ctcCode.setCode("OJCA");
	    certification.setCertificationTypeCode(ctcCode);
	    certification.setCertificationName("Oracle Java Certification Associate");
	    certifications.add(certification);
	    
	    List<License> licenses = new ArrayList<License>();
	    License license = new License();
	    Code ltcCode = new Code();
	    ltcCode.setName("Driver's Licence Code");
	    ltcCode.setCode("DLIC");
	    license.setLicenseTypeCode(ltcCode);
	    license.setLicenseName("Driver's Licence");
	    licenses.add(license);
	    
	    List<EmploymentReference> employmentReferences = new ArrayList<EmploymentReference>();
	    EmploymentReference employmentReference = new EmploymentReference();
	    employmentReference.setFormattedName("David Smith");
	    employmentReferences.add(employmentReference);
	    
	    List<Code> specialCommitments = new ArrayList<Code>();
	    Code specialCommitment = new Code();
	    specialCommitment.setCode("CHAR");
	    specialCommitment.setName("Chair person for non-profit organisation");
	    specialCommitments.add(specialCommitment);
	    
	    List<Attachment> attachments = new ArrayList<Attachment>();
	    Attachment attachment = new Attachment();
	    attachment.setFileName("CoverLetter.doc");
	    attachments.add(attachment);
	    
	    candidate.setPerson(person);
	    candidate.setPersonCompetency(personCompetencies);
	    candidate.setWorkEligibility(workEligibilities);
	    candidate.setPositionHistory(positionHistories);
	    candidate.setCertification(certifications);
	    candidate.setLicense(licenses);
	    candidate.setEmploymentReferences(employmentReferences);
	    candidate.setSpecialCommitment(specialCommitments);
	    candidate.setAttachment(attachments);  
	    
	    File inputJson = new File(filePathCandidatePartial);
	    String outputJson = Transformer.toJSON(candidate);
		
		ObjectMapper diffMapper = new ObjectMapper();
		JsonNode tree1 = diffMapper.readTree(inputJson);
		JsonNode tree2 = diffMapper.readTree(outputJson);
		
		//ASSERT
		org.junit.Assert.assertEquals(tree1, tree2);
	}
	

	/**
	 * Test construction in progress. Tests that the java model structure is identical to
	 * the original JSON schema definition. Types and structure (has same parents, siblings
	 * and children
	 */
	/**
	@Test
	public void testPojoCompletenessJackson() throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		File from = new File(filePathCandidate);
		
		JsonNode rootNode = null;

		rootNode = mapper.readTree(from);

		
		com.openplatform.udm.Candidate candidate = new Candidate();
		
		candidate.setPerson(new Person());
		
		Person person = new Person();
		person.setFamilyName("Smith");
		person.setGivenName("John");
		
		candidate.setPerson(person);

		System.out.println("--------------------");
		System.out.println("Candidate " + candidate.getClass());
		System.out.println("--------------------");
		
		//System.out.println("field key == " + rootNode.getKey());
		//System.out.println("field key == " + rootNode.getValue());
		
		//Method[] m = candidate.getDeclaredMethods();
		
		Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();

		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String key = field.getKey();
			JsonNode value = field.getValue();
			JsonNodeType nodeType = field.getValue().getNodeType();
			Class<? extends JsonNode> nodeClass = field.getValue().getClass();
			String nodeAsString = field.toString();
			
			System.out.println("key == " + key);
			System.out.println("value == " + value);
			System.out.println("nodeType == " + nodeType);
			System.out.println("nodeClass == " + nodeClass);
			System.out.println("nodeAsString == " + nodeAsString);
			System.out.println("--------------------");
			
		}

	}
	 */
}
