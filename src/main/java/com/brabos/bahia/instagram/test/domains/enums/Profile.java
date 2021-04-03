package com.brabos.bahia.instagram.test.domains.enums;

public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private int code;
    private String description;

    Profile(int code, String description) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    public static Profile toEnum(Integer code){
        if(code == null){
            return null;
        }
        for(Profile x : Profile.values()){
            if( x.getCode() == code){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + code);
    }
}
