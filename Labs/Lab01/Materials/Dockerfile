# base image - an image with openjdk 17
FROM openjdk:17

# working directory inside docker image
WORKDIR /home/sd

# copy the jar created by assembly to the docker image
COPY target/sd2425-lab1-1.0.jar sd.jar
COPY exec.sh exec.sh

# run Discovery when starting the docker image
CMD ["java", "-cp", "/home/sd/sd.jar", "sd.lab1.Discovery"]