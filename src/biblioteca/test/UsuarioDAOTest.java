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

public void testAdicionarUsuario() {
        try {
            Usuario usuario = new Usuario(0, "João Silva", "12345");
            usuarioDAO.adicionarUsuario(usuario);
            if (usuarioDAO.listarUsuarios().size() == 1) {
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
            usuarioDAO.adicionarUsuario(usuario);
            int id = usuarioDAO.listarUsuarios().get(0).getId();

            Usuario usuarioAtualizado = new Usuario(id, "Maria Silva", "67890");
            usuarioDAO.atualizarUsuario(usuarioAtualizado);
            
        if ("Maria Silva".equals(usuarioDAO.listarUsuarios().get(0).getNome())) {
                        System.out.println("testAtualizarUsuario: PASSOU");
                    } else {
                        System.out.println("testAtualizarUsuario: FALHOU");
                    }
                } catch (SQLException e) {
                    System.out.println("testAtualizarUsuario: FALHOU - Exceção: " + e.getMessage());
                }
            }

public void testDeletarUsuario() {
        try {
            Usuario usuario = new Usuario(0, "Carlos Oliveira", "54321");
            usuarioDAO.adicionarUsuario(usuario);
            int id = usuarioDAO.listarUsuarios().get(0).getId();

            usuarioDAO.deletarUsuario(id);

        if (usuarioDAO.listarUsuarios().size() == 0) {
                    System.out.println("testDeletarUsuario: PASSOU");
                } else {
                    System.out.println("testDeletarUsuario: FALHOU");
                }
            } catch (SQLException e) {
                System.out.println("testDeletarUsuario: FALHOU - Exceção: " + e.getMessage());
            }
        }
