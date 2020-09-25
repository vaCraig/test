package io.swagger.service;

import io.swagger.api.NotFoundException;
import io.swagger.model.Individual;
import io.swagger.model.IndividualCreate;
import io.swagger.model.IndividualUpdate;

import java.util.List;

public interface IndividualService {

    Individual createIndividual(IndividualCreate individualCreate);

    Individual updateIndividual(IndividualUpdate individualUpdate) throws NotFoundException;

    Individual getIndividual(String id) throws NotFoundException;

    List<Individual> listIndividuals();

    boolean deleteIndividual(String id);


}
