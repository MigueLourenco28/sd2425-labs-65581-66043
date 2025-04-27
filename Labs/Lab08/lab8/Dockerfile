# base ubuntu official image 
FROM ubuntu

# run a command (install a package)
RUN apt-get update && apt-get install iproute2 -y

# Copy openjdk 17 from another image
ENV JAVA_HOME=/opt/java/openjdk
COPY --from=eclipse-temurin:17 $JAVA_HOME $JAVA_HOME
ENV PATH=$PATH:$JAVA_HOME/bin

# working directory inside docker image
WORKDIR /home/sd

# copy hibernate config
COPY hibernate.cfg.xml hibernate.cfg.xml

# copy keystore and truststore
COPY *.ks /home/sd/

# copy the jar created by assembly to the docker image
COPY target/*jar-with-dependencies.jar sd2425.jar

# run Discovery when starting the docker image
CMD ["java", "-cp", "sd2425.jar", "-Djavax.net.ssl.keyStore=/home/sd/users-server.ks" ,
								"-Djavax.net.ssl.keyStorePassword=password" ,
								"-Djavax.net.ssl.trustStore=/home/sd/truststore.ks" ,
								"-Djavax.net.ssl.trustStorePassword=changeit" ,
								"lab8.impl.server.grpc.UsersServer"]






