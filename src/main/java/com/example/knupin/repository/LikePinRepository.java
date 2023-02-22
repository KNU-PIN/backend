package com.example.knupin.repository;

import com.example.knupin.domain.LikePin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LikePinRepository extends JpaRepository<LikePin, Integer> {
    Optional<LikePin> findByPinIdAndIp(int pinId, String ip);
    int countByPinId(int pinId);
}
