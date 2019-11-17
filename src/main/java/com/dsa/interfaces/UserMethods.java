package com.dsa.interfaces;

public interface UserMethods {

    void composeMessage(String message);

    void sendMessage();

    String readMessage();

    boolean isConnected();  // checks if a user is connected to at most 1 server
}
