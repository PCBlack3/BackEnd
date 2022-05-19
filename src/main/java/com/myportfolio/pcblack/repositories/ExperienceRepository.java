package com.myportfolio.pcblack.repositories;

import com.myportfolio.pcblack.models.Experience;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Black
 */
public interface ExperienceRepository extends JpaRepository<Experience, Long>{
    
    public List<Experience> findAllByPersonId(Long personId);
}
