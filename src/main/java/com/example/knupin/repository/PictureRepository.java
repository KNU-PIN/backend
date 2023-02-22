package com.example.knupin.repository;

import com.example.knupin.domain.Pin;
import com.example.knupin.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    List<Picture> findByPinId(int pinId);
}
