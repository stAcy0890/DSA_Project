package com.dsa.Internet;

import com.dsa.interfaces.ServerMethods;

public class Server implements ServerMethods {
    @Override
    public void sendPacket() {

    }

    @Override
    public void receivePacket() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public int numOfConnectedServers() {
        return 0;
    }

    @Override
    public int numOfConnectedUsers() {
        return 0;
    }
}
