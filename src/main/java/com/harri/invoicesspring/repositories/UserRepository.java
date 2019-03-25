package com.harri.invoicesspring.repositories;

import com.harri.invoicesspring.models.Invoice;
import com.harri.invoicesspring.models.User;                //Bean.User
import org.springframework.data.jpa.repository.JpaRepository;           // * thing
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@Repository
//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(@Param("username") String username);

}
