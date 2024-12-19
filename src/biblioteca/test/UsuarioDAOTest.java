package biblioteca.test;

import biblioteca.dao.UsuarioDAO;
import biblioteca.model.Usuario;

import java.sql.SQLException;

public class UsuarioDAOTest {
    private UsuarioDAO usuarioDAO;

    public UsuarioDAOTest() {
        usuarioDAO = new UsuarioDAO();
    }

    public void setUp() {
        // Configuração inicial, se necessário
    }
