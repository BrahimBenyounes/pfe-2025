package com.bys.formation.repositories;

import com.bys.formation.entities.Formation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Long> {
}
