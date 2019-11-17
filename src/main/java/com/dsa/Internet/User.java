package com.dsa.Internet;

import com.dsa.interfaces.UserMethods;

public class User implements UserMethods {
    @Override
    public void composeMessage(String message) {

    }

    @Override
    public void sendMessage() {

    }

    @Override
    public String readMessage() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
