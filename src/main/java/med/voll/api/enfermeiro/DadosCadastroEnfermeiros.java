package med.voll.api.enfermeiro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroEnfermeiros(
        @NotBlank(message = "nome é obrigatório")
        String nome,
        @NotBlank(message = "cpf é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "cpf incorreto")
        String cpf,
        @NotBlank(message = "email é obrigatório")
        @Email(message = "email incorreto")
        String email,
        @NotBlank(message = "cre é obrigatório")
        @Pattern(regexp = "\\d{4,6}", message = "cre incorreto")
        String cre,
        @NotBlank(message = "telefone é obrigatório")
        String telefone,
        DadosEndereco endereco

        ) {
}
