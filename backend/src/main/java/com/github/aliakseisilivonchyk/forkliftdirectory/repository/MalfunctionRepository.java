package com.github.aliakseisilivonchyk.forkliftdirectory.repository;

import com.github.aliakseisilivonchyk.forkliftdirectory.model.Malfunction;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MalfunctionRepository extends ListCrudRepository<Malfunction, Integer> {

    List<Malfunction> findAllByForkliftIdOrderByStartTimestampDesc(int forkliftId);
}
