package pe.rodcar.okrboard.controller;
import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import io.swagger.annotations.Authorization;
import pe.rodcar.okrboard.entities.Objective;
import pe.rodcar.okrboard.entities.User;
//import pe.rodcar.okrboard.security.services.UserPrinciple;
import pe.rodcar.okrboard.service.ObjectiveService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/users")
//@Api(value = "REST of objectives management")
public class ObjectiveController {

	@Autowired
	private ObjectiveService objectiveService;
	
	//@Autowired
	//private UserService userService;
	
	//@ApiOperation("List of all objectives of an user by id")
	//@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/{userId}/objectives", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Objective>> fetchobjectivesByUserId(@PathVariable("userId") Long userId) {
		//if (!userService.existsById(userId)) {
		//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
		
		try {
			List<Objective> objectives = new ArrayList<>();
			objectives = objectiveService.findByUserId(userId);
			return new ResponseEntity<List<Objective>>(objectives, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//@PreAuthorize("hasRole('USER')")
	//@ApiOperation(value="Save an objective", authorizations = @Authorization(value = "Bearer"))
	@PostMapping(value="/{userId}/objectives", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//public Objective createObjective(@Valid @RequestBody Objective objective){
	public ResponseEntity<Object> saveobjective(@PathVariable("userId") Long userId, @Valid @RequestBody Objective objective) {

		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		//if (authentication instanceof AnonymousAuthenticationToken) {
		//	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		//}
		
		//UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		
		//if (userPrinciple.getId() != userId) {
		//	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		//}
		
		try {
			User user = new User();
			user.setId(userId);
			objective.setUser(user);


			Objective o = objectiveService.save(objective);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(o.getId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//@ApiOperation(value="Update an objective", authorizations = @Authorization(value = "Bearer"))
	@PutMapping(value= "/{userId}/objectives/{id}", consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Objective> updateobjective(@PathVariable("userId") Long userId, @PathVariable("id") Long id, @RequestBody Objective objectiveRequest, Principal principal) {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//if (authentication instanceof AnonymousAuthenticationToken) {
		//	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		//}
		
	//	UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		
		//if (userPrinciple.getId() != userId) {
		//	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		//}
		
		try {
			Optional<Objective> objective = objectiveService.findById(id);
			
			if (!objective.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		//	if (objective.get().getUser().getId() != userPrinciple.getId()) {
		//		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		//	}


			objective.get().setTitle(objectiveRequest.getTitle());
			objective.get().setDescription(objectiveRequest.getDescription());
			objective.get().setOwner(objectiveRequest.getOwner());
			objective.get().setStartDate(objectiveRequest.getStartDate());
			objective.get().setDueDate(objectiveRequest.getDueDate());
			objective.get().setUpdatedOn(objectiveRequest.getUpdatedOn());
			objective.get().setUpdatedBy(objectiveRequest.getUpdatedBy());

			objectiveService.update(objective.get());
			return new ResponseEntity<>(objective.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//@ApiOperation(value="Delete an objective", authorizations = @Authorization(value = "Bearer"))
	@DeleteMapping(value = "/{userId}/objectives/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteobjective(@PathVariable("userId") Long userId, @PathVariable("id") Long id, Principal principal) {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//if (authentication instanceof AnonymousAuthenticationToken) {
		//	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		//}
		
		//UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		
	//	if (userPrinciple.getId() != userId) {
	//		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	//	}
		
		try {
			Optional<Objective> objective = objectiveService.findById(id);
			
			if (!objective.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
			} else {
				objectiveService.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//@ApiOperation("Get an objective by id")
	@GetMapping(value= "/{userId}/objectives/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Objective> fetchobjective(@PathVariable("id") Long id) {
		try {
			Optional<Objective> objective = objectiveService.findById(id);
			
			if (!objective.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(objective.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
