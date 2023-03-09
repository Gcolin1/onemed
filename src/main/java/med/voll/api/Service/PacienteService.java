package med.voll.api.Service;

import med.voll.api.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    //função para deletar medico do banco de dados pelo id puxando do repository
    public void deletePaciente(Long id){
        repository.deleteById(id);
    }
}
