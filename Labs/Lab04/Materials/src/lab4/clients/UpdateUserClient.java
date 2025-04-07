package lab4.clients;

import java.io.IOException;

import org.glassfish.jersey.client.ClientConfig;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lab4.api.User;
import lab4.api.rest.RestUsers;

public class UpdateUserClient {

	public static void main(String[] args) throws IOException {
		
		if( args.length != 6) {
			System.err.println( "Use: java " + CreateUserClient.class.getCanonicalName() + " url userId oldpwd fullName email password");
			return;
		}
		
		String serverUrl = args[0];
		String userId = args[1];
		String oldpwd = args[2];
		String fullName = args[3];
		String email = args[4];
		String password = args[5];
		
		User usr = new User( userId, fullName, email, password);
		
		System.out.println("/us request to server.");
		
		//----------------Added code------------------//
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(serverUrl).path(RestUsers.PATH).path(userId)
				.queryParam(RestUsers.PASSWORD, oldpwd);

		Response r = target.request()
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(usr, MediaType.APPLICATION_JSON));

		if (r.getStatus() == Status.OK.getStatusCode() && r.hasEntity()) {
			User returnedUser = r.readEntity(User.class);
			System.out.println("User successfully updated: " + returnedUser);
		} else {
			System.out.println("Error, HTTP error status: " + r.getStatus());
		}
		//---------------------------------------------//
	}
}
