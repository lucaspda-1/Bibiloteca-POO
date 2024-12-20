package biblioteca.test;

import biblioteca.bo.ItemBO;
import biblioteca.model.Item;
import biblioteca.exceptions.*;

import java.sql.SQLException;

public class ItemBOTest {
    private ItemBO itemBO;

    public ItemBOTest() {
        itemBO = new ItemBO();
    }

    public void setUp() {
        // Configuração inicial, se necessário
    }

    public void tearDown() {
        // Limpa todos os itens após cada teste
        try {
            for (Item item : itemBO.listarItens()) {
                itemBO.deletarItem(item.getId());
            }
        } catch (SQLException | ItemNaoEncontradoException e) {
            System.out.println("Erro ao limpar os itens: " + e.getMessage());
        }
    }

    public void testAdicionarItem() {
        try {
            // Adiciona um item com título "Java"
            Item item = new Item(0, "Livro", "Java", "Autor", 1);
            itemBO.adicionarItem(item);
            if (itemBO.listarItens().size() == 1) {
                System.out.println("testAdicionarItem: PASSOU");
            } else {
                System.out.println("testAdicionarItem: FALHOU");
            }
        } catch (SQLException | ItemDuplicadoException | ItemInvalidoException e) {
            System.out.println("testAdicionarItem: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public void testAtualizarItem() throws ItemDuplicadoException {
        try {
            // Adiciona um item com título "Python"
            Item item = new Item(0, "Livro", "Python", "Outro Autor", 1);
            itemBO.adicionarItem(item);
            int id = itemBO.listarItens().get(0).getId();

            // Atualiza o item para um novo título
            Item itemAtualizado = new Item(id, "Livro", "Python Atualizado", "Outro Autor", 2);
            itemBO.atualizarItem(itemAtualizado);

            if ("Python Atualizado".equals(itemBO.listarItens().get(0).getTitulo())) {
                System.out.println("testAtualizarItem: PASSOU");
            } else {
                System.out.println("testAtualizarItem: FALHOU");
            }
        } catch (SQLException | ItemNaoEncontradoException | ItemInvalidoException e) {
            System.out.println("testAtualizarItem: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public void testDeletarItem() throws ItemDuplicadoException, ItemInvalidoException {
        try {
            // Adiciona um item com título "C#"
            Item item = new Item(0, "Livro", "C#", "Autor", 1);
            itemBO.adicionarItem(item);
            int id = itemBO.listarItens().get(0).getId();

            // Deleta o item
            itemBO.deletarItem(id);

            if (itemBO.listarItens().size() == 0) {
                System.out.println("testDeletarItem: PASSOU");
            } else {
                System.out.println("testDeletarItem: FALHOU");
            }
        } catch (SQLException | ItemNaoEncontradoException e) {
            System.out.println("testDeletarItem: FALHOU - Exceção: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws ItemDuplicadoException, ItemInvalidoException {
        ItemBOTest test = new ItemBOTest();
        test.setUp();

        // Executa o teste de adicionar item
        test.testAdicionarItem();
        test.tearDown(); // Limpa os dados após o teste

        // Executa o teste de atualizar item
        test.testAtualizarItem();
        test.tearDown(); // Limpa os dados após o teste

        // Executa o teste de deletar item
        test.testDeletarItem();
        test.tearDown(); // Limpa os dados após o teste
    }
}
