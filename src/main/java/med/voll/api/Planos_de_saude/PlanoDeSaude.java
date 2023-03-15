package med.voll.api.Planos_de_saude;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "plano_de_saude")
@Entity(name = "PlanoDeSaude")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PlanoDeSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;
    private String telefone;
    private String coberturas;

    public PlanoDeSaude(DadosCadastroPlanos dados){
        this.nome = dados.nome();
        this.cnpj = dados.cnpj();
        this.telefone = dados.telefone();
        this.coberturas = dados.coberturas();
    }

}
