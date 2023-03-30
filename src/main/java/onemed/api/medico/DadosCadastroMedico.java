package onemed.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import onemed.api.endereco.DadosEndereco;

public record DadosCadastroMedico(

        @NotBlank(message = "nome incorreto")
        String nome,
        @NotBlank(message = "email incorreto")
        @Email
        String email,

        @NotBlank(message = "telefone incorreto")
        String telefone,

        @NotBlank(message = "crm incorreto")
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull(message = "especialidade incorreta")
        Especialidade especialidade,
        DadosEndereco endereco) {

}
