package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.Planos_de_saude.DadosCadastroPlanos;
import med.voll.api.Planos_de_saude.DadosListagemPlanos;
import med.voll.api.Planos_de_saude.PlanoDeSaude;
import med.voll.api.Planos_de_saude.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
}
