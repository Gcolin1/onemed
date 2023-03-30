package onemed.api.pacientes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import onemed.api.endereco.DadosEndereco;

public record DadosCadastroPacientes(
        @NotBlank(message = "nome não pode ser nulo")
        String nome,
        @NotBlank
        @Email(message = "email incorreto")
        String email,
        @NotBlank()
        @Pattern(regexp = "\\d{11}")
        String cpf,
        DadosEndereco endereco) {
}
