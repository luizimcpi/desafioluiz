package com.concrete.desafioluiz.keys;

public enum ErrorKeys {
	EMAIL_EXISTENTE("E-mail já existente"),
	UNAUTHORIZED("Não autorizado"),
	PASSWORD_ENCRYPT_ERROR("Ocorreu um erro ao criptografar a senha do usuário, tente cadastrar novamente"),
	INAVLID_CREDENTIALS("Usuário e/ou senha inválidos"),
	INVALID_SESSION("Sessão inválida");
	
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
