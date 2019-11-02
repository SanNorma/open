//package com.tradesystem.magic.controller;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class GoodsControllerRestTest {
//
//    private static final long ID = 1;
//    @LocalServerPort
//    int randomServerPort;
//    private TestRestTemplate testRestTemplate;
//
//    @BeforeEach
//    void setUp() {
//        testRestTemplate = new TestRestTemplate();
//    }
//
//    @Test
//    void return404_whenTryingToFetchDeletedEntity() {
//        String baseUrl = "http://localhost:" + randomServerPort;
//        ResponseEntity<JsonNode> resultEntity = testRestTemplate.getForEntity("http://localhost:" + randomServerPort+ "/good/1", JsonNode.class);
//        assertThat(resultEntity.getStatusCode(), is(HttpStatus.OK));
//        testRestTemplate.delete(baseUrl + "/good/" + ID);
//
//        resultEntity = testRestTemplate.getForEntity(baseUrl + "/good/" + ID, JsonNode.class);
//        assertThat(resultEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
//    }
//}
