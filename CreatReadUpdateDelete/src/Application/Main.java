package Application;

import java.util.List;

import DAO.UserDAO;
import Model.User;

public class Main {
	
	public static void main(String[] args) {
		
		UserDAO userDAO = new UserDAO();
		
		List<User> lista = userDAO.readUsers();
		
		// CREATE
		/*User newUser = new User();
		
		newUser.setNome("Gustavo");
		newUser.setEmail("gustavoarr2022@gmail.com");
		
		userDAO.createUser(newUser);*/
		
		// READ
		lista.forEach(
				user -> System.out.println(
						"ID: " + user.getId() +
						"\nNome: " + user.getNome() +
						"\nE-mail: " + user.getEmail()+
						"\n----------------------------"
						)
				);
		
		// UPDATE
		/*User existingUser = lista.get(0);
		
		existingUser.setNome("Gustavo");
		existingUser.setEmail("garrudam010@gmail.com");
		
		userDAO.updateUser(existingUser);*/
		
		// DELETE
		/*userDAO.deleteUser(1);*/
	}

}
