# Shèngdà de liánxì (SDL)
This is a Data Structures class project for the Fall 2019 semester. 

### Authors
* Stacy Sarfo - Second-year Computer Engineering student at Ashesi University
* Chinwe Ibegbu - Second-year Computer Science student at Ashesi University

### Overview
Model Base: Networking – the internet. 
Aim: Model movement of packets between end users (hence referred to as “users”) in a network, taking into consideration the following variables:
* Latency
* Sender and recipient users
* Efficiency 

### Components
•	Network: The network will be represented using the graph data structure with servers acting as internal nodes and users as external nodes/leaves.
•	Servers: A server is an internal node of the network which houses a server name, a latency table and connected edges.
•	Users: A user an external node of the network which houses a username and an inbox, which will be represented by the stack data structure.
•	Packets: A packet contains data which can be “sent” from one user to another over the network users. It will contain the names of the sender and recipient users, the data to be transferred and the path to be taken.
•	Path: A path represents how a packet will move from one user to another over server connections. It is represented by the linked list data structure and is determined by a Dijkstra’s algorithm implementation which will take into consideration the latency and the sender and receiver users.

__Phase 1__
- [ ] Implement Level 1 network (See fig. 1 in Appendix)
- [x] Create server and user nodes
- [ ] Create four (4) servers and connect them in a simple square
- [x] Connect a minimum of one (1) user and a maximum of three (3) users to each of the four (4) servers
- [ ] Create and send two (2) test packets
- [ ] Read from the inbox of the recipient users

__Phase 2__
- [ ] Implement Level 2 network (See fig. 2 in Appendix)
- [ ] Create four (4) more servers and connect to more than one (1) server each
- [ ] Randomize latency time
- [x] Initiate a “conversation” between two users
- [x] Create a “Unread” and “Read” stack for the user inbox
