package com.nnk.springboot;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
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

@WebMvcTest(RatingController.class)
public class RatingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RatingService ratingService;

    @Test
    @WithMockUser
    public void getRatingAdd_shouldReturnAddView() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    @WithMockUser
    public void postRatingValidate_shouldReturnListView() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("Aaa");
        rating.setSandPRating("AAA");
        rating.setFitchRating("AAA");
        rating.setOrderNumber(1);

        when(ratingService.save(any(Rating.class))).thenReturn(rating);
        when(ratingService.findAll()).thenReturn(List.of(rating));

        mockMvc.perform(post("/rating/validate")
                        .with(csrf())
                        .param("moodysRating", "Aaa")
                        .param("sandPRating", "AAA")
                        .param("fitchRating", "AAA")
                        .param("orderNumber", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("ratings"));
    }
}