package onemed.api.pacientes;

import jakarta.persistence.*;
import lombok.*;
import onemed.api.Atendimento.Atendimento;
import onemed.api.endereco.Endereco;

import java.util.List;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String cpf;

    @Embedded
    private Endereco endereco;

    //@OneToMany(mappedBy = "paciente")
    //private List<Atendimento> atendimentos;

    public Paciente(DadosCadastroPacientes dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }
}
