package com.tradesystem.magic.controller;

import com.tradesystem.magic.domain.Good;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GoodsControllerRestTest {

    public static final long GOOD_ID = 1;
    public static final String LOCALHOST = "http://localhost:";
    public static final String API_GOOD = "/api/good/";
    @LocalServerPort
    int randomServerPort;
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        testRestTemplate = new TestRestTemplate();
    }

    @Test
    @Transactional
    void return404_whenTryingToFetchDeletedEntity() {
        String baseUrl = LOCALHOST + randomServerPort;
        ResponseEntity<Good> resultEntity = testRestTemplate.getForEntity(baseUrl + API_GOOD + GOOD_ID, Good.class);
        assertThat(resultEntity.getStatusCode(), is(HttpStatus.OK));

        testRestTemplate.delete(baseUrl + API_GOOD + GOOD_ID);

        ResponseEntity<Good> resultEntity1 = testRestTemplate.getForEntity(baseUrl + "/api/good/" + GOOD_ID, Good.class);
        assertThat(resultEntity1.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
