package io.swagger.api;

import io.swagger.model.Individual;
import io.swagger.model.IndividualCreate;
import io.swagger.model.IndividualUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.IndividualService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-08T11:42:54.708Z")

@Controller
@RequestMapping("/individual")
public class IndividualApiController implements IndividualApi {

    private static final Logger log = LoggerFactory.getLogger(IndividualApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private IndividualService individualService;

    @org.springframework.beans.factory.annotation.Autowired
    public IndividualApiController(ObjectMapper objectMapper, HttpServletRequest request,
                                   IndividualService individualService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.individualService = individualService;
    }

    @PostMapping("/create")
    public ResponseEntity<Individual> createIndividual(@ApiParam(value = "The Individual to be created", required = true) @Valid @RequestBody IndividualCreate individual) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Individual createdIndividual = individualService.createIndividual(individual);

                if (Objects.nonNull(createdIndividual)) {
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(createdIndividual.getId()).toUri();

                    return ResponseEntity.created(location).body(createdIndividual);
                } else {
                    ResponseEntity.badRequest().body(individual);
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Individual>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteIndividual(@ApiParam(value = "Identifier of the Individual", required = true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        try {
            boolean success = individualService.deleteIndividual(id);

            if (success) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Individual>> listIndividual(@ApiParam(value = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) String fields, @ApiParam(value = "Requested index for start of resources to be provided in response") @Valid @RequestParam(value = "offset", required = false) Integer offset, @ApiParam(value = "Requested number of resources to be provided in response") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Individual> individuals = new ArrayList<>();
            try {
                individuals = individualService.listIndividuals();
                return ResponseEntity.ok(individuals);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Individual>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/patch")
    public ResponseEntity<Individual> patchIndividual(@ApiParam(value = "Identifier of the Individual", required = true) @PathVariable("id") String id, @ApiParam(value = "The Individual to be updated", required = true) @Valid @RequestBody IndividualUpdate individual) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Individual updateedIndividual = individualService.updateIndividual(individual);
                return new ResponseEntity<Individual>(objectMapper.readValue("{  \"aristocraticTitle\" : \"aristocraticTitle\",  \"gender\" : \"gender\",  \"@baseType\" : \"@baseType\",  \"countryOfBirth\" : \"countryOfBirth\",  \"@type\" : \"@type\",  \"familyName\" : \"familyName\",  \"deathDate\" : \"2000-01-23T04:56:07.000+00:00\",  \"fullName\" : \"fullName\",  \"id\" : \"id\",  \"href\" : \"href\",  \"@schemaLocation\" : \"http://example.com/aeiou\",  \"birthDate\" : \"2000-01-23T04:56:07.000+00:00\"}", Individual.class), HttpStatus.NOT_IMPLEMENTED);

            } catch (NotFoundException nfe) {
                return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/retrieve")
    public ResponseEntity<Individual> retrieveIndividual(@ApiParam(value = "Identifier of the Individual", required = true) @PathVariable("id") String id, @ApiParam(value = "Comma-separated properties to provide in response") @Valid @RequestParam(value = "fields", required = false) String fields) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Individual individual = individualService.getIndividual(id);
                return new ResponseEntity<Individual>(objectMapper.readValue("{  \"aristocraticTitle\" : \"aristocraticTitle\",  \"gender\" : \"gender\",  \"@baseType\" : \"@baseType\",  \"countryOfBirth\" : \"countryOfBirth\",  \"@type\" : \"@type\",  \"familyName\" : \"familyName\",  \"deathDate\" : \"2000-01-23T04:56:07.000+00:00\",  \"fullName\" : \"fullName\",  \"id\" : \"id\",  \"href\" : \"href\",  \"@schemaLocation\" : \"http://example.com/aeiou\",  \"birthDate\" : \"2000-01-23T04:56:07.000+00:00\"}", Individual.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (NotFoundException e) {
                return new ResponseEntity<Individual>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Individual>(HttpStatus.BAD_REQUEST);
    }

}
