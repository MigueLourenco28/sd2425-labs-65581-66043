package lab4.api.java;

public interface Image {
	
	String NAME = "Image";
	
	/**
	 * Create an image
	 * 
	 * @param imageContent the bytes of the image in PNG format (in the body of the request)
	 * @return 	OK in the case of success returning the URI to access the image. 
	 * 		   	BAD_REQUEST if imageContents has a size of zero
	 */
	Result<String> createImage(byte[] imageContents);

	/**
	 * Gets the contents of an image associated with the imageId
	 * 
	 * @param imageId the identifier of the image
	 * @return 	OK the case of success returning the bytes of the image exists
	 *  		NOT_FOUND should be returned if the image does not exists
	 */
	Result<byte[]> getImage(String imageId);
	
	/**
	 * Deletes an image identified by imageId
	 * 
	 * @param imageId the identifier of the image
	 * @return 	OK in case of success. 
	 * 			NOT_FOUND if the image does not exists
	 */
	Result<Void> deleteImage(String imageId);

}
