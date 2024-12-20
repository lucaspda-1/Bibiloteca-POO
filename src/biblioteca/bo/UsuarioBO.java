package biblioteca.bo;

import biblioteca.dao.UsuarioDAO;
import biblioteca.exceptions.UsuarioExistenteException;
import biblioteca.exceptions.UsuarioInvalidoException;
import biblioteca.exceptions.UsuarioNaoEncontradoException;
import biblioteca.exceptions.UsuarioSemPermissaoException;
import biblioteca.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Método para adicionar um usuário
    public void adicionarUsuario(Usuario usuario) throws SQLException, UsuarioExistenteException, UsuarioInvalidoException {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new UsuarioInvalidoException("Nome do usuário não pode ser vazio.");
        }
        if (usuario.getMatricula() == null || usuario.getMatricula().trim().isEmpty()) {
            throw new UsuarioInvalidoException("Matrícula do usuário não pode ser vazia.");
        }

        // Verifica se o usuário já existe
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        for (Usuario u : usuarios) {
            if (u.getMatricula().equals(usuario.getMatricula())) {
                throw new UsuarioExistenteException("Usuário com a mesma matrícula já existe.");
            }
        }

        usuarioDAO.adicionarUsuario(usuario);
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioDAO.listarUsuarios();
    }

    // Método para atualizar um usuário
    public void atualizarUsuario(Usuario usuario) throws SQLException, UsuarioNaoEncontradoException, UsuarioInvalidoException {
        if (usuario.getId() <= 0) {
            throw new UsuarioNaoEncontradoException("ID do usuário inválido para atualização.");
        }
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new UsuarioInvalidoException("Nome do usuário não pode ser vazio.");
        }

        usuarioDAO.atualizarUsuario(usuario);
    }

    // Método para deletar um usuário
    public void deletarUsuario(int id) throws SQLException, UsuarioNaoEncontradoException, UsuarioSemPermissaoException {
        if (id <= 0) {
            throw new UsuarioNaoEncontradoException("ID do usuário inválido para exclusão.");
        }

        // Verifica se o usuário tem permissão para deletar (exemplo fictício)
        if (!temPermissaoParaDeletar(id)) {
            throw new UsuarioSemPermissaoException("Usuário não tem permissão para deletar.");
        }

        usuarioDAO.deletarUsuario(id);
    }

    // Método fictício para verificar permissão
    private boolean temPermissaoParaDeletar(int id) {
        // Lógica fictícia para verificar permissão
        return id != 1; // Apenas um exemplo
    }
}
