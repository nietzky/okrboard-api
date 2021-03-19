package pe.rodcar.okrboard.service;

import pe.rodcar.okrboard.entities.Sponsor;

public interface SponsorService extends CrudService<Sponsor>{

    boolean existsById(Long id);

}
