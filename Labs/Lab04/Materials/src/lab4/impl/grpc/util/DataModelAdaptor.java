package lab4.impl.grpc.util;

import lab4.api.User;
import lab4.impl.server.grpc.generated_java.UsersProtoBuf.GrpcUser;
import lab4.impl.server.grpc.generated_java.UsersProtoBuf.GrpcUser.Builder;

public class DataModelAdaptor {

	public static User GrpcUser_to_User( GrpcUser from )  {
		return new User( 
				from.getUserId(), 
				from.getFullName(),
				from.getEmail(), 
				from.getPassword(), 
				from.getAvatar());	
	}

	public static GrpcUser User_to_GrpcUser( User from )  {
		Builder b = GrpcUser.newBuilder()
				.setUserId( from.getUserId())
				.setPassword( from.getPassword())
				.setEmail( from.getEmail())
				.setFullName( from.getFullName());
		
		if(from.getAvatar() != null)
			b.setAvatar( from.getAvatar());
		
		return b.build();
	}

}
