package med.voll.api.Service;

import med.voll.api.Planos_de_saude.PlanoDeSaude;
import med.voll.api.Planos_de_saude.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanoService {
    @Autowired
    private PlanoRepository repository;

    public void deletePlano(Long id){

        repository.deleteById(id);
    }

    public PlanoDeSaude busPorId(Long id) {

        return repository.findById(id).orElseThrow();
    }
}
