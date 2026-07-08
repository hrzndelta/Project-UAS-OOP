package kasir.controller;

import kasir.dao.LoginDAO;

/**
 * Mediates between the login view and the login data access layer.
 */
public class LoginController {

    private final LoginDAO loginDAO = new LoginDAO();

    public boolean login(String username, String password) {
        return loginDAO.authenticate(username, password);
    }
}
