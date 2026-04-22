package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    private final CurvePointRepository curvePointRepository;

    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    public CurvePoint save(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    public CurvePoint findById(Integer id) {
        return curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id: " + id));
    }

    public CurvePoint update(Integer id, CurvePoint curvePoint) {
        CurvePoint existingCurvePoint = findById(id);

        existingCurvePoint.setCurveId(curvePoint.getCurveId());
        existingCurvePoint.setTerm(curvePoint.getTerm());
        existingCurvePoint.setValue(curvePoint.getValue());

        return curvePointRepository.save(existingCurvePoint);
    }

    public void delete(Integer id) {
        CurvePoint curvePoint = findById(id);
        curvePointRepository.delete(curvePoint);
    }
}