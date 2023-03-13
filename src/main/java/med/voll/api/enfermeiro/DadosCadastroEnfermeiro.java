package med.voll.api.enfermeiro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroEnfermeiro(
        // falha se for nulo e ou se for vazio
        @NotBlank(message = "Nome é obrigatório!")
        String nome,
        @NotBlank(message = "CPF é obrigatório!")
        @Pattern(regexp = "\\d{11}", message = "CPF incorreto!")
        String cpf,
        @NotBlank(message = "CRE é obrigatório!")
        @Pattern(regexp = "\\d{4,6}", message = "CRE incorreto!")
        String cre,
        @NotBlank(message = "E-mail é obrigatório!")
        @Email(message = "E-mail incorreto!")
        String email,
        @NotBlank(message = "Telefone é obrigatório!")
        String telefone,
        DadosEndereco endereco) {
}
