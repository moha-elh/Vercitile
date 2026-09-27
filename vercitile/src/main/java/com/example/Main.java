package com.example;
import com.example.ClientBuilder;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallbackTemplate;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.AsyncDockerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClientBuilder cb = new ClientBuilder();
        String folderPath = "./src/main/java/com/example/";
        File dockerFile = new File(folderPath + "Dockerfile");
        if(dockerFile.exists()){
            try {
                System.out.println("File Name: " + dockerFile.getName() );
                // Convert File to Path and read all content at once

                String content = Files.readString(dockerFile.toPath());
                System.out.println(content);
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }

        }
        else{
            System.out.println("File Not Found");
        }
        //System.out.println("Docker Client Command : \n "+ cb.getDockerClient().listImagesCmd().exec());
        //Docker client
        DockerClient dockerClient = cb.getDockerClient();
        //Image Creation from docker file
        BuildImageResultCallback Command = dockerClient.buildImageCmd(dockerFile).start();
        String imageId = Command.awaitImageId();
        //Container creation
        CreateContainerResponse container = dockerClient.createContainerCmd(imageId).
                withExposedPorts(ExposedPort.tcp(80)).
                withHostConfig(HostConfig.newHostConfig()).
                withPortBindings(new PortBinding(Ports.Binding.bindPort(8080),ExposedPort.tcp(80))).
                exec();

        System.out.println("Created Container : \n" + container.getId());
        System.out.println("Running Container : \n" + dockerClient.startContainerCmd(container.getId()).exec());


        // Listing all Running containers
        System.out.println("Docker Containers List : \n "+ dockerClient.listContainersCmd().exec());



    }
}