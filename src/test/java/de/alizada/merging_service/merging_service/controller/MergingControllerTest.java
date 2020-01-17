package de.alizada.merging_service.merging_service.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MergingControllerTest {
    @Autowired
    private WebApplicationContext context;

    private static final String URL = "/merge/";

    @Test
    public void shouldResponse200ForValidData(){
        final String pathVarId = "1";

        RestAssuredMockMvc.given()
                .webAppContextSetup(context)
                .when()
                .get(URL + pathVarId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldResponse400ForNotValidData(){
        final String pathVarId = "wrong";

        RestAssuredMockMvc.given()
                .webAppContextSetup(context)
                .when()
                .get(URL + pathVarId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
