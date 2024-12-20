package biblioteca.bo;

import biblioteca.dao.ItemDAO;
import biblioteca.exceptions.*;
import biblioteca.model.Item;
import java.sql.SQLException;
import java.util.List;

public class ItemBO {
    private ItemDAO itemDAO;

    public ItemBO() {
        this.itemDAO = new ItemDAO();
    }

    // Método para adicionar um item com validação
    public void adicionarItem(Item item) throws SQLException, ItemDuplicadoException, ItemInvalidoException {
        if (item.getTitulo() == null || item.getTitulo().isEmpty()) {
            throw new ItemInvalidoException("Título do item não pode ser vazio.");
        }
        // Verifica se o item já existe
        if (itemDAO.verificarExistencia(item.getTitulo())) {
            throw new ItemDuplicadoException("Item com o título " + item.getTitulo() + " já existe.");
        }
        itemDAO.adicionarItem(item);
    }

    // Método para listar todos os itens
    public List<Item> listarItens() throws SQLException {
        return itemDAO.listarItens();
    }

    // Método para atualizar um item
    public void atualizarItem(Item item) throws SQLException, ItemNaoEncontradoException, ItemInvalidoException {
        if (item.getId() <= 0) {
            throw new ItemInvalidoException("ID do item inválido.");
        }
        if (!itemDAO.verificarExistencia(item.getId())) {
            throw new ItemNaoEncontradoException("Item com ID " + item.getId() + " não encontrado.");
        }
        itemDAO.atualizarItem(item);
    }

    // Método para deletar um item
    public void deletarItem(int id) throws SQLException, ItemNaoEncontradoException {
        if (!itemDAO.verificarExistencia(id)) {
            throw new ItemNaoEncontradoException("Item com ID " + id + " não encontrado.");
        }
        itemDAO.deletarItem(id);
    }
}