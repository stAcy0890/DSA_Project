package com.dsa.Internet;

import java.util.Stack;

public class Host {
    public void updateInbox(Packet p) {
        unreadInbox.push(p);
    }

    private String name;
    private Type type;
    private Packet receiver;
    private Stack<Packet> unreadInbox;
    private Stack<Packet> readInbox;
    private boolean isActive;

    public Host(String n, Type t) {
        name = n;
        type = t;
        isActive = true;
        unreadInbox = new Stack<Packet>();
        readInbox = new Stack<Packet>();
    }

    public int numOfConnectedUsers() {  //TODO: Haven't done this yet
        return 0;
    }

    public void openInbox() {
        if (!(unreadInbox.isEmpty())) {
            Packet p = unreadInbox.pop();
            readInbox.push(p);
            System.out.println(p.getMessage());
        } else {
            System.out.println("User inbox is empty.");
        }
    }

    public void setActivate(boolean b) {
        isActive = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public Type getType() {
        return type;
    }

    public Packet getReceiver() {
        return receiver;
    }

    public void setReceiver(Packet receiver) {
        this.receiver = receiver;
    }

    public boolean isActive() {
        return isActive;
    }

    enum Type {SERVER, USER}

    @Override
    public String toString() {
        return getName();
    }

}
