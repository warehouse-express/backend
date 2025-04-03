package com.inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inventory.model.repository.BuyerRepository;
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
public class BuyerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateGetUpdateDeleteBuyer() throws Exception {
        // 1. CREATE - Test creating a buyer
        ObjectNode buyerJson = objectMapper.createObjectNode();
        buyerJson.put("firstName", "Test");
        buyerJson.put("lastName", "Buyer");
        buyerJson.put("email", "test.buyer@example.com");
        buyerJson.put("password", "password123");
        buyerJson.put("shippingAddress", "123 Delivery St, Test City");
        buyerJson.put("billingAddress", "123 Billing St, Test City");
        buyerJson.put("phoneNumber", "123-456-7890");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/buyers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(buyerJson.toString()))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        ObjectNode createdBuyerJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        Long createdBuyerId = createdBuyerJson.get("id").asLong();

        // 2. READ - Test retrieving the buyer
        response = mockMvc.perform(
                        get("/api/buyers/" + createdBuyerId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode fetchedBuyerJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("Test", fetchedBuyerJson.get("firstName").asText());
        assertEquals("Buyer", fetchedBuyerJson.get("lastName").asText());
        assertEquals("123-456-7890", fetchedBuyerJson.get("phoneNumber").asText());

        // 3. UPDATE - Test updating the buyer
        ObjectNode updateJson = objectMapper.createObjectNode();
        updateJson.put("firstName", "Updated");
        updateJson.put("lastName", "BuyerName");
        updateJson.put("shippingAddress", "456 New Delivery St, Updated City");
        updateJson.put("billingAddress", "456 New Billing St, Updated City");
        updateJson.put("phoneNumber", "987-654-3210");

        mockMvc.perform(
                        put("/api/buyers/" + createdBuyerId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateJson.toString()))
                .andExpect(status().isOk());

        //Verify update
        response = mockMvc.perform(
                        get("/api/buyers/" + createdBuyerId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode updatedBuyerJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("Updated", updatedBuyerJson.get("firstName").asText());
        assertEquals("BuyerName", updatedBuyerJson.get("lastName").asText());
        assertEquals("987-654-3210", updatedBuyerJson.get("phoneNumber").asText());

        // 4. DELETE - Test deleting the buyer
        mockMvc.perform(
                        delete("/api/buyers/" + createdBuyerId))
                .andExpect(status().isNoContent());

        //Verify buyer was deleted
        mockMvc.perform(
                        get("/api/buyers/" + createdBuyerId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetBuyerWithOrders() throws Exception {
        //We need a buyer with orders, we use an existing one from the data.sql
        //Assuming buyer ID 1001 exists and has orders associated with it
        Long buyerId = 1001L;

        MockHttpServletResponse response = mockMvc.perform(
                        get("/api/buyers/" + buyerId + "/with-orders"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode buyerWithOrdersJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertTrue(buyerWithOrdersJson.has("orders"));
    }
}