package com.nnk.springboot;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BidListController.class)
public class BidListControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BidListService bidListService;

    @Test
    @WithMockUser
    public void getBidListAdd_shouldReturnAddView() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    @WithMockUser
    public void postBidListValidate_shouldReturnListView() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("Account Test");
        bidList.setType("Type Test");
        bidList.setBidQuantity(10.0);

        when(bidListService.save(any(BidList.class))).thenReturn(bidList);
        when(bidListService.findAll()).thenReturn(List.of(bidList));

        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .param("account", "Account Test")
                        .param("type", "Type Test")
                        .param("bidQuantity", "10.0"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bidLists"));
    }
}