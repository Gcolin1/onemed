package med.voll.api.planoSaude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

    public record dadosPlanos(

            String Ortopedia,

            String Cardiologia,

            String Ginecologia,

            String Dermatologia
    ) {

    }
