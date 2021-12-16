package rw.ac.rca.termOneExam.controller;


import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.service.CityService;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CityService cityService;



    @Test
    public void getAll_success() throws JSONException {
        String response = this.restTemplate.getForObject("/api/cities", String.class);
        System.out.println(response);
        //JSONAssert.assertEquals("[]", response, false);
        JSONAssert.assertEquals("[{\"id\":101,\"name\":\"Kigali\",\"weather\":24},{\"id\":102,\"name\":\"Musanze\",\"weather\":18},{\"id\":103,\"name\":\"Rubavu\",\"weather\":20},{\"id\":104,\"name\":\"Nyagatare\",\"weather\":28}]", response, true);
    }

    @Test
    public void getById_successEntity() throws JSONException {
        ResponseEntity<City> response = this.restTemplate.getForEntity("/api/cities/101", City.class);

        System.out.println(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(24, response.getBody().getWeather());
        assertEquals("Kigali", response.getBody().getName());

    }



    @Test
    public void getById_404() {
        ResponseEntity<APICustomResponse> response = this.restTemplate.getForEntity("/all-items/1000", APICustomResponse.class);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("City not found with id 1000", response.getBody().getMessage());
    }

    @Test
    public void createItem_Success() {

        CreateCityDTO city1=new CreateCityDTO("Kigali",24);


        ResponseEntity<City> response = this.restTemplate.postForEntity("/all-items", city1, City.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Kigali",response.getBody().getName());

    }

}
