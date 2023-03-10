package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.enfermeiro.DadosCadastroEnfermeiro;
import med.voll.api.enfermeiro.Enfermeiro;
import med.voll.api.enfermeiro.EnfermeiroRepository;
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
}
