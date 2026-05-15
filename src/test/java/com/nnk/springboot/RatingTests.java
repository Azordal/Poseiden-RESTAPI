package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingRepository ratingRepository;

	@Test
	public void ratingTest() {

		Rating rating = new Rating();
		rating.setMoodysRating("Aaa");
		rating.setSandPRating("AAA");
		rating.setFitchRating("AAA");
		rating.setOrderNumber(1);

		// Save
		rating = ratingRepository.save(rating);

		assertNotNull(rating.getId());
		assertEquals(1, rating.getOrderNumber());

		// Find
		Rating foundRating = ratingRepository.findById(rating.getId()).orElse(null);

		assertNotNull(foundRating);
		assertEquals(rating.getId(), foundRating.getId());

		// Update
		foundRating.setOrderNumber(2);

		ratingRepository.save(foundRating);

		Rating updatedRating = ratingRepository.findById(rating.getId()).orElse(null);

		assertEquals(2, updatedRating.getOrderNumber());

		// Delete
		Integer id = updatedRating.getId();

		ratingRepository.delete(updatedRating);

		assertFalse(ratingRepository.findById(id).isPresent());
	}
}