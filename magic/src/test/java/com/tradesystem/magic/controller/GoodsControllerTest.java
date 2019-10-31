package com.tradesystem.magic.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.dto.GoodRequest;
import com.tradesystem.magic.exception.GoodNotFoundException;
import com.tradesystem.magic.service.GoodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GoodsController.class)
class GoodsControllerTest {

    private static final String NAME = "Something";
    private static final long PRICE = 20L;
    private static final String DESCRIPTION = "Lorem ipsum";
    private static final long ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GoodService goodService;

    @Captor
    private ArgumentCaptor<GoodRequest> argumentCaptor;

    @Test
    void save_whenPostANewBook() throws Exception {
        GoodRequest request = GoodRequest.builder().name(NAME).price(PRICE).description(DESCRIPTION).build();

        when(goodService.createGood(argumentCaptor.capture())).thenReturn(1L);

        mockMvc.perform(post("/good").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/good/1"));

        assertThat(argumentCaptor.getValue().getName(), is(NAME));
        assertThat(argumentCaptor.getValue().getPrice(), is(PRICE));
        assertThat(argumentCaptor.getValue().getDescription(), is(DESCRIPTION));
    }

    @Test
    void return_whenCalledGetAllBooks() throws Exception {
        when(goodService.findAll()).thenReturn(List.of(createGood(ID, NAME, PRICE, DESCRIPTION)));
        mockMvc.perform(get("/good"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(NAME)))
                .andExpect(jsonPath("$[0].price", is(20)))
                .andExpect(jsonPath("$[0].description", is(DESCRIPTION)));
    }

    @Test
    void return_whenCalledGetBook() throws Exception {
        when(goodService.findById(ID)).thenReturn(createGood(ID, NAME, PRICE, DESCRIPTION));
        mockMvc.perform(get("/good/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.price", is(20)))
                .andExpect(jsonPath("$.description", is(DESCRIPTION)));
    }

    @Test
    void return404_whenCalledGetBook_ifNotFound() throws Exception {
        when(goodService.findById(42L)).thenThrow(new GoodNotFoundException("Good with id: '42' not found"));
        mockMvc.perform(get("/good/42")).andExpect(status().isNotFound());
    }

    private Good createGood(long id, String name, long price, String description) {
        return new Good(id, name, price, description);
    }
}