package com.dsa.Internet;

import java.util.*;

public class Model {
    private Map<Host, LinkedList<Node>> network = new HashMap<>();
    private Map<String, Host> members = new HashMap<>();
    private int numHosts;
    private int numServers;
    private int numUsers;

    public static void main(String[] args) {
        Model network = new Model();

        Host server1 = new Host("A", Host.Type.SERVER);
        Host server2 = new Host("B", Host.Type.SERVER);
        Host server3 = new Host("C", Host.Type.SERVER);
        Host server4 = new Host("D", Host.Type.SERVER);
        Host server5 = new Host("E", Host.Type.SERVER);
        Host user1 = new Host("Bob", Host.Type.USER);
        Host user2 = new Host("Dan", Host.Type.USER);
        Host user3 = new Host("Sue", Host.Type.USER);
        Host user4 = new Host("Robert", Host.Type.USER);
        Host user5 = new Host("Hans", Host.Type.USER);

        network.addHost(server1);
        network.addHost(server2);
        network.addHost(server3);
        network.addHost(server4);
        network.addHost(server5);
        network.addHost(user1);
        network.addHost(user2);
        network.addHost(user3);
        network.addHost(user4);
        network.addHost(user5);

        System.out.println(network);

        network.addConnection(server1, user1, 1);          // server to user connection is valid
        network.addConnection(server1, server2, 6);        // server to server connection is valid
        network.addConnection(server1, user2, 1);          // server to multiple user connection is valid
        network.addConnection(user1, user2, 1);            // user to user connection is invalid
        network.addConnection(user1, server2, 20);         // multiple server to user connection is invalid
        network.addConnection(server2, user2, 20);         // multiple server to user connection is invalid
        network.addConnection(server1, server4, 11);
        network.addConnection(server4, server2, 8);
        network.addConnection(server4, server5, 5);
        network.addConnection(server2, server5, 2);
        network.addConnection(server2, server3, 4);
        network.addConnection(server5, server3, 10);
        network.addConnection(server4, user4, 1);
        network.addConnection(server2, user5, 1);
        network.addConnection(server3, user3, 1);


        System.out.println();
        System.out.println(network);
        //network.removeHost(server2);

        System.out.println("Number of connected hosts: " + network.getNumHosts());
        System.out.println("Number of connected servers: " + network.getNumServers());
        System.out.println("Number of connected users: " + network.getNumUsers());

        System.out.println();
        Scanner opt = new Scanner(System.in);

        System.out.println("Select an option.\n\tS for Send Message\n\tO for Open User Inbox" +
                "\n\tV for View Graph Details");
        System.out.println("Press the <spacebar> then <enter> key to exit.");
        String answer = opt.nextLine();

        while (!answer.equals(" ") && !answer.equals("")) {
            switch (answer) {
                case "S":
                case "s":
                    network.sendPacket();
                    System.out.println("Would you like to send another message? (Y/N)");
                    String ans = opt.nextLine();
                    switch (ans) {
                        case "Y":
                        case "y":
                            for (String name : network.members.keySet()) {
                                if (network.members.get(name).getType() == Host.Type.USER)
                                    System.out.println("\t" + name);
                            }
                            String name = opt.nextLine();
                            Host user = network.members.get(name);
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

            System.out.println("Select an option.\n\tS for Send Message\n\tO for Open User Inbox" +
                    "\n\tV for View Graph Details");
            System.out.println("Press the <spacebar> then <enter> key to exit.");
            answer = opt.nextLine();
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
        if (h.isActive()) {
            h.setActivate(false);
            if (h.getType() == Host.Type.SERVER)
                numServers--;
            else
                numUsers--;
            numHosts--;
        }
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

//        LinkedList<Host> path = new LinkedList<>();
//        path.add(source);
//        path.add(network.get(source).get(0).getHost());
//        // .get(source): returns linked list for source host
//        // .get(1): returns the only node (i.e. the node containing the server)
//        // .getHost(): returns the host at the node (i.e. the server)
//        path.add(destination);

        //TODO: implement real pathfinder in phase 4
        LinkedList<Host> path = fastestPath(source, destination);

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
        LinkedList<Host> path = new LinkedList<>();                // create path

        // check if source and destination is in the network
        if (members.containsValue(source) == false || members.containsValue(destination) == false)
            System.out.println("Destination not found.");
        else {
            ArrayList<Host> visited = new ArrayList<Host>();           // create visited list
            ArrayList<Host> unvisited = new ArrayList<Host>();         // create unvisited list
            Host current = network.get(source).get(0).getHost();       // set start of path finder to the source's server
            current.setD(0);                                           // set distance of start from start to 0

            Host parentSource = network.get(source).get(0).getHost();
            Host parentDestination = network.get(destination).get(0).getHost();

            // add all servers to the unvisited stack
            for (Map.Entry<String, Host> member : members.entrySet()) {
                if (member.getValue().getType() == Host.Type.SERVER) {
                    unvisited.add(member.getValue());
                }
            }

            System.out.println("Initial Unvisited: " + unvisited);
            System.out.println("Initial Visited: " + visited);

            while (unvisited.size() != 0) {
                ArrayList<Node> neighbours = new ArrayList<>();

                // find index of the current node
                int index = unvisited.indexOf(current);
                System.out.println(index);

                // examine the unvisited neighbours of current node
                LinkedList<Node> n = network.get(current);
                for (int j = 0; j < n.size(); j++) {
                    Node temp = n.get(j);
                    if (temp.host.getType() == Host.Type.SERVER && unvisited.contains(temp.host)) {
                        neighbours.add(n.get(j));
                    }
                }

                System.out.print("Neighbours: ");
                for (int y = 0; y < neighbours.size(); y++) {
                    System.out.print(neighbours.get(y).host.getName());
                    System.out.print(" ");
                }
                System.out.println();

                // for each unvisited neighbour {1. check dist = current.d + weight, 2. if dist < d, update d and make parent = current}
                int dist = 0;
                for (int s = 0; s < neighbours.size(); s++) {
                    dist = current.getD() + neighbours.get(s).weight;
                    if (dist < neighbours.get(s).host.getD()) {
                        neighbours.get(s).host.setD(dist);
                        neighbours.get(s).host.setParent(current);
                    }
                }

                for (int ss = 0; ss < unvisited.size(); ss++) {
                    System.out.println(unvisited.get(ss).getName() + ": " + unvisited.get(ss).getD());
                }
                System.out.println();

                // add current to visited and remove from unvisited
                Host temporary = unvisited.get(index);
                unvisited.remove(index);
                visited.add(temporary);
                System.out.println("Unvisited: " + unvisited);
                System.out.println("Visited: " + visited);

                // check unvisited hosts for least distance and make current node
                int tempMin = 100;
                for (int k = 0; k < unvisited.size(); k++) {
                    if (tempMin > unvisited.get(k).getD()) {
                        tempMin = unvisited.get(k).getD();
                        if (current == parentSource)
                            unvisited.get(k).setParent(current);
                        current = unvisited.get(k);
                    }
                }
                System.out.println(current.getName());
                System.out.println("----------------");
            }

            path.add(source);                                       // add source
            path.add(parentSource);                                 // add source's server
            Host c = parentDestination;
            LinkedList<Host> tempPath = new LinkedList<>();
            while (c.getParent() != parentSource) {
                tempPath.add(c.getParent());
                c = c.getParent();
            }
            for (int x = tempPath.size() - 1; x < 0; x--) {
                path.add(tempPath.get(x));
            }
            path.add(parentDestination);                             // add destination's server
            path.add(destination);                                   // add destination


        }
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

/*
                // find the unvisited host with the lowest distance from the start
                for (int i = 0; i < unvisited.size(); i++) {
                    if (min > unvisited.get(i).getD()) {
                        min = unvisited.get(i).getD();
                        index = i;
                    }
                }
                */
