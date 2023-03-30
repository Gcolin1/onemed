package onemed.api.Atendimento;

public record DadosListagemAtendimento(Long id, String data, String horario, onemed.api.medico.Especialidade especialidade, String descricao) {

    public DadosListagemAtendimento(Atendimento atendimento){
        this(
                atendimento.getId(),
                atendimento.getData(),
                atendimento.getHorario(),
                atendimento.getEspecialidade(),
                atendimento.getDescricao()
        );
    }
}
