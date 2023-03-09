package med.voll.api.pacientes;

public record DadosListagemPaciente(String nome, String cpf, String email) {
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
