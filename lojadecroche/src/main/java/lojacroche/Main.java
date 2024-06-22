package lojacroche;

import java.util.Scanner;
import java.util.List;
import lojacroche.dao.ClienteDAO;
import lojacroche.model.Cliente;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();

        exibirMenu();
        System.out.print("Digite sua opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do Scanner

        switch (opcao) {
            case 1:
                cadastrarCliente(scanner, clienteDAO);
                break;
            case 2:
                consultarClientes(clienteDAO);
                break;
            case 3:
                atualizarCliente(scanner, clienteDAO);
                break;
            case 4:
                excluirCliente(scanner, clienteDAO);
                break;
            case 0:
                System.out.println("Saindo do sistema.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Consultar Clientes");
        System.out.println("3. Atualizar Cliente");
        System.out.println("4. Excluir Cliente");
        System.out.println("0. Sair do Sistema");
        System.out.println("=====================");
    }

    private static void cadastrarCliente(Scanner scanner, ClienteDAO clienteDAO) {
        System.out.println("=== Cadastro de Cliente ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        try {
            Cliente novoCliente = new Cliente();
            novoCliente.setNome(nome);
            novoCliente.setEmail(email);
            novoCliente.setTelefone(telefone);
            novoCliente.setEndereco(endereco);
            novoCliente.setCidade(cidade);
            novoCliente.setEstado(estado);
            novoCliente.setCep(cep);

            clienteDAO.cadastrarCliente(novoCliente);

            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private static void consultarClientes(ClienteDAO clienteDAO) {
        try {
            System.out.println("=== Consulta de Clientes ===");
            List<Cliente> clientes = clienteDAO.consultarClientes();

            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar clientes: " + e.getMessage());
        }
    }

    private static void atualizarCliente(Scanner scanner, ClienteDAO clienteDAO) {
        System.out.println("=== Atualização de Cliente ===");
        System.out.print("Digite o ID do cliente a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do Scanner

        try {
            Cliente cliente = clienteDAO.consultarClientePorId(id);

            if (cliente != null) {
                System.out.print("Novo Nome (deixe em branco para manter o mesmo): ");
                String nome = scanner.nextLine();
                if (!nome.isEmpty()) {
                    cliente.setNome(nome);
                }

                System.out.print("Novo Email (deixe em branco para manter o mesmo): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) {
                    cliente.setEmail(email);
                }

                System.out.print("Novo Telefone (deixe em branco para manter o mesmo): ");
                String telefone = scanner.nextLine();
                if (!telefone.isEmpty()) {
                    cliente.setTelefone(telefone);
                }

                System.out.print("Novo Endereço (deixe em branco para manter o mesmo): ");
                String endereco = scanner.nextLine();
                if (!endereco.isEmpty()) {
                    cliente.setEndereco(endereco);
                }

                System.out.print("Nova Cidade (deixe em branco para manter a mesma): ");
                String cidade = scanner.nextLine();
                if (!cidade.isEmpty()) {
                    cliente.setCidade(cidade);
                }

                System.out.print("Novo Estado (deixe em branco para manter o mesmo): ");
                String estado = scanner.nextLine();
                if (!estado.isEmpty()) {
                    cliente.setEstado(estado);
                }

                System.out.print("Novo CEP (deixe em branco para manter o mesmo): ");
                String cep = scanner.nextLine();
                if (!cep.isEmpty()) {
                    cliente.setCep(cep);
                }

                clienteDAO.atualizarCliente(cliente);
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    private static void excluirCliente(Scanner scanner, ClienteDAO clienteDAO) {
        System.out.println("=== Exclusão de Cliente ===");
        System.out.print("Digite o ID do cliente a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do Scanner

        try {
            Cliente cliente = clienteDAO.consultarClientePorId(id);

            if (cliente != null) {
                clienteDAO.excluirCliente(id);
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}
