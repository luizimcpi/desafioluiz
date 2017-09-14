package com.company.desafioluiz.keys;

public enum ErrorKeys {
	EMAIL_EXISTENTE("E-mail ja existente"),
	UNAUTHORIZED("Nao autorizado"),
	PASSWORD_ENCRYPT_ERROR("Ocorreu um erro ao criptografar a senha do usuario, tente cadastrar novamente"),
	INAVLID_CREDENTIALS("Usuario e/ou senha invalidos"),
	INVALID_SESSION("Sessao invalida");
	
	private final String name;       

    private ErrorKeys(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
