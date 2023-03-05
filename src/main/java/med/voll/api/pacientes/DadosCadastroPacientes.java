package med.voll.api.pacientes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.medico.Especialidade;

public record DadosCadastroPacientes(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{6}")
        String cpf,
        DadosEndereco endereco) {
}
