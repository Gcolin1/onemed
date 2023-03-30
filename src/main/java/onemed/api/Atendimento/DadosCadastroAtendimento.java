package onemed.api.Atendimento;

import jakarta.validation.constraints.NotBlank;
import onemed.api.medico.Especialidade;

public record DadosCadastroAtendimento(
        @NotBlank
        String data,
        @NotBlank
        String horario,
        @NotBlank
        Especialidade especialidade,
        @NotBlank
        String descricao,
        @NotBlank
        String paciente
) {
}
