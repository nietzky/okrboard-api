package pe.rodcar.okrboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.rodcar.okrboard.entities.KeyResult;
import pe.rodcar.okrboard.entities.Sponsor;
import pe.rodcar.okrboard.entities.Objective;
import pe.rodcar.okrboard.entities.User;
import pe.rodcar.okrboard.service.KeyResultService;
import pe.rodcar.okrboard.service.ObjectiveService;
import pe.rodcar.okrboard.service.SponsorService;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.Optional;



@RestController
@RequestMapping("/users") // sample call http://localhost:8080/users/1/objectives
//@Api(value= "REST of key results")
public class SponsorController {

    private static final Logger logger = LoggerFactory.getLogger(SponsorController.class);

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private ObjectiveService objectiveService;

    //@Autowired
    //private UserService userService;

	/*@ApiOperation("List of all the key results")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KeyResult>> fetchKeyResult() {
		try {
			List<KeyResult> keyResults = new ArrayList<>();
			keyResults = keyResultService.findAll();
			return new ResponseEntity<>(keyResults, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

    //@PreAuthorize("hasRole('USER')")
    //@ApiOperation(value="Save a key result")//, authorizations = @Authorization(value="Bearer"))
    @PostMapping(value="/{userId}/objectives/{objectiveId}/sponsors", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveSponsor(@PathVariable("userId") Long userId, @PathVariable("objectiveId") Long objectiveId, @Valid @RequestBody Sponsor sponsor, Principal principal) {

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //if (authentication instanceof AnonymousAuthenticationToken) {
        //	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        //}

        //	UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        //	if (userPrinciple.getId() != userId) {
        //		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        //	}

        if (!objectiveService.existsById(objectiveId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            User user = new User();
            user.setId(userId);
            Objective objective = new Objective();
            objective.setId(objectiveId);
            objective.setUser(user);
            sponsor.setObjective(objective);
            Sponsor sponsorPersisted = sponsorService.save(sponsor);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sponsorPersisted.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@ApiOperation("Get a Key Result by id")
    @GetMapping(value="/{userId}/objectives/{objectiveId}/sponsors/{id}")
    public ResponseEntity<Object> fetchKeyResultById(@PathVariable("userId") Long userId, @PathVariable("objectiveId") Long objectiveId, @PathVariable("id") Long id) {
//		if (!userService.existsById(userId) || !objectiveService.existsById(objectiveId)) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}

        try {
            Optional<Sponsor> sponsor = sponsorService.findById(id);

            if (!sponsor.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(sponsor.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@PreAuthorize("hasRole('USER')")
    //@ApiOperation(value="Updates a Key Result by id")//, authorizations = @Authorization(value="Bearer"))
    @PutMapping(value="/{userId}/objectives/{objectiveId}/sposnors/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sponsor> updateKeyResult(@PathVariable("userId") Long userId, @PathVariable("objectiveId") Long objectiveId, @PathVariable("id") Long id, @Valid @RequestBody Sponsor sponsorRequest, Principal principal) {

        //	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //	if (authentication instanceof AnonymousAuthenticationToken) {
        //		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        //	}

        //UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        //	if (userPrinciple.getId() != userId) {
        //		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        //}

        if (!objectiveService.existsById(objectiveId) || !sponsorService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Sponsor sponsor = sponsorService.findById(id).get();

            //logger.info("user id finded: " + keyResult.getObjective().getUser().getId() + ", user principle id: " + userPrinciple.getId());
            //if (keyResult.getObjective().getUser().getId() != userPrinciple.getId()) {
            //	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            //}

            sponsor.setSid(sponsorRequest.getSid());
            sponsor.setLastName(sponsorRequest.getLastName());
            Sponsor sponsorUpdated = sponsorService.update(sponsor);
            return new ResponseEntity<>(sponsorUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@PreAuthorize("hasRole('USER')")
    //@ApiOperation(value="Deletes an Key Result by id")//, authorizations = @Authorization(value = "Bearer"))
    @DeleteMapping(value="/{userId}/objectives/{objectiveId}/sponsors/{id}")
    public ResponseEntity<Object> deleteKeyResult(@PathVariable("userId") Long userId, @PathVariable("objectiveId") Long objectiveId, @PathVariable("id") Long id, Principal principal) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //if (authentication instanceof AnonymousAuthenticationToken) {
        //	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        //}

        //UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        //	if (userPrinciple.getId() != userId) {
        //		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        //	}

        if (!objectiveService.existsById(objectiveId) || !sponsorService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Sponsor sponsor = sponsorService.findById(id).get();

            //	if (userPrinciple.getId() != keyResult.getObjective().getUser().getId()) {
            //		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            //	}

            sponsorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
