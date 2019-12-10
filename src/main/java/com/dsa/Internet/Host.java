package com.dsa.Internet;

import java.util.Stack;

public class Host {
    private Packet receiver;                // used by the move(...) method of the Model class

    private String name;
    private Type type;
    private Stack<Packet> unreadInbox;      // holds all unread "emails"
    private Stack<Packet> readInbox;        // holds all read "emails"
    private int d;                          // represents the distance from the start vertex for Dijktra's algorithm
    private Host parent;                    // represents the previous vertex of a vertex for Dijktra's algorithm

    public Host(String n, Type t) {
        name = n;
        type = t;
        d = 10000;
        parent = null;
        unreadInbox = new Stack<Packet>();
        readInbox = new Stack<Packet>();
    }

    /*
     * This method reads the last message received by the user
     * stored in the unreadInbox of the host.
     * Once an "email" is read, it is popped to the unread stack.
     *
     * Parameter(s): None
     * Return value: None
     */
    public void openInbox() {
        if (!(unreadInbox.isEmpty())) {
            Packet p = unreadInbox.pop();
            readInbox.push(p);
            System.out.println(p.getMessage());
        } else {
            System.out.println("User inbox is empty.");
        }
    }

    /*
     * The updateInbox(...) method adds a packet to the unreadInbox
     * of the host it is called on.
     *
     * Parameter(s): The packet to be added
     * Return value: None
     */
    public void updateInbox(Packet p) {
        unreadInbox.push(p);
    }

    enum Type {SERVER, USER}

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

    public Host getParent() {
        return parent;
    }

    public void setParent(Host p) {
        parent = p;
    }

    public int getD() {
        return d;
    }

    public int setD(int distance) {
        return d = distance;
    }

    @Override
    public String toString() {
        return getName();
    }

}
