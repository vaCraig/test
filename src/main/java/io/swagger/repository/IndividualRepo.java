package io.swagger.repository;

import io.swagger.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepo  extends JpaRepository<Individual, String> {

    Individual findById(String id);

}
