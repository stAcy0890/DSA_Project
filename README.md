# Shèngdà de liánxì (SDL)
This is a Data Structures class project for the Fall 2019 semester. 

### Authors
* Stacy Sarfo 
* Chinwe Ibegbu 

### Overview
SDL focuses on modelling networking in terms of the internet. The __aim__ of this project is to model the movement of packets between end users (hence referred to as “users”) in a network, taking into consideration the following variables:
* Latency (defined by the weight on edges)
* Sender and recipient users
* Efficiency (which defines the data structures chosen)
The intended __application__ of this project is in teaching networking at a secondary school level. With the development of an appropriate Graphical User Interface (GUI), the authors of this project hope to aid the complete understanding of key, basic networking terminology and processes. 

### Components
* Network: The network will be represented using the graph data structure with servers acting as internal nodes and users as external nodes/leaves. 
* Hosts: A host is a node in the network which can either be a server or a client. The key features of each  host are a name, type and inbox.
  * Servers: A server is an internal node of the network which houses a server name, a latency table and connected edges.
  * Users: A user an external node of the network which houses a username and an inbox, which will be represented by the stack data structure.
* Packets: A packet contains data which can be “sent” from one user to another over the network users. It will contain the names of the sender and recipient users, the data to be transferred and the path to be taken.
* Path: A path represents how a packet will move from one user to another over server connections. It is represented by the linked list data structure and is determined by a Dijkstra’s algorithm implementation which will take into consideration the latency and the sender and receiver users.

__Phase 1__
- [x] Create server and user nodes
- [ ] Create four (4) servers and connect them in a simple square
- [x] Connect a minimum of one (1) user and a maximum of three (3) users to each of the four (4) servers
- [x] Create and send a test packet
- [x] Read from the inbox of the recipient users

__Phase 2__
- [x] Create four (4) more servers and connect to more than one (1) server each
- [ ] Initiate a “conversation” between two users by sending packets to and fro
- [x] Create a “Unread” and “Read” stack for the user inbox
- [x] Implement Dijkstra's algorithm

__Phase 3__
- [ ] Allow end user choose action 
- [ ] Randomize latency time 
- [ ] Create a user interface

### Acknowledgements 
* Dijkstra's algorithm: "Graph Data Structure 4. Dijkstra's Shortest Path Algorithm" by Computer Science (https://youtu.be/pVfj6mxhdMw)
