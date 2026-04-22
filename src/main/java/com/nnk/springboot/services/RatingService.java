package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAll() {
        return (List<Rating>) ratingRepository.findAll();
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating findById(Integer id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating Id: " + id));
    }

    public Rating update(Integer id, Rating rating) {
        Rating existingRating = findById(id);

        existingRating.setMoodysRating(rating.getMoodysRating());
        existingRating.setSandPRating(rating.getSandPRating());
        existingRating.setFitchRating(rating.getFitchRating());
        existingRating.setOrderNumber(rating.getOrderNumber());

        return ratingRepository.save(existingRating);
    }

    public void delete(Integer id) {
        Rating rating = findById(id);
        ratingRepository.delete(rating);
    }
}