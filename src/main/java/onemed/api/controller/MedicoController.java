package onemed.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import onemed.api.Service.MedicoService;
import onemed.api.medico.DadosCadastroMedico;
import onemed.api.medico.DadosListagemMedico;
import onemed.api.medico.Medico;
import onemed.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//defini endpoint da url
@RequestMapping("/medicos")
public class MedicoController {

    //passa para o controller a injeção de dependencias automatica
    @Autowired
    private MedicoRepository repository;

    //Metodo post pra enviar dados pra api
    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping
    @Transactional
                        //requestBody vai pegar o json enviado
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Medico cadastrado com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping
    public Page<DadosListagemMedico> listarMedicos(Pageable paginacao){
        //find all metodo do JPA repository
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @Autowired
    private MedicoService medicoService;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedicoPorId(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok().body(medico);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @DeleteMapping("/{id}")//caminho para ser deletado por id
    //ResponseEntity retorna resposta se foi executado com sucesso
    public  ResponseEntity<Map<String, Object>> deletarMedico(@PathVariable Long id){
        //chamando a função deleteMedico do medicoService passando como parametro o id do medico
        medicoService.deleteMedico(id);
        //retornando um json como resposta caso ao delete seja executado com sucesso
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Medico excluído com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizarUsuario(@PathVariable Long id, @RequestBody Medico medicoAtualizado){
        Optional<Medico> medicoExistenteOptional = repository.findById(id);

        if (!medicoExistenteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Medico medicoExistente = medicoExistenteOptional.get();
        medicoExistente.setNome(medicoAtualizado.getNome());
        medicoExistente.setTelefone(medicoAtualizado.getTelefone());
        medicoExistente.setEmail(medicoAtualizado.getEmail());
        medicoExistente.setCrm(medicoAtualizado.getCrm());
        medicoExistente.setEspecialidade(medicoAtualizado.getEspecialidade());
        medicoExistente.setEndereco(medicoAtualizado.getEndereco());
        Medico medicoAtualizadoSalvo = repository.save(medicoExistente);

        return ResponseEntity.ok(medicoAtualizadoSalvo);
    }
    
    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value = "/buscar", produces = "application/json")
    public ResponseEntity<List<Medico>> getMedicoById(@RequestParam(name = "nome") String nome){
        List<Medico> medicos = repository.getMedicoByName(nome);

        return new ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
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
