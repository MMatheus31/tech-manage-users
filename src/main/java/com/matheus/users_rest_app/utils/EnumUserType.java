package com.matheus.users_rest_app.utils;

public enum EnumUserType {

    ADMIN("Administrador do sistema"),
    EDITOR("Usuário com permissão de edição"),
    VIEWER("Usuário com permissão apenas para visualização");

    private final String description;

    EnumUserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
