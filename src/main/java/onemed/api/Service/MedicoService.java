package onemed.api.Service;

import onemed.api.medico.Medico;
import onemed.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
