package biblioteca.view;

import biblioteca.bo.ItemBO;
import biblioteca.bo.UsuarioBO;
import biblioteca.exceptions.*;
import biblioteca.model.Item;
import biblioteca.model.Usuario;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItemBO itemBO = new ItemBO();
        UsuarioBO usuarioBO = new UsuarioBO();

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Adicionar Item");
            System.out.println("2. Listar Itens");
            System.out.println("3. Atualizar Item");
            System.out.println("4. Deletar Item");
            System.out.println("5. Adicionar Usuário");
            System.out.println("6. Listar Usuários");
            System.out.println("7. Atualizar Usuário");
            System.out.println("8. Deletar Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        System.out.print("Tipo: ");
                        String tipo = scanner.nextLine();
                        if (tipo.trim().isEmpty()) {
                            System.out.println("Erro: O tipo não pode ser vazio.");
                            break;
                        }

                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        if (titulo.trim().isEmpty()) {
                            System.out.println("Erro: O título não pode ser vazio.");
                            break;
                        }

                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        if (autor.trim().isEmpty()) {
                            System.out.println("Erro: O autor não pode ser vazio.");
                            break;
                        }

                        System.out.print("Edição: ");
                        int edicao = 0;
                        try {
                            edicao = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: A edição deve ser um número inteiro.");
                            scanner.nextLine(); // Limpar o buffer
                            break;
                        }

                        itemBO.adicionarItem(new Item(0, tipo, titulo, autor, edicao));
                        break;

                    case 2:
                        List<Item> itens = itemBO.listarItens();
                        if (itens.isEmpty()) {
                            System.out.println("Nenhum item encontrado.");
                        } else {
                            itens.forEach(item -> System.out.println(item.getId() + " - " + item.getTitulo()));
                        }
                        break;

                    case 3:
                        System.out.print("ID do Item: ");
                        int idItem = 0;
                        try {
                            idItem = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: O ID deve ser um número inteiro.");
                            scanner.nextLine(); // Limpar o buffer
                            break;
                        }

                        System.out.print("Novo Título: ");
                        String novoTitulo = scanner.nextLine();
                        if (novoTitulo.trim().isEmpty()) {
                            System.out.println("Erro: O título não pode ser vazio.");
                            break;
                        }

                        itemBO.atualizarItem(new Item(idItem, null, novoTitulo, null, 0));
                        break;

                    case 4:
                        System.out.print("ID do Item: ");
                        int idDelete = 0;
                        try {
                            idDelete = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: O ID deve ser um número inteiro.");
                            scanner.nextLine(); // Limpar o buffer
                            break;
                        }

                        itemBO.deletarItem(idDelete);
                        break;

                    case 5:
                        System.out.print("Nome do Usuário: ");
                        String nome = scanner.nextLine();
                        if (nome.trim().isEmpty()) {
                            System.out.println("Erro: O nome não pode ser vazio.");
                            break;
                        }

                        System.out.print("Matrícula: ");
                        String matricula = scanner.nextLine();
                        if (matricula.trim().isEmpty()) {
                            System.out.println("Erro: A matrícula não pode ser vazia.");
                            break;
                        }

                        usuarioBO.adicionarUsuario(new Usuario(0, nome, matricula));
                        break;

                    case 6:
                        List<Usuario> usuarios = usuarioBO.listarUsuarios();
                        if (usuarios.isEmpty()) {
                            System.out.println("Nenhum usuário encontrado.");
                        } else {
                            usuarios.forEach(usuario -> System.out.println(usuario.getId() + " - " + usuario.getNome()));
                        }
                        break;

                    case 7:
                        System.out.print("ID do Usuário: ");
                        int idUsuario = 0;
                        try {
                            idUsuario = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: O ID deve ser um número inteiro.");
                            scanner.nextLine(); // Limpar o buffer
                            break;
                        }

                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.nextLine();
                        if (novoNome.trim().isEmpty()) {
                            System.out.println("Erro: O nome não pode ser vazio.");
                            break;
                        }

                        usuarioBO.atualizarUsuario(new Usuario(idUsuario, novoNome, null));
                        break;

                    case 8:
                        System.out.print("ID do Usuário: ");
                        int idUsuarioDelete = 0;
                        try {
                            idUsuarioDelete = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: O ID deve ser um número inteiro.");
                            scanner.nextLine(); // Limpar o buffer
                            break;
                        }

                        usuarioBO.deletarUsuario(idUsuarioDelete);
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        scanner.close(); // Fechar o Scanner ao sair
                        return;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SQLException | UsuarioNaoEncontradoException | UsuarioExistenteException | UsuarioInvalidoException | UsuarioSemPermissaoException |
                     ItemDuplicadoException | ItemInvalidoException | ItemNaoEncontradoException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }
}
