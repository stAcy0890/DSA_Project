package com.dsa.Internet;

import java.util.*;

public class Model {
    private Map<Host, List<Node>> network = new HashMap<>();
    private Map<String, Host> members = new HashMap<>();
    private int numHosts;
    private int numServers;
    private int numUsers;

    public static void main(String[] args) {
        Model network = new Model();

        Host server1 = new Host("A", Host.Type.SERVER);
        Host server2 = new Host("B", Host.Type.SERVER);
        Host user1 = new Host("Bob", Host.Type.USER);
        Host user2 = new Host("Dan", Host.Type.USER);

        network.addHost(server1);
        network.addHost(server2);
        network.addHost(user1);
        network.addHost(user2);

        System.out.println(network);

        network.addConnection(server1, user1, 8);          // server to user connection is valid
        network.addConnection(server1, server2, 12);       // server to server connection is valid
        network.addConnection(server1, user2, 4);          // server to multiple user connection is valid
        network.addConnection(user1, user2, 1);            // user to user connection is invalid
        network.addConnection(user1, server2, 20);         // multiple server to user connection is invalid
        network.addConnection(server2, user2, 20);         // multiple server to user connection is invalid

        System.out.println();
        System.out.println(network);

        System.out.println("Number of connected hosts: " + network.getNumHosts());
        System.out.println("Number of connected servers: " + network.getNumServers());
        System.out.println("Number of connected users: " + network.getNumUsers());

        Scanner opt = new Scanner(System.in);

        System.out.println("Select an option.\n\tS for Send Message\n\tO for Open User Inbox" +
                "\n\tV for View Graph Details");
        System.out.println("Press the <spacebar> then <enter> key to exit.");
        String answer = opt.nextLine();

        switch (answer) {
            case "S":
            case "s":
                network.sendPacket();
                System.out.println("Would you like to send another message? (Y/N)");
                String ans = opt.nextLine();
                switch (ans) {
                    case "Y":
                    case "y":
                        network.sendPacket();
                        break;
                    case "N":
                    case "n":
                        break;
                }
                break;
            case "O":
            case "o":
                System.out.println("Enter User: ");
                for (String name : network.members.keySet()) {
                    if (network.members.get(name).getType() == Host.Type.USER)
                        System.out.println("\t" + name);
                }
                String name = opt.nextLine();
                Host user = network.members.get(name);
                user.openInbox();
                break;
            case "V":
            case "v":
                System.out.println();
                System.out.println(network);
                System.out.println("Number of connected hosts: " + network.getNumHosts());
                System.out.println("Number of connected servers: " + network.getNumServers());
                System.out.println("Number of connected users: " + network.getNumUsers());
        }

    }

    public void addHost(Host h) {
        members.put(h.getName(), h);
        network.put(h, new LinkedList<Node>());
        numHosts++;

        if (h.getType() == Host.Type.SERVER)
            numServers++;
        else
            numUsers++;
    }

    public void removeHost(Host h) {
        if (h.isActive())
            h.setActivate(false);
    }

    public void addConnection(Host A, Host B, int weight) {
        if (!network.containsKey(A))
            addHost(A);

        if (!network.containsKey(B))
            addHost(B);

        if (!(A.getType() == Host.Type.USER && B.getType() == Host.Type.USER)) {
            Node node1 = new Node(A, weight);
            Node node2 = new Node(B, weight);

            if (((!network.get(A).isEmpty()) && (A.getType() == Host.Type.USER)) || ((!network.get(B).isEmpty()) && (B.getType() == Host.Type.USER))) {
                if (A.getType() == Host.Type.USER)
                    System.out.println("User " + A.getName() + " is already connected to a server.");
                if (B.getType() == Host.Type.USER)
                    System.out.println("User " + B.getName() + " is already connected to a server.");
            } else {
                network.get(A).add(node2);
                network.get(B).add(node1);
            }

        } else {
            System.out.println("User cannot be directly connected to a server.");
        }
    }

    public void sendPacket() {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose a user (source): ");
        //System.out.println(network.getUsers);
        String sourceName = input.nextLine();
        Host source = members.get(sourceName);

        System.out.println("Enter your message: ");
        String message = input.nextLine();

        System.out.println("Choose a user (destination): ");
        //System.out.println(network.getUsers);
        String destName = input.nextLine();
        Host destination = members.get(destName);

        LinkedList<Host> path = new LinkedList<>();
        path.add(source);
        path.add(network.get(source).get(0).getHost());
        // .get(source): returns linked list for source host
        // .get(1): returns the only node (i.e. the node containing the server)
        // .getHost(): returns the host at the node (i.e. the server)
        path.add(destination);

        //TO-DO: implement real pathfinder in phase 4
        //LinkedList<Host> path = fastestPath(Host source, Host destination);
        //Packet p = new Packet(message);

        Packet packet = new Packet(message);
        move(packet, path);
    }

    public void move(Packet packet, LinkedList<Host> path) {

        path.getFirst().setReceiver(packet);
        Iterator<Host> iter = path.iterator();

        while (iter.hasNext()) {
            iter.next().setReceiver(packet);
        }

        path.getLast().updateInbox(path.getLast().getReceiver());
        System.out.println("Success!");
    }

    public LinkedList<Host> fastestPath(Host source, Host destination) {
        LinkedList<Host> path = new LinkedList<>();
        return path;
    }

    public int getNumHosts() {
        return numHosts;
    }

    public int getNumServers() {
        return numServers;
    }

    public int getNumUsers() {
        return numUsers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Host v : network.keySet()) {
            builder.append(v.toString() + ": ");
            for (Node n : network.get(v)) {
                if (v.getType() == Host.Type.SERVER)
                    builder.append(n.getHost().toString() + " (" + n.getWeight() + ") -  ");
                else
                    builder.append(n.getHost().toString() + " (" + n.getWeight() + ")");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }

    private class Node {
        private Host host;
        private int weight;

        Node(Host h, int w) {
            host = h;
            weight = w;
        }

        public Host getHost() {
            return host;
        }

        public int getWeight() {
            return weight;
        }
    }
}
