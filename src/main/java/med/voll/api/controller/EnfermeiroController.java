package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.enfermeiro.DadosCadastroEnfermeiros;
import med.voll.api.enfermeiro.Enfermeiro;
import med.voll.api.enfermeiro.EnfermeiroRepository;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.pacientes.DadosCadastroPacientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/enfermeiros")
public class EnfermeiroController {

    @Autowired
    private EnfermeiroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody @Valid DadosCadastroEnfermeiros dados){
        repository.save(new Enfermeiro(dados));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Enfermeiro cadastrado com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
