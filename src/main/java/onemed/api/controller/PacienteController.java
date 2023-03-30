package onemed.api.controller;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import onemed.api.pacientes.DadosCadastroPacientes;
import onemed.api.pacientes.DadosListagemPaciente;
import onemed.api.pacientes.Paciente;
import onemed.api.Service.PacienteService;
import onemed.api.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//defini endpoint da url
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;


    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping
    public Page<DadosListagemPaciente> listarPacientes(Pageable paginacao){
        //find all metodo do JPA repository
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }


    @PostMapping
    @Transactional
    @CrossOrigin(origins = "http://localhost:8100")
    public void cadastrar(@RequestBody @Valid DadosCadastroPacientes dados){
        repository.save(new Paciente(dados));
    }

    @Autowired
    private PacienteService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletarPaciente(@PathVariable Long id){
        service.deletePaciente(id);

        //retornando um json como resposta caso ao delete seja executado com sucesso
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Usuário excluído com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente pacienteAtualizado){
        Optional<Paciente> pacienteExistenteOptional = repository.findById(id);

        if (!pacienteExistenteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Paciente pacienteExistente = pacienteExistenteOptional.get();
        pacienteExistente.setNome(pacienteAtualizado.getNome());
        pacienteExistente.setEmail(pacienteAtualizado.getEmail());
        pacienteExistente.setCpf(pacienteAtualizado.getCpf());
        pacienteExistente.setEndereco(pacienteAtualizado.getEndereco());
        Paciente pacienteAtualizadoSalvo = repository.save(pacienteExistente);

        return ResponseEntity.ok(pacienteAtualizadoSalvo);
    }

    //QUERY PARA BUSCAR PACIENTES PELO NOME
    @GetMapping(value = "/buscarPaciente", produces = "application/json")
    public ResponseEntity<List<Paciente>> getPacienteById(@RequestParam(name = "nome") String nome){
        List<Paciente> pacientes = repository.getPacienteByName(nome);

        return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
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
