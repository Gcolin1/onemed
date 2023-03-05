package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//defini endpoint da url
@RequestMapping("/medicos")
public class MedicoController {

    //passa para o controller a injeção de dependencias automatica
    @Autowired
    private MedicoRepository repository;

    //Metodo post pra enviar dados pra api
    @PostMapping
    @Transactional
                        //requestBody vai pegar o json enviado
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listarMedicos(Pageable paginacao){
        //find all metodo do JPA repository
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
}
