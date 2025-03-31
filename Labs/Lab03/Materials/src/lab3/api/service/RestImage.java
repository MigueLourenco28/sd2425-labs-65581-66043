package lab3.api.service;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path(RestImage.PATH)
public interface RestImage {

	public static final String PATH = "/image";
	public static final String IMAGE_ID = "/id";
	
	/**
	 * Create an image
	 * 
	 * @param imageContent the bytes of the image in PNG format (in the body of the request)
	 * @return 200 in the case of success returning the URI to access the image. 
	 * 		   400 if imageContents has a size of zero
	 */
	@POST
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	String createImage(byte[] imageContents);

	/**
	 * Gets the contents of an image associated with the imageId
	 * 
	 * @param imageId the identifier of the image
	 * @return 200 the case of success returning the bytes of the image exists
	 *  404 should be returned if the image does not exists
	 */
	@GET
	@Path("{" + IMAGE_ID + "}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	byte[] getImage(@PathParam(IMAGE_ID) String imageId);
	
	/**
	 * Deletes an image identified by imageId
	 * 
	 * @param imageId the identifier of the image
	 * @return 204 in the case of success. 404 if the image does not exists
	 */
	@DELETE
	@Path("{" + IMAGE_ID + "}/")
	void deleteImage(@PathParam(IMAGE_ID) String imageId);
	
	
	
}