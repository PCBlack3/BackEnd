
package com.myportfolio.pcblack.repositories;

import com.myportfolio.pcblack.models.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Black
 */
public interface ProjectRepository extends JpaRepository<Project, Long>{
        
    public List<Project> findAllByPersonId(Long personId);

}
