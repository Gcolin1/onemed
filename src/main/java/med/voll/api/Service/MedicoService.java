package med.voll.api.Service;

import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Service
public class MedicoService{

    @Autowired
    private MedicoRepository repository;

    //função para deletar medico do banco de dados pelo id puxando do repository
    public void deleteMedico(Long id){
        repository.deleteById(id);
    }

    public Medico buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
