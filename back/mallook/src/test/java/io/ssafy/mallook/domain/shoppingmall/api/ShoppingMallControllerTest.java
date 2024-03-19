package io.ssafy.mallook.domain.shoppingmall.api;

import io.ssafy.mallook.domain.shoppingmall.application.ShoppingMallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles(profiles = {"dev", "local"})
@WebMvcTest(controllers = ShoppingMallController.class)
class ShoppingMallControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ShoppingMallService shoppingMallService;

    private String url = "/api/shopping-mall";

    @Test
    @WithMockUser
    void getAllShoppingMallList() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);

        mockMvc.perform(
                        MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SELECT SUCCESS"));
    }
}