package med.voll.api.enfermeiro;

public record DadosListaEnfermeiro(String nome, String email, String cre, String cpf) {
    public DadosListaEnfermeiro(Enfermeiro enfermeiros){
        this(enfermeiros.getNome(), enfermeiros.getEmail(), enfermeiros.getCre(), enfermeiros.getCpf());
    }
}
