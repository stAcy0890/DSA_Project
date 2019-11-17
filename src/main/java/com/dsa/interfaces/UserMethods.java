package com.dsa.interfaces;

public interface UserMethods {

    void composeMessage();

    void sendMessage();

    String readMessage();

    boolean isConnected();  // checks if a user is connected to at most 1 server
}
