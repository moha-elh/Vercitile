package com.example;
import com.example.ClientBuilder;


public class Main {
    public static void main(String[] args) {
        ClientBuilder cb = new ClientBuilder();
        System.out.println("Docker Client : \n "+ cb);
        System.out.println("Docker Client Command : \n "+ cb.getDockerClient().pingCmd().exec());

    }
}