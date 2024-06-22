package lojacroche.dao;

import lojacroche.model.Cliente;
import lojacroche.util.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() throws SQLException {
        connection = MySQLConnector.getConnection();
    }

    // Método para cadastrar um cliente
    public void cadastrarCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente (nome, email, telefone, endereco, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getCidade());
            stmt.setString(6, cliente.getEstado());
            stmt.setString(7, cliente.getCep());

            stmt.executeUpdate();
        }
    }

    // Método para consultar todos os clientes
    public List<Cliente> consultarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCep(rs.getString("cep"));

                clientes.add(cliente);
            }
        }

        return clientes;
    }

    // Método para consultar um cliente por ID
    public Cliente consultarClientePorId(int id) throws SQLException {
        String query = "SELECT * FROM cliente WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setCidade(rs.getString("cidade"));
                    cliente.setEstado(rs.getString("estado"));
                    cliente.setCep(rs.getString("cep"));

                    return cliente;
                }
            }
        }

        return null; // Retorna null se o cliente não for encontrado
    }

    // Método para atualizar um cliente
    public void atualizarCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE cliente SET nome=?, email=?, telefone=?, endereco=?, cidade=?, estado=?, cep=? WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getCidade());
            stmt.setString(6, cliente.getEstado());
            stmt.setString(7, cliente.getCep());
            stmt.setInt(8, cliente.getId());

            stmt.executeUpdate();
        }
    }

    // Método para excluir um cliente
    public void excluirCliente(int id) throws SQLException {
        String query = "DELETE FROM cliente WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
