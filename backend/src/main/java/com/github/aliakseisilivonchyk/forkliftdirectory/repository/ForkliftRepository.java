package com.github.aliakseisilivonchyk.forkliftdirectory.repository;

import com.github.aliakseisilivonchyk.forkliftdirectory.model.Forklift;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForkliftRepository extends ListCrudRepository<Forklift, Integer>, JpaSpecificationExecutor<Forklift> {
}
