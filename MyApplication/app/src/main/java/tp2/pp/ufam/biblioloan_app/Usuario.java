package tp2.pp.ufam.biblioloan_app;

import java.io.Serializable;

/*
 * Created by caiotelles on 21/06/17.
 */

public class Usuario implements Serializable
{
    private String nomeDeUsuario, senha, anoDeNascimento, nomeCompleto;
    private int codigo, podeEmprestar;

    public int getPodeEmprestar() {
        return podeEmprestar;
    }

    public void setPodeEmprestar(int podeEmprestar) {
        this.podeEmprestar = podeEmprestar;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(String anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }
}
