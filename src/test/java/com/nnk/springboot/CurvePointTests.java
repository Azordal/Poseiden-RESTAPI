package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {

		CurvePoint curvePoint = new CurvePoint();
		curvePoint.setCurveId(1);
		curvePoint.setTerm(10.0);
		curvePoint.setValue(30.0);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);

		assertNotNull(curvePoint.getId());
		assertEquals(1, curvePoint.getCurveId());

		// Find
		CurvePoint foundCurvePoint = curvePointRepository.findById(curvePoint.getId()).orElse(null);

		assertNotNull(foundCurvePoint);
		assertEquals(curvePoint.getId(), foundCurvePoint.getId());

		// Update
		foundCurvePoint.setTerm(50.0);

		curvePointRepository.save(foundCurvePoint);

		CurvePoint updatedCurvePoint = curvePointRepository.findById(curvePoint.getId()).orElse(null);

		assertEquals(50.0, updatedCurvePoint.getTerm());

		// Delete
		Integer id = updatedCurvePoint.getId();

		curvePointRepository.delete(updatedCurvePoint);

		assertFalse(curvePointRepository.findById(id).isPresent());
	}
}