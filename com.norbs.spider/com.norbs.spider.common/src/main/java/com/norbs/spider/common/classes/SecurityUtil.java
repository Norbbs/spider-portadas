package com.norbs.spider.common.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase general encargada de realizar operaciones relacionadas con la seguridad 
 * del sistema.
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
public class SecurityUtil {
    
    /**
     * Cifra un texto empleando el algomitmo de seguridad MD5.
     * @param texto cadena de texto a cifrar.
     * @return cadena de texto cifrada.
     */
    public static String cifrarMD5(String texto) {
        
        if (StringUtil.esNulaOVacia(texto)) {
            return null;
        }

        MessageDigest md;
        StringBuffer sb = new StringBuffer();

        try {

            md = MessageDigest.getInstance("MD5");
            md.update(texto.getBytes());

            byte byteData[] = md.digest();

            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
    
}
