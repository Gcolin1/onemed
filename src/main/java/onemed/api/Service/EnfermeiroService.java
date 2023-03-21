package onemed.api.Service;

import onemed.api.enfermeiro.EnfermeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnfermeiroService {
    @Autowired
    private EnfermeiroRepository repository;

    public void deleteEnfermeiro(long id){
        repository.deleteById(id);
    }


}
