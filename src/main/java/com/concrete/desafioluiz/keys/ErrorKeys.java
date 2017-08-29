package com.concrete.desafioluiz.keys;

public enum ErrorKeys {
	EMAIL_EXISTENTE("E-mail j� existente"),
	UNAUTHORIZED("N�o autorizado"),
	PASSWORD_ENCRYPT_ERROR("Ocorreu um erro ao criptografar a senha do usu�rio, tente cadastrar novamente"),
	INAVLID_CREDENTIALS("Usu�rio e/ou senha inv�lidos"),
	INVALID_SESSION("Sess�o inv�lida");
	
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
