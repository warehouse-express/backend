package com.inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inventory.model.repository.SellerRepository;
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
public class SellerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateGetUpdateDeleteSeller() throws Exception {
        // 1. CREATE - Test creating a seller
        ObjectNode sellerJson = objectMapper.createObjectNode();
        sellerJson.put("firstName", "Test");
        sellerJson.put("lastName", "Seller");
        sellerJson.put("email", "test.seller1@example.com");
        sellerJson.put("password", "password123");
        sellerJson.put("companyName", "Test Company");
        sellerJson.put("companyDescription", "A company for testing");
        sellerJson.put("contactPhone", "123-456-7890");
        sellerJson.put("businessAddress", "123 Business St, Test City");
        sellerJson.put("taxId", "TX123456789");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/sellers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(sellerJson.toString()))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        ObjectNode createdSellerJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        Long createdSellerId = createdSellerJson.get("id").asLong();

        // 2. READ - Test retrieving the seller
        response = mockMvc.perform(
                        get("/api/sellers/" + createdSellerId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode fetchedSellerJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("Test", fetchedSellerJson.get("firstName").asText());
        assertEquals("Seller", fetchedSellerJson.get("lastName").asText());
        assertEquals("Test Company", fetchedSellerJson.get("companyName").asText());
        assertEquals("123-456-7890", fetchedSellerJson.get("contactPhone").asText());

        // 3. UPDATE - Test updating the seller
        ObjectNode updateJson = objectMapper.createObjectNode();
        updateJson.put("firstName", "Updated");
        updateJson.put("lastName", "SellerName");
        updateJson.put("companyName", "Updated Company");
        updateJson.put("companyDescription", "An updated company description");
        updateJson.put("contactPhone", "987-654-3210");
        updateJson.put("businessAddress", "456 Updated Business St, Updated City");
        updateJson.put("taxId", "TX987654321");

        mockMvc.perform(
                        put("/api/sellers/" + createdSellerId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateJson.toString()))
                .andExpect(status().isOk());

        //Verify update
        response = mockMvc.perform(
                        get("/api/sellers/" + createdSellerId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode updatedSellerJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("Updated", updatedSellerJson.get("firstName").asText());
        assertEquals("SellerName", updatedSellerJson.get("lastName").asText());
        assertEquals("Updated Company", updatedSellerJson.get("companyName").asText());
        assertEquals("987-654-3210", updatedSellerJson.get("contactPhone").asText());

        // 4. DELETE - Test deleting the seller
        mockMvc.perform(
                        delete("/api/sellers/" + createdSellerId))
                .andExpect(status().isNoContent());

        //Verify seller was deleted
        mockMvc.perform(
                        get("/api/sellers/" + createdSellerId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetSellerWithProducts() throws Exception {
        //We need a seller with products, we use an existing one from the data.sql
        //Assuming seller ID 2001 exists and has products associated with it
        Long sellerId = 2001L;

        MockHttpServletResponse response = mockMvc.perform(
                        get("/api/sellers/" + sellerId + "/with-products"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode sellerWithProductsJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertTrue(sellerWithProductsJson.has("products"));
        assertTrue(sellerWithProductsJson.get("products").isArray());
        assertTrue(sellerWithProductsJson.get("products").size() > 0);
    }
}