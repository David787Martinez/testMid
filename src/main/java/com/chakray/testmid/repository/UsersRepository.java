package com.chakray.testmid.repository;

import com.chakray.testmid.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author luis-barrera
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Integer>, JpaSpecificationExecutor<UsersModel>{
    
    boolean existsByTaxId(String taxId);      
    
}
