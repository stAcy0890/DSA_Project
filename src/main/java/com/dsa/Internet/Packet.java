package com.dsa.Internet;

import java.util.LinkedList;

public class Packet {
    private String message;
    private LinkedList<Host> path;
    private Host source;
    private Host destination;

    public Packet(String m) {
        message = m;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Host getSource() {
        return source;
    }

    public void setSource(Host source) {
        this.source = source;
    }

    public Host getDestination() {
        return destination;
    }

    public void setDestination(Host destination) {
        this.destination = destination;
    }

    public LinkedList<Host> getPath() {
        return path;
    }

    public void setPath(LinkedList<Host> path) {
        this.path = path;
    }


}
