package com.dsa.interfaces;

public interface GraphMethods<T> {

    void addSystem(T s);

    void removeSystem();

    void addConnection();

    void fastestPath();

    int getNumSystems();

    int getNumServers();

    int getNumUsers();

    String toString();

    //...the rest

}
