package lab2.api.service;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import lab2.api.User;

@Path(RestUsers.PATH)
public interface RestUsers {

	public static final String PATH = "/users";
	public static final String QUERY = "query";
	public static final String USER_ID = "userId";
	public static final String PASSWORD = "password";
	public static final String AVATAR = "avatar";

	/**
	 * Creates a new user.
	 * 
	 * @param user User to be created (in the body of the request)
	 * @return 200 and the userId. 409 if the userId already exists. 400 otherwise.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String createUser(User user);

	/**
	 * Obtains the information on the user identified by name.
	 * 
	 * @param userId   the userId of the user
	 * @param password password of the user
	 * @return 200 and the user object, if the userId exists and password matches the
	 *         existing password; 403 if the password is incorrect; 404 if no user
	 *         exists with the provided userId
	 */
	@GET
	@Path("/{" + USER_ID + "}")
	@Produces(MediaType.APPLICATION_JSON)
	User getUser(@PathParam(USER_ID) String userId, @QueryParam(PASSWORD) String password);

	/**
	 * Modifies the information of a user. Values of null in any field of the user
	 * will be considered as if the the fields is not to be modified (the id cannot
	 * be modified).
	 * 
	 * @param userId   the userId of the user
	 * @param password password of the user
	 * @param user     Updated information (in the body of the request)
	 * @return 200 the updated user object, if the name exists and password matches
	 *         the existing password 403 if the password is incorrect 404 if no user
	 *         exists with the provided userId 400 otherwise.
	 */
	@PUT
	@Path("/{" + USER_ID + "}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	User updateUser(@PathParam(USER_ID) String userId, @QueryParam(PASSWORD) String password, User user);

	/**
	 * Deletes the user identified by userId. The spreadsheets owned by the user
	 * should be eventually removed (asynchronous deletion is ok).
	 * 
	 * @param nauserId the userId of the user
	 * @param password password of the user
	 * @return 200 the deleted user object, if the name exists and pwd matches the
	 *         existing password 403 if the password is incorrect 404 if no user
	 *         exists with the provided userId
	 */
	@DELETE
	@Path("/{" + USER_ID + "}")
	@Produces(MediaType.APPLICATION_JSON)
	User deleteUser(@PathParam(USER_ID) String userId, @QueryParam(PASSWORD) String password);

	/**
	 * Returns the list of users for which the pattern is a substring of the name
	 * (of the user), case-insensitive. The password of the users returned by the
	 * query must be set to the empty string "".
	 * 
	 * @param pattern substring to search
	 * @return 200 when the search was successful, regardless of the number of hits
	 *         (including 0 hits). 400 otherwise.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<User> searchUsers(@QueryParam(QUERY) String pattern);
	
	/**
	 * Associate an Avatar image to a user profile
	 * 
	 * @param userId the identifier of the user
	 * @param avatar the bytes of the image in PNG format (in the body of the request)
	 * @return 204 in the case of success. 404 if the user does not exists, 403 
	 * if password incorrect, 400 if avatar has a size of zero
	 */
	@PUT
	@Path("{" + USER_ID + "}/" + AVATAR)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	void associateAvatar(@PathParam(USER_ID) String userId, @QueryParam(PASSWORD) String password, byte[] avatar);

	
	/**
	 * Deletes an Avatar image associated to the current user profile
	 * 
	 * @param userId the identifier of the user
	 * @return 204 in the case of success. 404 if the user or avatar does not exists, 403 
	 * if password incorrect
	 */
	@DELETE
	@Path("{" + USER_ID + "}/" + AVATAR)
	void removeAvatar(@PathParam(USER_ID) String userId, @QueryParam(PASSWORD) String password);
	
	/**
	 * Gets an Avatar image associated to the current user profile
	 * 
	 * @param userId the identifier of the user
	 * @return 200 the case of success returning the bytes of the user image (if one is associated) 
	 * or the default otherwise. 404 should be returned if the user does not exists
	 */
	@GET
	@Path("{" + USER_ID + "}/" + AVATAR)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	byte[] getAvatar(@PathParam(USER_ID) String userId);
	
}