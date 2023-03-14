package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.Service.MedicoService;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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


}
