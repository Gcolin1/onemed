package onemed.api.Service;

import onemed.api.Atendimento.Atendimento;
import onemed.api.Atendimento.AtendimentoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtendimentoService {

    AtendimentoRepository repository;

    public Atendimento buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void deleteAtendimento(Long id){
        repository.deleteById(id);
    }
}
