package kitchenpos.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import kitchenpos.application.ProductService;
import kitchenpos.application.TableGroupService;
import kitchenpos.domain.Product;
import kitchenpos.fixture.ProductFixture;

@WebMvcTest(controllers = ProductRestController.class)
class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @DisplayName("Product 생성")
    @Test
    void create() throws Exception {
        Product product = ProductFixture.createWithId(ProductFixture.ID1,ProductFixture.PRICE1);
        when(productService.create(any())).thenReturn(product);

        mockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(product))
        )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(header().stringValues("location", "/api/products/" + product.getId()))
            .andExpect(jsonPath("name").value(ProductFixture.NAME1));
    }

    @DisplayName("Find all products")
    @Test
    void list() throws Exception {
        Product product1 = ProductFixture.createWithId(ProductFixture.ID1,ProductFixture.PRICE1);
        Product product2 = ProductFixture.createWithId(ProductFixture.ID2,ProductFixture.PRICE1);
        when(productService.list()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/products")
            .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("[0].id").value(ProductFixture.ID1))
            .andExpect(jsonPath("[1].id").value(ProductFixture.ID2));
    }
}