/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author jvm
 */
public interface EncryptPassword {
    public String createSalt();
    public String createHash(String password, String salt);
}
