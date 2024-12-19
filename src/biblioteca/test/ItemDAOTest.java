package biblioteca.test;

import biblioteca.dao.ItemDAO;
import biblioteca.model.Item;

import java.sql.SQLException;

public class ItemDAOTest {
    private ItemDAO itemDAO;

    public ItemDAOTest() {
        itemDAO = new ItemDAO();
    }

    public void setUp() {
        // Configuração inicial, se necessário
    }

    public void testAdicionarItem() {
        try {
            Item item = new Item(0, "Livro", "Java", "Autor", 1);
            itemDAO.adicionarItem(item);
            if (itemDAO.listarItens().size() == 1) {
                System.out.println("testAdicionarItem: PASSOU");
            } else {
                System.out.println("testAdicionarItem: FALHOU");
            }
        } catch (SQLException e) {
            System.out.println("testAdicionarItem: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public void testAtualizarItem() {
        try {
            Item item = new Item(0, "Livro", "Java", "Autor", 1);
            itemDAO.adicionarItem(item);
            int id = itemDAO.listarItens().get(0).getId();

            Item itemAtualizado = new Item(id, "Livro", "Python", "Outro Autor", 2);
            itemDAO.atualizarItem(itemAtualizado);

            if ("Python".equals(itemDAO.listarItens().get(0).getTitulo())) {
                System.out.println("testAtualizarItem: PASSOU");
            } else {
                System.out.println("testAtualizarItem: FALHOU");
            }
        } catch (SQLException e) {
            System.out.println("testAtualizarItem: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public void testDeletarItem() {
        try {
            Item item = new Item(0, "Livro", "Java", "Autor", 1);
            itemDAO.adicionarItem(item);
            int id = itemDAO.listarItens().get(0).getId();

            itemDAO.deletarItem(id);

            if (itemDAO.listarItens().size() == 0) {
                System.out.println("testDeletarItem: PASSOU");
            } else {
                System.out.println("testDeletarItem: FALHOU");
            }
        } catch (SQLException e) {
            System.out.println("testDeletarItem: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ItemDAOTest test = new ItemDAOTest();
        test.setUp();
        test.testAdicionarItem();
        test.testAtualizarItem();
        test.testDeletarItem();
    }
}
