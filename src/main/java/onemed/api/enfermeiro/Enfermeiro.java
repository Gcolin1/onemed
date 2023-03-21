package onemed.api.enfermeiro;

import jakarta.persistence.*;
import lombok.*;
import onemed.api.endereco.Endereco;

@Table(name = "enfermeiros")
@Entity(name = "Enfermeiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Enfermeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
