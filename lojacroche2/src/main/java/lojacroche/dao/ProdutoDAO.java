package lojacroche.dao;

import lojacroche.model.Produto;
import lojacroche.util.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO() throws SQLException {
        connection = MySQLConnector.getConnection();
    }

    // Método para cadastrar um produto
    public void cadastrarProduto(Produto produto) throws SQLException {
        String query = "INSERT INTO produto (nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());

            stmt.executeUpdate();
        }
    }

    // Método para consultar todos os produtos
    public List<Produto> consultarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM produto";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));

                produtos.add(produto);
            }
        }

        return produtos;
    }

    // Método para consultar um produto por ID
    public Produto consultarProdutoPorId(int id) throws SQLException {
        String query = "SELECT * FROM produto WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setQuantidade(rs.getInt("quantidade"));

                    return produto;
                }
            }
        }

        return null; // Retorna null se o produto não for encontrado
    }

    // Método para atualizar um produto
    public void atualizarProduto(Produto produto) throws SQLException {
        String query = "UPDATE produto SET nome=?, descricao=?, preco=?, quantidade=? WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getId());

            stmt.executeUpdate();
        }
    }

    // Método para excluir um produto
    public void excluirProduto(int id) throws SQLException {
        String query = "DELETE FROM produto WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
