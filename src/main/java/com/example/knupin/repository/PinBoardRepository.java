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
            "select pin_id as pinId, latitude, longitude, type\n" +
                    "from (\n" +
                    "\tselect p.*, IFNULL(count,0) as like_cnt \n" +
                    "\tfrom pin p left join \n" +
                    "\t\t(select pin_id, count(*) as count from like_pin group by pin_id) l on p.pin_id = l.pin_id\n" +
                    "\twhere (title like CONCAT('%',:keyword,'%')\n" +
                    "\t\tOR contents like CONCAT('%',:keyword,'%') )\n" +
                    "\t\tand is_deleted = 0\n" +
                    "\t\tand (latitude between :latitudeleft and :latituderight)\n" +
                    "\t\tand (longitude between :longitudeleft and :longituderight)\n" +
                    "\torder by like_cnt desc, p.created_at desc\n" +
                    "\tlimit 1) as result", nativeQuery = true)
    public Optional<SearchPinInterface> searchPin(String keyword, int latitudeleft, int latituderight, int longitudeleft, int longituderight);

}
