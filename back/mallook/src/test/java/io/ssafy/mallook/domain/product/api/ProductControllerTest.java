package io.ssafy.mallook.domain.product.api;

import io.ssafy.mallook.config.security.WithMockCustomUser;
import io.ssafy.mallook.domain.product.application.ProductService;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private String url = "/api/products";

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getProductList() throws Exception {
        String mainCategory = "top";
        String subCategory = "sport";
        List<ProductListDto> list = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 2);
        Page<ProductListDto> page = new PageImpl<>(list, pageable, list.size());
        when(productService.getProductList(pageable, mainCategory.toUpperCase(), subCategory.toUpperCase()))
                .thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("primary", mainCategory)
                        .param("secondary", subCategory))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SELECT SUCCESS"));
    }
}