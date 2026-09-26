package com.example;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

public class ClientBuilder {
    private static DockerClientConfig dockerClientConfig;
    private static DockerHttpClient dockerHttpClient;
    public static DockerClient dockerClient;

    public ClientBuilder(){
        dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        dockerHttpClient = new ApacheDockerHttpClient.Builder().
                dockerHost(dockerClientConfig.getDockerHost()).
                build();
        dockerClient = DockerClientImpl.getInstance(dockerClientConfig, dockerHttpClient);
    }
    public DockerClient getDockerClient(){
        return dockerClient;
    }

}
