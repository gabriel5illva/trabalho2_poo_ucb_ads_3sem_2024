package lojacroche.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    // Configurações de conexão
    private static final String URL = "jdbc:mariadb://localhost:3306/lojadcroche";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Você pode lidar com a exceção de fechamento aqui
            }
        }
    }
}
