package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.Planos_de_saude.DadosCadastroPlanos;
import med.voll.api.Planos_de_saude.DadosListagemPlanos;
import med.voll.api.Planos_de_saude.PlanoDeSaude;
import med.voll.api.Planos_de_saude.PlanoRepository;
import med.voll.api.Service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/planos")
public class PlanoController {
    @Autowired
    PlanoRepository repository;

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody DadosCadastroPlanos dados){
        repository.save(new PlanoDeSaude(dados));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Plano cadastrado com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping
    public Page<DadosListagemPlanos> DadosListaPlanos(Pageable paginacao){
        //find all metodo do JPA repository
        return repository.findAll(paginacao).map(DadosListagemPlanos::new);
    }

    @Autowired
    private PlanoService planoService;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/{id}")
    public ResponseEntity<PlanoDeSaude> buscarPlanoPorId(@PathVariable Long id) {
        PlanoDeSaude plano = planoService.busPorId(id);
        return ResponseEntity.ok().body(plano);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Map<String, Object>> deletarPlano(@PathVariable Long id){
        planoService.deletePlano(id);
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("message", "Plano de Saúde excluído com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDeSaude> atualizarPlano(@PathVariable Long id, @RequestBody PlanoDeSaude planoAtualizado){
        Optional<PlanoDeSaude> planoExistenteOptional = repository.findById(id);

        if (!planoExistenteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        PlanoDeSaude planoExistente = planoExistenteOptional.get();
        planoExistente.setNome(planoAtualizado.getNome());
        planoExistente.setTelefone(planoAtualizado.getTelefone());
        planoExistente.setCnpj(planoAtualizado.getCnpj());
        PlanoDeSaude planoAtualizadoSalvo = repository.save(planoExistente);

        return ResponseEntity.ok(planoAtualizadoSalvo);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value = "/buscar", produces = "application/json")
    public ResponseEntity<List<PlanoDeSaude>> getMedicoById(@RequestParam(name = "nome") String nome){
        List<PlanoDeSaude> plano = repository.getPlanoByName(nome);

        return new ResponseEntity<List<PlanoDeSaude>>(plano, HttpStatus.OK);
    }
}
