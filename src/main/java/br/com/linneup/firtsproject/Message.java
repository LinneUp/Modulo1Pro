package br.com.linneup.firtsproject;

import io.swagger.v3.oas.models.security.SecurityScheme;

public class Message {
    private Integer id;
    private String aluno;

    private Integer idade;

    public Message(Integer id, String aluno, Integer idade) {
        this.id = id;
        this.aluno = aluno;
        this.idade = idade;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }
}
