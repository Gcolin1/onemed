package onemed.api.Atendimento;


import jakarta.persistence.*;
import lombok.*;
import onemed.api.medico.Especialidade;


@Table(name = "atendimento")
@Entity(name = "Atendimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paciente;

    String data;

    String horario;

    private Especialidade especialidade;

    private String descricao;

    public Atendimento(DadosCadastroAtendimento dados){
        this.data = dados.data();
        this.descricao = dados.descricao();
        this.especialidade = dados.especialidade();
        this.horario = dados.horario();
        this.paciente = dados.paciente();
    }
}