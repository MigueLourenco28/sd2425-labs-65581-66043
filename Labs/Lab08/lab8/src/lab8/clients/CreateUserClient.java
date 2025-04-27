package lab8.clients;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import lab8.api.User;
import lab8.api.java.Result;
import lab8.clients.grpc.GrpcUsersClient;
import lab8.clients.java.UsersClient;
import lab8.clients.rest.RestUsersClient;

public class CreateUserClient {

	private static Logger Log = Logger.getLogger(CreateUserClient.class.getName());

	public static void main(String[] args) throws IOException {

		if (args.length != 5) {
			System.err.println(
					"Use: java " + CreateUserClient.class.getCanonicalName() + " url userId fullName email password");
			return;
		}

		String serverUrl = args[0];
		String userId = args[1];
		String fullName = args[2];
		String email = args[3];
		String password = args[4];

		User usr = new User(userId, fullName, email, password);

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

		Result<String> result = client.createUser(usr);
		if (result.isOK())
			Log.info("Created user:" + result.value());
		else
			Log.info("Create user failed with error: " + result.error());

	}

}
