package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Factory.DatabaseConnection;
import Model.User;

public class UserDAO {

	// CREATE: Método para criar um novo usuário no banco de dados
	public void createUser(User user) {
		String sql = "INSERT INTO tb_users (nome, email) VALUES (?, ?)"; // SQL para inserir dados

		try (Connection conn = DatabaseConnection.getConnection(); // Obtém conexão com o banco
				PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
			stmt.setString(1, user.getNome()); // Define o nome do usuário
			stmt.setString(2, user.getEmail()); // Define o email do usuário
			stmt.executeUpdate(); // Executa a inserção

			System.out.println("Usuário criado com sucesso!"); // Mensagem de sucesso
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir usuário", e); // Em caso de erro
		}
	}

	// READ: Método para recuperar todos os usuários do banco de dados
	public List<User> readUsers() {
		String sql = "SELECT * FROM tb_users"; // SQL para selecionar todos os usuários
		List<User> users = new ArrayList<>(); // Lista para armazenar os usuários

		try (Connection conn = DatabaseConnection.getConnection(); // Obtém conexão com o banco
				PreparedStatement stmt = conn.prepareStatement(sql); // Prepara a instrução SQL
				ResultSet rs = stmt.executeQuery()) { // Executa a consulta e obtém o resultado

			// Processa os resultados e cria objetos User
			while (rs.next()) {
				User user = new User(0, null, null); // Cria um novo objeto User
				user.setId(rs.getInt("id")); // Define o ID do usuário
				user.setNome(rs.getString("nome")); // Define o nome do usuário
				user.setEmail(rs.getString("email")); // Define o email do usuário
				users.add(user); // Adiciona o usuário à lista
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar usuários", e); // Em caso de erro
		}

		return users; // Retorna a lista de usuários
	}

	// UPDATE: Método para atualizar as informações de um usuário existente
	public void updateUser(User user) {
		String sql = "UPDATE tb_users SET nome = ?, email = ? WHERE id = ?"; // SQL para atualizar o usuário

		try (Connection conn = DatabaseConnection.getConnection(); // Obtém conexão com o banco
				PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
			stmt.setString(1, user.getNome()); // Define o novo nome do usuário
			stmt.setString(2, user.getEmail()); // Define o novo email do usuário
			stmt.setInt(3, user.getId()); // Define o ID do usuário que será atualizado
			stmt.executeUpdate(); // Executa a atualização

			System.out.println("Usuário atualizado com sucesso!"); // Mensagem de sucesso
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar usuário", e); // Em caso de erro
		}
	}

	// DELETE: Método para excluir um usuário com base no seu ID
	public void deleteUser(int id) {
		String sql = "DELETE FROM tb_users WHERE id = ?"; // SQL para deletar um usuário

		try (Connection conn = DatabaseConnection.getConnection(); // Obtém conexão com o banco
				PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
			stmt.setInt(1, id); // Define o ID do usuário a ser deletado
			stmt.executeUpdate(); // Executa a exclusão
			System.out.println("Usuário deletado com sucesso");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar usuário", e); // Em caso de erro
		}
	}
}
