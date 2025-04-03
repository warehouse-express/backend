package com.inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inventory.model.entity.Order;
import com.inventory.model.repository.BuyerRepository;
import com.inventory.model.repository.OrderRepository;
import com.inventory.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testCreateGetUpdateOrder() throws Exception {
        //We use existing buyer and product IDs from data.sql
        //Assuming buyer ID 1001 and product ID 1 exist
        Long buyerId = 1001L;
        Long productId = 1L;

        // 1. CREATE - Test creating an order
        ObjectNode orderJson = objectMapper.createObjectNode();
        orderJson.put("buyerId", buyerId);
        orderJson.put("shippingAddress", "123 Test Delivery St, Test City");

        ArrayNode itemsArray = objectMapper.createArrayNode();
        ObjectNode itemJson = objectMapper.createObjectNode();
        itemJson.put("productId", productId);
        itemJson.put("quantity", 2);
        itemsArray.add(itemJson);
        orderJson.set("items", itemsArray);

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(orderJson.toString()))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        ObjectNode createdOrderJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        Long createdOrderId = createdOrderJson.get("id").asLong();
        String orderNumber = createdOrderJson.get("orderNumber").asText();

        // 2. READ - Test retrieving the order
        response = mockMvc.perform(
                        get("/api/orders/" + createdOrderId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode fetchedOrderJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(orderNumber, fetchedOrderJson.get("orderNumber").asText());
        assertEquals("PENDING", fetchedOrderJson.get("status").asText());

        //Also test fetching by order number
        response = mockMvc.perform(
                        get("/api/orders/number/" + orderNumber))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode orderByNumberJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(createdOrderId.longValue(), orderByNumberJson.get("id").asLong());

        // 3. UPDATE - Test updating order status
        mockMvc.perform(
                        put("/api/orders/" + createdOrderId + "/status")
                                .param("status", "PROCESSING"))
                .andExpect(status().isOk());

        //Verify status update
        response = mockMvc.perform(
                        get("/api/orders/" + createdOrderId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode updatedOrderJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("PROCESSING", updatedOrderJson.get("status").asText());

        //Test updating tracking information
        mockMvc.perform(
                        put("/api/orders/" + createdOrderId + "/tracking")
                                .param("trackingNumber", "TRACK123456"))
                .andExpect(status().isOk());

        //Verify tracking update
        response = mockMvc.perform(
                        get("/api/orders/" + createdOrderId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        updatedOrderJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("TRACK123456", updatedOrderJson.get("trackingNumber").asText());

        // 4. CANCEL - Test cancelling the order
        mockMvc.perform(
                        delete("/api/orders/" + createdOrderId + "/cancel"))
                .andExpect(status().isNoContent());

        //Verify order was cancelled
        response = mockMvc.perform(
                        get("/api/orders/" + createdOrderId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        updatedOrderJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("CANCELLED", updatedOrderJson.get("status").asText());
    }

    @Test
    void testGetOrdersByBuyer() throws Exception {
        //Assuming buyer ID 1001 exists and has orders
        Long buyerId = 1001L;

        MockHttpServletResponse response = mockMvc.perform(
                        get("/api/orders/buyer/" + buyerId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //Verify we got an array of orders
        ArrayNode ordersJson = (ArrayNode) objectMapper.readTree(response.getContentAsString());
        assertTrue(ordersJson.size() > 0);
    }
}