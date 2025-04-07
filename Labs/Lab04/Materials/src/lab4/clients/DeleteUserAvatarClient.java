package lab4.clients;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.glassfish.jersey.client.ClientConfig;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lab4.api.rest.RestUsers;

public class DeleteUserAvatarClient {

	public static void main(String[] args) throws IOException {
		
		if( args.length != 3) {
			System.err.println( "Use: java " + CreateUserClient.class.getCanonicalName() + " url userId password");
			return;
		}
		
		String serverUrl = args[0];
		String userId = args[1];
		String password = args[2];

		System.out.println("Sending request to server.");
		
		//----------------Added code------------------//
		ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target( serverUrl ).path( RestUsers.PATH );


        Response r = target.path( userId ).path( RestUsers.AVATAR )
                .queryParam(RestUsers.PASSWORD, password).request()
                .delete();


        if( r.getStatus() == Status.NO_CONTENT.getStatusCode() )
            System.out.println("User Avatar deleted.");
        else
            System.out.println("Error, HTTP error status: " + r.getStatus() );
		//---------------------------------------------//
	}
	
}
