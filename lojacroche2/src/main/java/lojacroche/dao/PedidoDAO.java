package lojacroche.dao;

import lojacroche.model.Pedido;
import lojacroche.util.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO() throws SQLException {
        connection = MySQLConnector.getConnection();
    }

    // Método para cadastrar um pedido
    public void cadastrarPedido(Pedido pedido) throws SQLException {
        String query = "INSERT INTO pedido (cliente_id, data_pedido, total) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pedido.getClienteId());
            stmt.setObject(2, pedido.getDataPedido());
            stmt.setDouble(3, pedido.getTotal());

            stmt.executeUpdate();
        }
    }

    // Método para consultar todos os pedidos
    public List<Pedido> consultarPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT * FROM pedido";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setClienteId(rs.getInt("cliente_id"));
                pedido.setDataPedido(rs.getObject("data_pedido", LocalDateTime.class));
                pedido.setTotal(rs.getDouble("total"));

                pedidos.add(pedido);
            }
        }

        return pedidos;
    }

    // Método para consultar um pedido por ID
    public Pedido consultarPedidoPorId(int id) throws SQLException {
        String query = "SELECT * FROM pedido WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getInt("id"));
                    pedido.setClienteId(rs.getInt("cliente_id"));
                    pedido.setDataPedido(rs.getObject("data_pedido", LocalDateTime.class));
                    pedido.setTotal(rs.getDouble("total"));

                    return pedido;
                }
            }
        }

        return null; // Retorna null se o pedido não for encontrado
    }

    // Método para atualizar um pedido
    public void atualizarPedido(Pedido pedido) throws SQLException {
        String query = "UPDATE pedido SET cliente_id=?, data_pedido=?, total=? WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pedido.getClienteId());
            stmt.setObject(2, pedido.getDataPedido());
            stmt.setDouble(3, pedido.getTotal());
            stmt.setInt(4, pedido.getId());

            stmt.executeUpdate();
        }
    }

    // Método para excluir um pedido
    public void excluirPedido(int id) throws SQLException {
        String query = "DELETE FROM pedido WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
