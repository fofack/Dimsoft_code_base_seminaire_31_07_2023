package com.proof.concept.controllers.unit_tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.proof.concept.beans.Fruit;
import com.proof.concept.controllers.FruitsController;
import com.proof.concept.exceptions.InvalidInputException;
import com.proof.concept.services.FruitsService;
import com.proof.concept.utils.Utils;

@WebMvcTest(FruitsController.class)
public class FruitsControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
	private FruitsService fruitsService;

    @Test
    public void aFruitCanBeCreatedUsingTheRightEndpoint() throws Exception {
        Fruit fruit = new Fruit("Mango", 10);

        when(fruitsService.findByFruitName(fruit.getFruitName())).thenReturn(null);
        when(fruitsService.createOrUpdateFruit(any(Fruit.class))).thenReturn(new Fruit(1, fruit.getFruitName(), fruit.getFruitRating()));

        this.mockMvc.perform(post("/fruits/createFruit")
                                .content(Utils.asJsonString(fruit))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.fruitId").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.fruitId").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.fruitName").value("Mango"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.fruitRating").value(10));
    }

    @Test
    public void anInvalidInputExceptionIsRaisedWhenTheFruitAlreadyExist() throws Exception {
        Fruit fruit = new Fruit("Mango", 10);

        when(fruitsService.findByFruitName(fruit.getFruitName())).thenReturn(fruit);
        when(fruitsService.createOrUpdateFruit(any(Fruit.class))).thenReturn(new Fruit(1, fruit.getFruitName(), fruit.getFruitRating()));

        this.mockMvc.perform(post("/fruits/createFruit")
                                .content(Utils.asJsonString(fruit))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidInputException));
    }
}
