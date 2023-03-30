package onemed.api.controller;

import jakarta.transaction.Transactional;
import onemed.api.Atendimento.Atendimento;
import onemed.api.Atendimento.AtendimentoRepository;
import onemed.api.Atendimento.DadosCadastroAtendimento;
import onemed.api.Atendimento.DadosListagemAtendimento;
import onemed.api.Service.AtendimentoService;
import onemed.api.Service.PacienteService;
import onemed.api.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    AtendimentoRepository repository;

    @Autowired
    PacienteService pacienteService;

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping
    @Transactional
    //requestBody vai pegar o json enviado
    public HttpStatus cadastrar(@RequestBody DadosCadastroAtendimento dados){
        //repository.save(new Atendimento(dados /*pacienteService.buscarPorId(//dados.paciente_id()*/));
        //return HttpStatus.OK;
        repository.save(new Atendimento(dados));
        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping
    public Page<DadosListagemAtendimento> listarAtendimentos(Pageable paginacao){
        //find all metodo do JPA repository
        return repository.findAll(paginacao).map(DadosListagemAtendimento::new);
    }

    @Autowired
    private AtendimentoService atendimentoService;

    @DeleteMapping("/{id}")
    public HttpStatus deletarAtendimento(@PathVariable long id) {
        atendimentoService.deleteAtendimento(id);

        return  HttpStatus.OK;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PutMapping("/{id}")
    public ResponseEntity<Atendimento> atualizarAtendimento(@PathVariable Long id, @RequestBody Atendimento atendimentoAtualizado){
        Optional<Atendimento> atendimentoExistenteOptional = repository.findById(id);

        if (!atendimentoExistenteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Atendimento atendimentoExistente = atendimentoExistenteOptional.get();
        atendimentoExistente.setData(atendimentoAtualizado.getData());
        atendimentoExistente.setHorario(atendimentoAtualizado.getHorario());
        atendimentoExistente.setDescricao(atendimentoAtualizado.getDescricao());
        atendimentoExistente.setEspecialidade(atendimentoAtualizado.getEspecialidade());
        Atendimento atendimentoAtualizadoSalvo = repository.save(atendimentoExistente);

        return ResponseEntity.ok(atendimentoAtualizadoSalvo);
    }
}
