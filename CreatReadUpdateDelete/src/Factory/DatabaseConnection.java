package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	// URL do Banco de Dados
	private static final String URL = "jdbc:mysql://localhost:3306/db_users";

	// Usuário do Banco de Dados
	private static final String USER = "root";

	// Senha do Banco de Dados
	private static final String PASSWORD = "root";

	// Criando a conexão com o método "getConnection()"
	public static Connection getConnection() {

		// Fazendo testes na conexão com o Driver e MySql
		try {

			// Carregando o Driver manualmente
			Class.forName("com.mysql.cj.jdbc.Driver");

			// O Driver deverá retornar o parâmetro do método "getConnection()"
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}

		// Caso o Driver não seja carregador
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Não foi possível carregar o driver JDBC", e);
		}

		// Caso o SQL não seja conectado
		catch (SQLException e) {
			throw new RuntimeException("Não foi possível conectar ao Banco de Dados", e);
		}

	}

}
