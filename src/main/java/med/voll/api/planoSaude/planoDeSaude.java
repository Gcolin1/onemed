package med.voll.api.planoSaude;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class planoDeSaude {
    private String Ortopedia;
    private String Cardiologia;
    private String Ginecologia;
    private String Dermatologia;

    public planoDeSaude(dadosPlanos dados) {
        this.Ortopedia = dados.Ortopedia();
        this.Cardiologia = dados.Cardiologia();
        this.Ginecologia = dados.Ginecologia();
        this.Dermatologia = dados.Dermatologia();
    }
}
