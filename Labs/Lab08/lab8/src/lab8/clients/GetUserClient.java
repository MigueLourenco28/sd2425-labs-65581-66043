package lab8.clients;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import lab8.api.User;
import lab8.api.java.Result;
import lab8.clients.grpc.GrpcUsersClient;
import lab8.clients.java.UsersClient;
import lab8.clients.rest.RestUsersClient;

public class GetUserClient {
	
	private static Logger Log = Logger.getLogger(GetUserClient.class.getName());


	public static void main(String[] args) throws IOException {
		
		if( args.length != 3) {
			System.err.println( "Use: java " + CreateUserClient.class.getCanonicalName() + " url userId password");
			return;
		}
		
		String serverUrl = args[0];
		String userId = args[1];
		String password = args[2];
		
		UsersClient client = null;
		
		try {
			if (serverUrl.endsWith("rest"))
				client = new RestUsersClient(URI.create(serverUrl));
			else
				client = new GrpcUsersClient(URI.create(serverUrl));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
			
		Result<User> result = client.getUser(userId, password);
		if( result.isOK()  )
			Log.info("Get user:" + result.value() );
		else
			Log.info("Get user failed with error: " + result.error());
		
	}
	
}
