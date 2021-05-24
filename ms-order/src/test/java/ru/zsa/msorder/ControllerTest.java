package ru.zsa.msorder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

@Test
    public void getAllOrdersTest() throws Exception {
        mvc.perform(get("/api/v1/orders/order")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].price",is(100)));

}

    @Test
    public void getOrderByOrderIdTest() throws Exception {
        mvc.perform(get("/api/v1/orders/order/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(100)));

    }

@Test
    public void getAllOrderItemsByProductIdTest() throws Exception{
    mvc.perform(get("/api/v1/orders/orderItems/productId/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$",hasSize(2)))
            .andExpect(jsonPath("$.[0].quantity",is(8)));

}

}


