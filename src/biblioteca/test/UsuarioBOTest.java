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

    public void testAtualizarUsuario() {
        try {
            Usuario usuario = new Usuario(0, "Maria Souza", "67890");
            usuarioBO.adicionarUsuario(usuario);
            int id = usuarioBO.listarUsuarios().get(0).getId();

            Usuario usuarioAtualizado = new Usuario(id, "Maria Silva", "67890");
            usuarioBO.atualizarUsuario(usuarioAtualizado);

            if ("Maria Silva".equals(usuarioBO.listarUsuarios().get(0).getNome())) {
                System.out.println("testAtualizarUsuario: PASSOU");
            } else {
                System.out.println("testAtualizarUsuario: FALHOU");
            }
        } catch (SQLException | UsuarioNaoEncontradoException e) {
            System.out.println("testAtualizarUsuario: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public void testDeletarUsuario() {
        try {
            Usuario usuario = new Usuario(0, "Carlos Oliveira", "54321");
            usuarioBO.adicionarUsuario(usuario);
            int id = usuarioBO.listarUsuarios().get(0).getId();

            usuarioBO.deletarUsuario(id);

            if (usuarioBO.listarUsuarios().size() == 0) {
                System.out.println("testDeletarUsuario: PASSOU");
            } else {
                System.out.println("testDeletarUsuario: FALHOU");
            }
        } catch (SQLException | UsuarioNaoEncontradoException e) {
            System.out.println("testDeletarUsuario: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public void testAtualizarUsuarioInvalido() {
        try {
            Usuario usuario = new Usuario(-1, "Teste", "11111");
            usuarioBO.atualizarUsuario(usuario);
            System.out.println("testAtualizarUsuarioInvalido: FALHOU - Não lançou exceção");
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println("testAtualizarUsuarioInvalido: PASSOU - Exceção lançada");
        } catch (SQLException e) {
            System.out.println("testAtualizarUsuarioInvalido: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public void testDeletarUsuarioInvalido() {
        try {
            usuarioBO.deletarUsuario(-1);
            System.out.println("testDeletarUsuarioInvalido: FALHOU - Não lançou exceção");
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println("testDeletarUsuarioInvalido: PASSOU - Exceção lançada");
        } catch (SQLException e) {
            System.out.println("testDeletarUsuarioInvalido: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        UsuarioBOTest test = new UsuarioBOTest();
        test.setUp();
        test.testAdicionarUsuario();
        test.testAtualizarUsuario();
        test.testDeletarUsuario();
        test.testAtualizarUsuarioInvalido();
        test.testDeletarUsuarioInvalido();
    }
}
