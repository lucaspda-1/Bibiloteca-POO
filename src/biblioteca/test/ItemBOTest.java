package biblioteca.test;

import biblioteca.bo.ItemBO;
import biblioteca.model.Item;

import java.sql.SQLException;

public class ItemBOTest {
    private ItemBO itemBO;

    public ItemBOTest() {
        itemBO = new ItemBO();
    }

    public void setUp() {
        // Configuração inicial, se necessário
    }

    public void testAdicionarItem() {
        try {
            Item item = new Item(0, "Livro", "Java", "Autor", 1);
            itemBO.adicionarItem(item);
            if (itemBO.listarItens().size() == 1) {
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
            itemBO.adicionarItem(item);
            int id = itemBO.listarItens().get(0).getId();

            Item itemAtualizado = new Item(id, "Livro", "Python", "Outro Autor", 2);
            itemBO.atualizarItem(itemAtualizado);

            if ("Python".equals(itemBO.listarItens().get(0).getTitulo())) {
                System.out.println("testAtualizarItem: PASSOU");
            } else {
                System.out.println("testAtualizarItem: FALHOU");
            }
        } catch (SQLException e) {
            System.out.println("testAtualizarItem: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ItemBOTest test = new ItemBOTest();
        test.setUp();
        test.testAdicionarItem();
        test.testAtualizarItem();
        test.testDeletarItem();
    }
}
