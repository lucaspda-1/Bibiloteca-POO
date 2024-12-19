package biblioteca.view;

import biblioteca.bo.ItemBO;
import biblioteca.bo.UsuarioBO;
import biblioteca.exceptions.UsuarioNaoEncontradoException;
import biblioteca.model.Item;
import biblioteca.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItemBO itemBO = new ItemBO();
        UsuarioBO usuarioBO = new UsuarioBO();
