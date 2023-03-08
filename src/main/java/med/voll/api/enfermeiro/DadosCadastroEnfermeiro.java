package med.voll.api.enfermeiro;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroEnfermeiro(
        // falha se for nulo e ainda se for vazia
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String cre,
        @NotBlank
        String telefone,
        @NotNull @Valid DadosEndereco endereco) {
}
