package io.swagger.service;

import io.swagger.api.NotFoundException;
import io.swagger.model.Individual;
import io.swagger.model.IndividualCreate;
import io.swagger.model.IndividualUpdate;
import io.swagger.repository.IndividualRepo;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IndividualServiceImpl implements IndividualService {

    private DozerBeanMapper mapper = new DozerBeanMapper();

    private IndividualRepo individualRepo;

    public IndividualServiceImpl(IndividualRepo individualRepo){
        this.individualRepo = individualRepo;
    }

    @Override
    public Individual createIndividual(IndividualCreate individualCreate) {
        Individual individual = mapper.map(individualCreate, Individual.class);
        individualRepo.save(individual);
        return individual;
    }

    @Override
    public Individual updateIndividual(IndividualUpdate individualUpdate) throws NotFoundException {

        Individual individual = individualRepo.findById(individualUpdate.getId());

        if (Objects.nonNull(individual)) {
            individual = mapper.map(individualUpdate, Individual.class);
            individual = individualRepo.save(individual);
        } else {
            throw new NotFoundException(9,"Individual to update Not found");
        }
        return individual;
    }

    @Override
    public Individual getIndividual(String id) throws NotFoundException{
        Individual individual = individualRepo.findById(id);

        if (Objects.isNull(individual)) {
            throw new NotFoundException(9, "Individual Not Found");
        }
        return individual;
    }

    @Override
    public List<Individual> listIndividuals() {
        return individualRepo.findAll();
    }

    @Override
    public boolean deleteIndividual(String id) {
        boolean returnValue = false;

        Individual individual = individualRepo.findById(id);
        if (Objects.nonNull(individual)) {
            individualRepo.delete(individual);
            returnValue = true;
        }
        return returnValue;
    }
}
