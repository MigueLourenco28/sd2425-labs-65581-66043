package lab3.server.resources;

import java.util.List;
import java.util.logging.Logger;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import lab3.api.User;
import lab3.api.service.RestUsers;
import lab3.server.persistence.Hibernate;

public class UsersResource implements RestUsers {

	private static Logger Log = Logger.getLogger(UsersResource.class.getName());

	private Hibernate hibernate;
	
	public UsersResource() {
		hibernate = Hibernate.getInstance();
	}

	@Override
	public String createUser(User user) {
		Log.info("createUser : " + user);

		// Check if user data is valid
		if (user.getUserId() == null || user.getPassword() == null || user.getFullName() == null
				|| user.getEmail() == null) {
			Log.info("User object invalid.");
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		try {
			hibernate.persist(user);
		} catch (Exception e) {
			e.printStackTrace(); //Most likely the exception is due to the user already existing...
			Log.info("User already exists.");
			throw new WebApplicationException(Status.CONFLICT);
		}
		
		return user.getUserId();
	}

	@Override
	public User getUser(String userId, String password) {
		Log.info("getUser : user = " + userId + "; pwd = " + password);

		// Check if user is valid
		if (userId == null || password == null) {
			Log.info("UserId or password null.");
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		User user = null;
		try {
			user = hibernate.get(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

		// Check if user exists
		if (user == null) {
			Log.info("User does not exist.");
			throw new WebApplicationException(Status.NOT_FOUND);
		}

		// Check if the password is correct
		if (!user.getPassword().equals(password)) {
			Log.info("Password is incorrect.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}

		return user;
	}

	@Override
	public User updateUser(String userId, String password, User user) {
		Log.info("updateUser : user = " + userId + "; pwd = " + password + " ; userData = " + user);
		//---------------Added code------------------//
		if (userId == null || password == null || user == null) { // Check if userId, password or user is null
            Log.info("Invalid input.");
            throw new WebApplicationException(Status.BAD_REQUEST);
        }

        User existingUser = getUser(userId, password);
        
        if (user.getFullName() != null) {
            existingUser.setFullName(user.getFullName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        try {
            hibernate.update(existingUser); // Update the user in the database
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }

        return existingUser;
		//---------------End of added code------------------//
	}

	@Override
	public User deleteUser(String userId, String password) {
		Log.info("deleteUser : user = " + userId + "; pwd = " + password);
		//---------------Added code------------------//
		if (userId == null || password == null) {
            Log.info("Invalid input.");
            throw new WebApplicationException(Status.BAD_REQUEST);
        }

        User user = getUser(userId, password);

        try {
            hibernate.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }

        return user;
		//---------------End of added code------------------//
	}

	@Override
	public List<User> searchUsers(String pattern) {
		Log.info("searchUsers : pattern = " + pattern);
		
		try {
			List<User> list = hibernate.jpql("SELECT u FROM User u WHERE u.userId LIKE '%" + pattern +"%'", User.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

}
