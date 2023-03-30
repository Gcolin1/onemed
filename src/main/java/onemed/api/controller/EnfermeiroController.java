package onemed.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import onemed.api.enfermeiro.Service.EnfermeiroService;
import onemed.api.enfermeiro.DadosCadastroEnfermeiro;
import onemed.api.enfermeiro.DadosListaEnfermeiro;
import onemed.api.enfermeiro.Enfermeiro;
import onemed.api.enfermeiro.EnfermeiroRepository;
import onemed.api.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/enfermeiros")
public class EnfermeiroController{
    //passa para o controller a injeção de dependencias automatica
    @Autowired
    private EnfermeiroRepository repository;

    @PostMapping
    @Transactional
    @CrossOrigin(origins = "http://localhost:8100")
    //requestBody vai pegar o json enviado
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody @Valid DadosCadastroEnfermeiro dados){
        repository.save(new Enfermeiro(dados));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Enfermeiro cadastrado com sucesso!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping
    public Page<DadosListaEnfermeiro> buscar(Pageable pagina){
        return  repository.findAll(pagina).map(DadosListaEnfermeiro::new);
    }
    @Autowired
    private EnfermeiroService enfermeiroService;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/{id}")
    public ResponseEntity<Enfermeiro> buscarEnfermeiroId(@PathVariable Long id) {
        Enfermeiro enfermeiro = enfermeiroService.buscarPorId(id);
        return ResponseEntity.ok().body(enfermeiro);
    }

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


    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value = "/buscar", produces = "application/json")
    public ResponseEntity<List<Enfermeiro>> getEnfermeiroByid(@RequestParam(name = "nome")String nome){
        List<Enfermeiro> enfermeiros = repository.getEnfermeiroName(nome);

        return new ResponseEntity<List<Enfermeiro>>(enfermeiros, HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> tratar(ConstraintViolationException exception) {
        List<String> erros = new ArrayList<>();

        for(ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String erro = String.format(
                    "%s %s",
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
            );

            erros.add(erro);
        }

        return erros;
    }
}
