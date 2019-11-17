package com.dsa.interfaces;

public interface ServerMethods {

    void sendPacket();

    void receivePacket();

    boolean isActive(); //whether or not the server is on

    int numOfConnectedServers(); // num of currently connected servers

    int numOfConnectedUsers();// num of currently connected users


}
