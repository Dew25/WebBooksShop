/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.session;

import entity.User;
import session.UserRolesFacade;

/**
 *
 * @author jvm
 */
public class UserRolesFacadeTest extends UserRolesFacade{
    @Override
    public boolean isRole(String roleName, User user) {
        try {
//            UserRoles userRoles = (UserRoles) super.getEntityManager().createQuery("SELECT userRoles FROM UserRoles userRoles WHERE userRoles.role.roleName = :roleName AND userRoles.user = :user")
//                    .setParameter("roleName", roleName)
//                    .setParameter("user", user)
//                    .getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public String getTopRoleForUser(User user) {
        return "ADMIN";
    }
    
}
