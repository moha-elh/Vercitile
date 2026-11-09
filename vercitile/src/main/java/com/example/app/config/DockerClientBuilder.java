package com.example.app.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.stereotype.Component;

@Component
public class DockerClientBuilder {
    private final DockerClientConfig dockerClientConfig;
    private final DockerHttpClient dockerHttpClient;
    public final DockerClient dockerClient;

    public DockerClientBuilder(DockerClientConfig clientConfig,
                               DockerHttpClient httpClient,
                               DockerClient client){

        this.dockerClientConfig = clientConfig;
        this.dockerHttpClient = httpClient;
        this.dockerClient = client;
    }


}
