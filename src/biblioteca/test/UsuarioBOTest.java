package biblioteca.test;

import biblioteca.bo.UsuarioBO;
import biblioteca.exceptions.UsuarioNaoEncontradoException;
import biblioteca.model.Usuario;

import java.sql.SQLException;

public class UsuarioBOTest {
    private UsuarioBO usuarioBO;

    public UsuarioBOTest() {
        usuarioBO = new UsuarioBO();
    }

    public void setUp() {
        // Configuração inicial, se necessário
    }
}
