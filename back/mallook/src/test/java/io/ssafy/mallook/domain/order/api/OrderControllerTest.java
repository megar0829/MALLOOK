package io.ssafy.mallook.domain.order.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ssafy.mallook.config.security.WithMockCustomUser;
import io.ssafy.mallook.domain.order.application.OrderService;
import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.order.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.order.dto.response.OrderDetailDto;
import io.ssafy.mallook.domain.order.dto.response.OrderListDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private String url = "/api/orders";

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getOrderList() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SELECT SUCCESS"));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getOrderDetail() throws Exception {
        Long orderId = 1L;
        OrderDetailDto orderDetailDto = new OrderDetailDto(1000L, 100L, 10L);
        Mockito.when(orderService.getOrderDetail(orderId))
                .thenReturn(orderDetailDto);

        mockMvc.perform(MockMvcRequestBuilders.get(url + "/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SELECT SUCCESS"));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void createOrder() throws Exception {
        OrderCreateDto orderCreateDto = new OrderCreateDto(1000L, 100L, 10L);

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCreateDto))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("INSERT SUCCESS")
                );
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void deleteOrder() throws Exception {
        OrderDeleteDto orderDeleteDto = new OrderDeleteDto(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDeleteDto))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("DELETE SUCCESS"));

    }
}