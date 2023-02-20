package com.example.knupin.repository;

import com.example.knupin.domain.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinBoardRepository extends JpaRepository<Pin, Integer> {
    List<Pin> findByPinId(int pinId);
}
