package com.norbs.spider.common.enums;

/**
 * Clase enum que describe los tipos de URL que se pueden generar para descargar portadas
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
public enum EnumTipoURL {
    FNAC(1), 
    CEGAL(2),
    LSF(3),
    CUSPIDE(4),
    CLOUD_FRONT(5);
    
    private int value;

    private EnumTipoURL(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
