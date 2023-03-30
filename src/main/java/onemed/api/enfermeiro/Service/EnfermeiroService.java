package onemed.api.enfermeiro.Service;

import onemed.api.enfermeiro.Enfermeiro;
import onemed.api.enfermeiro.EnfermeiroRepository;
import onemed.api.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnfermeiroService {
    @Autowired
    private EnfermeiroRepository repository;

    public void deleteEnfermeiro(long id){
        repository.deleteById(id);
    }

    public Enfermeiro buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }


}
