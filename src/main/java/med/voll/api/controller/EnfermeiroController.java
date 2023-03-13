package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.Service.EnfermeiroService;
import med.voll.api.enfermeiro.*;
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
@RequestMapping("/enfermeiros")
public class EnfermeiroController{
    //passa para o controller a injeção de dependencias automatica
    @Autowired
    private EnfermeiroRepository repository;

    @PostMapping
    @Transactional
    //requestBody vai pegar o json enviado
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody @Valid DadosCadastroEnfermeiro dados){
        repository.save(new Enfermeiro(dados));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Enfermeiro cadastrado com sucesso!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public Page<DadosListaEnfermeiro> buscar(Pageable pagina){
        return  repository.findAll(pagina).map(DadosListaEnfermeiro::new);
    }
    @Autowired
    private EnfermeiroService enfermeiroService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletarEnfermeiro(@PathVariable long id) {
        enfermeiroService.deleteEnfermeiro(id);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("message", "Usuário excluído com sucesso.");
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enfermeiro> atualizarEnfer(@PathVariable Long id, @RequestBody Enfermeiro enferAtualizado){
        Optional<Enfermeiro> enferExisteOpcional = repository.findById(id);

        if (!enferExisteOpcional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Enfermeiro enferExiste = enferExisteOpcional.get();
        enferExiste.setNome(enferAtualizado.getNome());
        enferExiste.setTelefone(enferAtualizado.getTelefone());
        enferExiste.setEmail(enferAtualizado.getEmail());
        enferExiste.setCre(enferAtualizado.getCre());
        enferExiste.setEndereco(enferAtualizado.getEndereco());
        Enfermeiro enferAtuaSalvo = repository.save(enferExiste);

        return ResponseEntity.ok(enferAtuaSalvo);
    }

    @GetMapping(value = "\buscar", produces = "application/json")
    public ResponseEntity<List<Enfermeiro>> getEnfermeiroByid(@RequestParam(name = "nome")String nome){
        List<Enfermeiro> enfermeiros = repository.getEnfermeiroName(nome);

        return new ResponseEntity<List<Enfermeiro>>(enfermeiros, HttpStatus.OK);
    }
}
