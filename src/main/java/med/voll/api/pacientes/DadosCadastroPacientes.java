package med.voll.api.pacientes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.medico.Especialidade;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPacientes(
        @NotBlank(message = "nome não pode ser nulo")
        String nome,
        @NotBlank(message = "email não pode ser nulo")
        @Email(message = "email incorreto")
        String email,
        @NotBlank(message = "cpf não pode ser nulo")
        @Pattern(regexp = "\\d{11}")
        String cpf,
        DadosEndereco endereco) {
}
