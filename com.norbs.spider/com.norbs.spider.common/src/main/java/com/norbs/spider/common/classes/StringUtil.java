package com.norbs.spider.common.classes;

/**
 * Clase generica en la que se definen metodos para trabajar objetos tipo String
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
public class StringUtil {

    /**
     * Verifica si una cadena es nula o vacia
     * @param cadena texto a verificar
     * @return true si la cadena envia es nula o vacia y false en caso contrario.
     */
    public static boolean esNulaOVacia(String cadena) {
        return cadena == null || cadena.isEmpty();
    }
}