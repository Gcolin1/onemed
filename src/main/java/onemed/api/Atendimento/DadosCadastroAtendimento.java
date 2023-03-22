package onemed.api.Atendimento;

import onemed.api.medico.Especialidade;
import onemed.api.pacientes.Paciente;

public record DadosCadastroAtendimento(
        String data,
        String horario,
        Especialidade especialidade,
        String descricao,

        String paciente_id

        //Paciente paciente
) {
}
