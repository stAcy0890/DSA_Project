package com.dsa.Internet;

import java.util.*;

public class Model {
    private Map<Host, LinkedList<Node>> network = new HashMap<>();
    private Map<String, Host> members = new HashMap<>();
    private int numHosts;
    private int numServers;
    private int numUsers;

    public static void main(String[] args) {
        System.out.println("\nWELCOME.\n");

        // Creating the network
        System.out.println("Creating network...");
        Model network = new Model();

        // Creating servers and users using the Host class
        System.out.println("Creating servers...");
        Host server1 = new Host("A", Host.Type.SERVER);
        Host server2 = new Host("B", Host.Type.SERVER);
        Host server3 = new Host("C", Host.Type.SERVER);
        Host server4 = new Host("D", Host.Type.SERVER);
        Host server5 = new Host("E", Host.Type.SERVER);
        Host server6 = new Host("F", Host.Type.SERVER);
        System.out.println("Creating users...");
        Host user1 = new Host("Bob", Host.Type.USER);
        Host user2 = new Host("Dan", Host.Type.USER);
        Host user3 = new Host("Sue", Host.Type.USER);
        Host user4 = new Host("Robert", Host.Type.USER);
        Host user5 = new Host("Hans", Host.Type.USER);
        Host user6 = new Host("Bill", Host.Type.USER);
        // This server will not be added to the network
        Host server7 = new Host("Redundant", Host.Type.SERVER);

        // Adding all hosts to the network
        System.out.println("Adding servers and users to network...");
        network.addHost(server1);
        network.addHost(server2);
        network.addHost(server3);
        network.addHost(server4);
        network.addHost(server5);
        network.addHost(server6);
        network.addHost(user1);
        network.addHost(user2);
        network.addHost(user3);
        network.addHost(user4);
        network.addHost(user5);
        network.addHost(user6);

        // Adding connections between users and servers
        // while testing the validity of requested connections
        System.out.println("Adding connections...");
        network.addConnection(server1, user1, 1);          // server to user connection is valid
        network.addConnection(server1, server2, 6);        // server to server connection is valid
        network.addConnection(server1, user2, 1);          // server to multiple user connection is valid
        network.addConnection(user1, user2, 1);            // user to user connection is invalid
        network.addConnection(user1, server2, 20);         // multiple server to user connection is invalid
        network.addConnection(server2, user2, 20);
        network.addConnection(server1, server4, 11);
        network.addConnection(server4, server2, 8);
        network.addConnection(server4, server5, 5);
        network.addConnection(server2, server5, 2);
        network.addConnection(server2, server3, 4);
        network.addConnection(server5, server3, 10);
        network.addConnection(server4, user4, 1);
        network.addConnection(server2, user5, 1);
        network.addConnection(server3, user3, 1);
        network.addConnection(server5, server6, 3);
        network.addConnection(server6, user6, 1);

        System.out.println();
        System.out.println("Number of connected hosts: " + network.getNumHosts());
        System.out.println("Number of connected servers: " + network.getNumServers());
        System.out.println("Number of connected users: " + network.getNumUsers());

        System.out.println();
        System.out.println(network);
        System.out.println("Removing servers...");
        network.removeHost(server7);                        // testing removal of a server not within the network
        network.removeHost(server6);                        // testing removal of a server within the network

        System.out.println();
        System.out.println(network);
        System.out.println("Number of connected hosts: " + network.getNumHosts());
        System.out.println("Number of connected servers: " + network.getNumServers());
        System.out.println("Number of connected users: " + network.getNumUsers());

        System.out.println();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Proceed to send emails, read your latest email or view the network.");
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
                            network.sendPacket();
                            System.out.println("Would you like to send another message? (Y/N)");
                            ans = opt.nextLine();
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

    /*
     * The addHost(...) method adds a host to the network HashMap.
     * The number of Hosts as well as the number of Hosts by type
     * are incremented accordingly.
     *
     * Parameter(s): The host to be added
     * Return value: None
     */
    public void addHost(Host h) {
        members.put(h.getName(), h);
        network.put(h, new LinkedList<Node>());
        numHosts++;

        if (h.getType() == Host.Type.SERVER)
            numServers++;
        else
            numUsers++;
    }

    /*
     * The removeHost(...) method removes a host from the network HashMap.
     * The host is removed from the network and name HashMaps.
     * It is also removed from the neighbours of all other hosts.
     * The number of Hosts as well as the number of Hosts by type
     * are decremented accordingly.
     *
     * Parameter(s): The host to be removed
     * Return value: None
     */
    public void removeHost(Host h) {
        LinkedList<Node> tempNodes = new LinkedList<Node>();
        LinkedList<Host> tempHosts = new LinkedList<Host>();
        int index = 0;

        // Checking if the Host h is in the network
        if (!network.containsKey(h)) {
            System.out.println(">>> Removal Failed: Host '" + h.getName() + "' not found in network.");
        } else {
            tempNodes = network.get(h);         // getting all neighbours of Host h
            network.remove(h);                  // removing Host h from the network HashMap

            // Creating a list of all neighbour Hosts from the list of neighbour Nodes
            for (int i = 0; i < tempNodes.size(); i++) {
                tempHosts.add(tempNodes.get(i).host);
            }

            // Removing Host h from the adjacency list of its neighbours
            for (int j = 0; j < tempHosts.size(); j++) {
                // Making a copy of the adjacency list of each neighbour
                LinkedList<Node> neighbours = network.get(tempHosts.get(j));
                // Finding the index of Host h in the adjacency list of its neighbours
                for (int l = 0; l < neighbours.size(); l++) {
                    if (neighbours.get(l).host == h) {
                        index = l;
                        break;
                    }
                }
                neighbours.remove(index);
                // Replacing the adjacency list of each neighbour with the list without Host h
                network.replace(tempHosts.get(j), neighbours);

                if (tempHosts.get(j).getType() == Host.Type.USER) {
                    network.remove(tempHosts.get(j));
                    members.remove(tempHosts.get(j).getName());
                }
            }
            members.remove(h.getName());        // removing Host h from the members HashMap


            if (h.getType() == Host.Type.SERVER)
                numServers--;
            else
                numUsers--;
            numHosts--;
        }

    }

    /*
     * The addConnection(...) method creates a weighted edge
     * between two hosts in the network.
     * A connection is made in both directions and certain conditions
     * must be met for a connection to be made
     *
     * Parameter(s): The hosts to be connected; weight of the connection
     * Return value: None
     */
    public void addConnection(Host A, Host B, int weight) {
        if (!network.containsKey(A))
            addHost(A);

        if (!network.containsKey(B))
            addHost(B);

        // Check if both hosts are users --> INVALID CONNECTION
        if (!(A.getType() == Host.Type.USER && B.getType() == Host.Type.USER)) {
            Node node1 = new Node(A, weight);
            Node node2 = new Node(B, weight);

            // Check if the host is a user that already has a server --> INVALID CONNECTION
            if (((!network.get(A).isEmpty()) && (A.getType() == Host.Type.USER)) || ((!network.get(B).isEmpty()) && (B.getType() == Host.Type.USER))) {
                if (A.getType() == Host.Type.USER)
                    System.out.println(">>> Connection failed: User " + A.getName() + " is already connected to a server.");
                if (B.getType() == Host.Type.USER)
                    System.out.println(">>> Connection failed: User " + B.getName() + " is already connected to a server.");
            } else {
                network.get(A).add(node2);
                network.get(B).add(node1);
            }

        } else {
            System.out.println(">>> Connection failed: User cannot be directly connected to a server.");
        }
    }

    /*
     * The printUsers() method prints out all host users by iterating
     * through all keys in the network HashMap
     *
     * Parameter(s): None
     * Return value: None
     */
    public void printUsers() {
        for (String name : this.members.keySet()) {
            if (this.members.get(name).getType() == Host.Type.USER)
                System.out.println("\t" + name);
        }
    }

    /*
     * The sendPacket() sends a packet (which is a proxy email)
     * from one user to another along a path defined by the
     * fastestPath() method using the move(). Packets are created
     * within this method as well using the Packet class constructor.
     *
     * Parameter(s): None
     * Return value: None
     */
    public void sendPacket() {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose a user (source): ");
        this.printUsers();
        String sourceName = input.nextLine();
        Host source = members.get(sourceName);

        System.out.println("Enter your message: ");
        String message = input.nextLine();

        System.out.println("Choose a user (destination): ");
        this.printUsers();
        String destName = input.nextLine();
        Host destination = members.get(destName);

        LinkedList<Host> path = fastestPath(source, destination);

        Packet packet = new Packet("FROM " + sourceName + ": " + message);
        move(packet, path);
    }

    /*
     * The move(...) method sends a packet from one host to another along
     * the LinkedList<Host> path.
     * Packets is temporarily stored in the receiver of each host in the path.
     *
     * Parameter(s): The packet to be sent; the shortest path for travel
     * Return value: None
     */
    public void move(Packet packet, LinkedList<Host> path) {

        // Displaying the path to be traversed
        System.out.print(">>> PATH: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getName() + " --> ");
        }
        System.out.println("END");

        path.getFirst().setReceiver(packet);
        Iterator<Host> iter = path.iterator();

        // Moving packet from the receiver of one host to the next along the defined path
        while (iter.hasNext()) {
            iter.next().setReceiver(packet);
        }

        // Adding the packet in the receiver of the recipient to the unreadInbox of said recipient
        path.getLast().updateInbox(path.getLast().getReceiver());
        System.out.println("Success!");
    }

    /*
     * The fastestPath(...) method finds the shortest path from the source
     * to the destination using Dijkstra's Algorithm.
     * See in-line comments to follow the algorithm's steps.
     *
     * Parameter(s): The source and destination hosts
     * Return value: LinkedList<Host> path
     */
    public LinkedList<Host> fastestPath(Host source, Host destination) {
        LinkedList<Host> path = new LinkedList<>();                    // create path

        // check if source and destination is in the network
        if (members.containsValue(source) == false || members.containsValue(destination) == false) {
            System.out.println("Destination not found.");
        } else {
            ArrayList<Host> visited = new ArrayList<Host>();           // create visited list
            ArrayList<Host> unvisited = new ArrayList<Host>();         // create unvisited list
            Host current = network.get(source).get(0).getHost();       // set start of path finder to the source's server
            current.setD(0);                                           // STEP 1: set distance of start from start to 0

            Host parentSource = network.get(source).get(0).getHost();
            Host parentDestination = network.get(destination).get(0).getHost();

            // Check if source and destination are the same
            // If so, do not proceed with Dijkstra's algorithm
            if (source == destination) {
                path.add(source);
                path.add(parentSource);
                path.add(destination);
                return path;
            }

            // STEP 2: Add all servers to the unvisited stack
            for (Map.Entry<String, Host> member : members.entrySet()) {
                if (member.getValue().getType() == Host.Type.SERVER) {
                    unvisited.add(member.getValue());
                }
            }

            // STEP 3: Repeat the following process until all the nodes have been visited
            while (unvisited.size() != 0) {
                ArrayList<Node> neighbours = new ArrayList<>();

                // Find index of the current node
                int index = unvisited.indexOf(current);

                // Examine the unvisited neighbours of current vertex
                LinkedList<Node> n = network.get(current);
                for (int j = 0; j < n.size(); j++) {
                    Node temp = n.get(j);
                    if (temp.host.getType() == Host.Type.SERVER && unvisited.contains(temp.host)) {
                        neighbours.add(n.get(j));
                    }
                }

                // For each unvisited neighbour of the current vertex:
                //      1. Calculate the distance from the start vertex to the neighbour
                //      2. if the distance is less than the neighbbour's d value, update d
                //         and make its parent the current vertex
                int dist = 0;
                for (int s = 0; s < neighbours.size(); s++) {
                    dist = current.getD() + neighbours.get(s).weight;
                    if (dist < neighbours.get(s).host.getD()) {
                        neighbours.get(s).host.setD(dist);
                        neighbours.get(s).host.setParent(current);
                    }
                }

                // Add current to the Visited ArrayList and remove from it from the Unvisited ArrayList
                Host temporary = unvisited.get(index);
                unvisited.remove(index);
                visited.add(temporary);

                // Check unvisited hosts for vertex with the least d value and make that vertex current
                int tempMin = 100;
                for (int k = 0; k < unvisited.size(); k++) {
                    if (tempMin > unvisited.get(k).getD()) {
                        tempMin = unvisited.get(k).getD();
                        if (current == parentSource)
                            unvisited.get(k).setParent(current);
                        current = unvisited.get(k);
                    }
                }
            }

            // STEP 4: Create the path
            path.add(source);                                       // add source to the path
            Host c = parentDestination;

            // Trace the path from the destination's server to the source's server
            LinkedList<Host> tempPath = new LinkedList<>();
            while (c != parentSource) {
                tempPath.add(c.getParent());
                c = c.getParent();
            }
            // Reverse the order and insert into the real path
            if (tempPath.size() > 0) {
                for (int x = tempPath.size() - 1; x >= 0; x--) {
                    path.add(tempPath.get(x));
                }
            }

            path.add(parentDestination);                             // add destination's server to the path
            path.add(destination);                                   // add destination to the path

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
        System.out.println("NETWORK NAME: Shèngdà de liánxì");
        System.out.println(">>> View the network adjacency lists below:");
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

    /*
     * This Node class defines a node in the linked list for
     * the adjacency lists that represent neighbours.
     *
     * Each node contains the neighbour host and the
     * weight of the connection.
     */
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


