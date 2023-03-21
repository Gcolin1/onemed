package onemed.api.controller;

import jakarta.transaction.Transactional;
import onemed.api.Atendimento.Atendimento;
import onemed.api.Atendimento.AtendimentoRepository;
import onemed.api.Atendimento.DadosCadastroAtendimento;
import onemed.api.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    AtendimentoRepository repository;

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping
    @Transactional
    //requestBody vai pegar o json enviado
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody DadosCadastroAtendimento dados){

        repository.save(new Atendimento(dados));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Medico cadastrado com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
