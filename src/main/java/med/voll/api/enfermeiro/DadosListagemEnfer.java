package med.voll.api.enfermeiro;

public record DadosListagemEnfer(String nome, String email, String cre){
    public DadosListagemEnfer(Enfermeiro enfermeiro) {
        this(enfermeiro.getNome(), enfermeiro.getEmail(), enfermeiro.getCre());
    }
}
