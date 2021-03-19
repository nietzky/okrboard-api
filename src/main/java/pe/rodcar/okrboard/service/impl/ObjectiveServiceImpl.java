package pe.rodcar.okrboard.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.rodcar.okrboard.entities.Objective;
import pe.rodcar.okrboard.repository.ObjectiveRepository;
import pe.rodcar.okrboard.service.ObjectiveService;

@Service
public class ObjectiveServiceImpl implements ObjectiveService {

	@Autowired
	private ObjectiveRepository objectiveRepository;

	@Override
	public List<Objective> findAll() throws Exception {
		return objectiveRepository.findAll();
	}

	@Override
	public Objective save(Objective t) throws Exception {
		return objectiveRepository.save(t);
	}

	@Override
	public Objective update(Objective t) throws Exception {
		return objectiveRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		objectiveRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// Method not implemented
	}

	@Override
	public Optional<Objective> findById(Long id) throws Exception {
		return objectiveRepository.findById(id);
	}

	@Override
	public List<Objective> findByUserId(Long id) throws Exception {
		return objectiveRepository.findByUserId(id);
	}

	@Override
	public boolean existsById(Long id) {
		return objectiveRepository.existsById(id);
	}
	
}
