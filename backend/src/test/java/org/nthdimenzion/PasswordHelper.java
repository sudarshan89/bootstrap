package org.nthdimenzion;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 26/10/12
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class PasswordHelper {

    static ShaPasswordEncoder encoder = new ShaPasswordEncoder();
    public static void main(String a[]){
        System.out.println("Macula password " + encoder.encodePassword("activator","macula"));
        System.out.println(new Date(1394097480000l));

    }

}
