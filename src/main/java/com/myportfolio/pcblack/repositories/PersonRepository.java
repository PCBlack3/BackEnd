
package com.myportfolio.pcblack.repositories;

import com.myportfolio.pcblack.models.Person;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Black
 */
public interface PersonRepository extends JpaRepository<Person,Long>{
    public abstract ArrayList<Person> findByApellido(String apellido);
}
