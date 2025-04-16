package com.bys.formation.services;

import com.bys.formation.entities.Formation;
import com.bys.formation.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationServiceImpl implements FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Override
    public List<Formation> retrieveAllFormations() {
        return (List<Formation>) formationRepository.findAll();
    }

    @Override
    public Formation addFormation(Formation f) {

        if (f.getVideoUrl() == null || f.getVideoUrl().isEmpty()) {
            f.setVideoUrl("default_video_url");
        }
        return formationRepository.save(f);
    }

    @Override
    public void deleteFormation(Long id) {
        formationRepository.deleteById(id);
    }

    @Override
    public Formation updateFormation(Formation f) {

        return formationRepository.save(f);
    }

    @Override
    public Formation retrieveFormation(Long id) {
        return formationRepository.findById(id).orElse(null);
    }
}
