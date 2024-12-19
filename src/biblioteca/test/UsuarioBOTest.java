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

public void testAdicionarUsuario() {
    try {
        Usuario usuario = new Usuario(0, "João Silva", "12345");
        usuarioBO.adicionarUsuario(usuario);
        if (usuarioBO.listarUsuarios().size() == 1) {
            System.out.println("testAdicionarUsuario: PASSOU");
        } else {
            System.out.println("testAdicionarUsuario: FALHOU");
        }
    } catch (SQLException e) {
        System.out.println("testAdicionarUsuario: FALHOU - Exceção: " + e.getMessage());
    }
}
