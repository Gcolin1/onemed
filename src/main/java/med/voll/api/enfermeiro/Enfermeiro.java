package med.voll.api.enfermeiro;

import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import med.voll.api.endereco.Endereco;

@Getter

public class Enfermeiro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String cre;
    private String telefone;
    @Embedded
    private Endereco endereco;

    public Enfermeiro(DadosCadastroEnfermeiro dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cre = dados.cre();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }
}
