package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.Category;
import com.landingis.api.storage.model.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NationRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Nation> {

}
