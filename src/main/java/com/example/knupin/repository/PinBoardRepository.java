package com.example.knupin.repository;

import com.example.knupin.domain.Pin;
import com.example.knupin.model.SearchPinInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PinBoardRepository extends JpaRepository<Pin, Integer> {

    List<Pin> findByPinId(int pinId);

    @Query(value =
            "select p.pin_id as pinId, latitude, longitude, type\n" +
                    "from `knu-pin`.like_pin,\n" +
                    "(\n" +
                    "   select * \n" +
                    "\tfrom `knu-pin`.pin\n" +
                    "\twhere (title like CONCAT('%',:keyword,'%')\n" +
                    "\tOR contents like CONCAT('%',:keyword,'%') )\n" +
                    "\tand is_deleted = 0\n" +
                    "\tand (latitude between :latitudeleft and :latituderight)\n" +
                    "\tand (longitude between :longitudeleft and :longituderight)\n" +
                    ") as p\n" +
                    "where like_pin.pin_id = p.pin_id\n" +
                    "group by p.pin_id\n" +
                    "order by count(*) desc, p.created_at desc\n" +
                    "limit 1", nativeQuery = true)
    public Optional<SearchPinInterface> searchPin(String keyword, int latitudeleft, int latituderight, int longitudeleft, int longituderight);

}
