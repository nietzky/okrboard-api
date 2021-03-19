package pe.rodcar.okrboard.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.rodcar.okrboard.entities.Sponsor;
import pe.rodcar.okrboard.repository.SponsorRepository;
import pe.rodcar.okrboard.service.SponsorService;

@Service
public class SponsorServiceImpl implements SponsorService{

    @Autowired
    private SponsorRepository sponsorRepository;

    @Override
    public List<Sponsor> findAll() throws Exception {
        return sponsorRepository.findAll();
    }

    @Override
    public Sponsor save(Sponsor t) throws Exception {
        return sponsorRepository.save(t);
    }

    @Override
    public Sponsor update(Sponsor t) throws Exception {
        return sponsorRepository.save(t);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        sponsorRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        // Method not implemented
    }

    @Override
    public Optional<Sponsor> findById(Long id) throws Exception {
        return sponsorRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return sponsorRepository.existsById(id);
    }

}
