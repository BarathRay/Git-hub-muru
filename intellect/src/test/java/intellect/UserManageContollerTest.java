package intellect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellect.SpringBootApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserManageContollerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


	@Test
	public void testCreateuser() throws JsonProcessingException{

		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("fName", "mani");
		requestBody.put("lName", "ram");
		requestBody.put("email", "test@gmail1.com");
		requestBody.put("pinCode", 54456);
		requestBody.put("birthDate", "07-Dec-2017");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		//Creating http entity object with request body and headers
		HttpEntity<String> httpEntity =
				new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);


		//Invoking the API
		Map<String, Object> apiResponse =
				restTemplate.postForObject(createURLWithPort("/api/user/create"), httpEntity, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		//Asserting the response of the API.
		String message = apiResponse.get("resMsg").toString();
		System.out.println("apiResponse ==>"+apiResponse);
		assertEquals("Request Success", message);
	}

	@Test
	public void testCreateuserNagtivecase() throws JsonProcessingException{

		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("fName", "mani");
		requestBody.put("lName", "ram");
		requestBody.put("email", "test@gmail4.com");
		requestBody.put("pinCode", 54456);
		requestBody.put("birthDate", "07-Nov-2017");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		//Creating http entity object with request body and headers
		HttpEntity<String> httpEntity =
				new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);


		//Invoking the API
		Map<String, Object> apiResponse =
				restTemplate.postForObject(createURLWithPort("/api/user/create"), httpEntity, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		//Asserting the response of the API.
		String message = apiResponse.get("resMsg").toString();
		System.out.println("apiResponse ==>"+apiResponse);
		assertEquals("Request Success", message);


		Map<String, Object> requestBody2 = new HashMap<String, Object>();
		requestBody2.put("fName", "mani");
		requestBody2.put("lName", "ram");
		requestBody2.put("email", "test@gmail4.com");
		requestBody2.put("pinCode", 54456);
		requestBody2.put("birthDate", "07-Nov-2017");

		//Creating http entity object with request body and headers
		HttpEntity<String> httpEntity2 =
				new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody2), requestHeaders);


		Map<String, Object> apiResponse2 =
				restTemplate.postForObject(createURLWithPort("/api/user/create"), httpEntity2, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		//Asserting the response of the API.
		String message2 = apiResponse2.get("resMsg").toString();
		System.out.println("apiResponse2 ==>"+apiResponse);
		assertEquals("Validation Info", message2);
	}

	@Test
	public void testupdateuser() throws JsonProcessingException{

		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("id", "1011");
		/*		  requestBody.put("fName", "mani");
		  requestBody.put("lName", "ram");
		  requestBody.put("email", "test@gmail1.com");*/
		requestBody.put("pinCode", 464687);
		requestBody.put("birthDate", "07-Nov-2012");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		//Creating http entity object with request body and headers
		HttpEntity<String> httpEntity =
				new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);


		//Invoking the API
		Map<String, Object> apiResponse =
				restTemplate.postForObject(createURLWithPort("/api/user/update"), httpEntity, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		//Asserting the response of the API.
		String message = apiResponse.get("resMsg").toString();
		System.out.println("apiResponse ==>"+apiResponse);
		assertEquals("Request Success", message);
	}


	@Test
	public void testnagtiveupdateuser() throws JsonProcessingException{

		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("id", "101045");
		/*		  requestBody.put("fName", "mani");
		  requestBody.put("lName", "ram");
		  requestBody.put("email", "test@gmail1.com");*/
		requestBody.put("pinCode", 464687);
		requestBody.put("birthDate", "07-Nov-2012");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		//Creating http entity object with request body and headers
		HttpEntity<String> httpEntity =
				new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);


		//Invoking the API
		Map<String, Object> apiResponse =
				restTemplate.postForObject(createURLWithPort("/api/user/update"), httpEntity, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		//Asserting the response of the API.
		String message = apiResponse.get("resMsg").toString();
		System.out.println("apiResponse ==>"+apiResponse);
		assertEquals("Validation Info", message);
	}

	@Test
	public void testdeleteuser() throws JsonProcessingException{


		//Invoking the API
		Map<String, Object> apiResponse =
				restTemplate.postForObject(createURLWithPort("/api/user/delete/1010"), null, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		//Asserting the response of the API.
		String message = apiResponse.get("resMsg").toString();
		System.out.println("apiResponse ==>"+apiResponse);
		assertEquals("Request Success", message);
	}

	@Test
	public void testnagtivedeleteuser() throws JsonProcessingException{


		//Invoking the API
		Map<String, Object> apiResponse =
				restTemplate.postForObject(createURLWithPort("/api/user/delete/10104"), null, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		//Asserting the response of the API.
		String message = apiResponse.get("resMsg").toString();
		System.out.println("apiResponse ==>"+apiResponse);
		assertEquals("Validation Info", message);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
