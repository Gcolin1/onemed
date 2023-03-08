package med.voll.api.enfermeiro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "enfermeiros")
@Entity(name = "Enfermeiros")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Enfermeiro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String cre;
    private String cpf;
    private String telefone;
    @Embedded
    private Endereco endereco;

    public Enfermeiro(DadosCadastroEnfermeiro dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cre = dados.cre();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }
}
