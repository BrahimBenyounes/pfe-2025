
package com.bys.formation.services;

import com.bys.formation.entities.Formation;

import java.util.List;

public interface FormationService {

    List<Formation> retrieveAllFormations();

    Formation addFormation(Formation f);

    void deleteFormation(Long id);

    Formation updateFormation(Formation f);

    Formation retrieveFormation(Long id);
}
