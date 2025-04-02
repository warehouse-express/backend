package com.inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inventory.model.entity.Product;
import com.inventory.model.entity.Seller;
import com.inventory.model.repository.ProductRepository;
import com.inventory.model.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    private Seller testSeller;
    private final Long TEST_PRODUCT_ID = 9999L;

    @BeforeEach
    void setUp() {

        //create a test seller if not exists
        if (!sellerRepository.findById(9999L).isPresent()) {
            Seller seller = new Seller();
            seller.setId(9999L);
            seller.setFirstName("Test");
            seller.setLastName("Seller");
            seller.setEmail("test.seller@example.com");
            seller.setPassword("password");
            seller.setRole(Seller.Role.SELLER);
            seller.setCompanyName("Test Company");
            testSeller = sellerRepository.save(seller);
        } else {
            testSeller = sellerRepository.findById(9999L).get();
        }
    }

    @Test
    void testCreateGetUpdateDeleteProduct() throws Exception {
        // 1. CREATE - Test creating a product
        ObjectNode productJson = objectMapper.createObjectNode();
        productJson.put("name", "Test Product");
        productJson.put("description", "Test Description");
        productJson.put("price", 99.99);
        productJson.put("quantity", 50);
        productJson.put("category", "Test Category");
        productJson.put("sellerId", testSeller.getId());

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(productJson.toString()))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        ObjectNode createdProductJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        Long createdProductId = createdProductJson.get("id").asLong();

        // 2. READ - Test retrieving the product
        response = mockMvc.perform(
                        get("/api/products/" + createdProductId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode fetchedProductJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("Test Product", fetchedProductJson.get("name").asText());
        assertEquals(99.99, fetchedProductJson.get("price").asDouble());

        // 3. UPDATE - Test updating the product
        ObjectNode updateJson = objectMapper.createObjectNode();
        updateJson.put("name", "Updated Product");
        updateJson.put("price", 129.99);
        updateJson.put("quantity", 25);

        mockMvc.perform(
                        put("/api/products/" + createdProductId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateJson.toString()))
                .andExpect(status().isOk());

        // Verify update
        response = mockMvc.perform(
                        get("/api/products/" + createdProductId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ObjectNode updatedProductJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("Updated Product", updatedProductJson.get("name").asText());
        assertEquals(129.99, updatedProductJson.get("price").asDouble());
        assertEquals(25, updatedProductJson.get("quantity").asInt());

        // 4. DELETE - Test deleting the product
        mockMvc.perform(
                        delete("/api/products/" + createdProductId))
                .andExpect(status().isNoContent());

        // Verify product was deleted
        mockMvc.perform(
                        get("/api/products/" + createdProductId))
                .andExpect(status().isNotFound());
    }
}