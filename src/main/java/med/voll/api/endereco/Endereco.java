package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
            private String logradouro;
            private String cep;
            private String bairro;
            private String numero;
            private String complemento;
            private String cidade;
            private String uf;

            public Endereco(DadosEndereco dados) {
                this.bairro = dados.bairro();
                this.logradouro = dados.logradouro();
                this.cep = dados.cep();
                this.cidade = dados.cidade();
                this.numero = dados.numero();
                this.uf = dados.uf();
                this.complemento = dados.complemento();
            }
}