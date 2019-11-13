# DSA_Project
Fall2019_DSA_Project

Data Structures Project Description
Stacy Sarfo, Chinwe Ibegbu


Overview
Project Name: Shèngdà de liánxì (SDL)
Model Base: Networking – the internet
Aim: Model movement of packets between end users (hence referred to as “users”) in a network, taking into consideration the following variables:
•	Latency
•	Sender and recipient users
•	Efficiency 

Components
•	Network: The network will be represented using the graph data structure with servers acting as internal nodes and users as external nodes/leaves.
•	Servers: A server is an internal node of the network which houses a server name, a latency table and connected edges.
•	Users: A user an external node of the network which houses a username and an inbox, which will be represented by the stack data structure.
•	Packets: A packet contains data which can be “sent” from one user to another over the network users. It will contain the names of the sender and recipient users, the data to be transferred and the path to be taken.
•	Path: A path represents how a packet will move from one user to another over server connections. It is represented by the linked list data structure and is determined by a Dijkstra’s algorithm implementation which will take into consideration the latency and the sender and receiver users.

Phase 1
1.	Implement Level 1 network (See fig. 1 in Appendix)
2.	Create server and user nodes
3.	Create four (4) servers connected in a simple square
4.	Connect a minimum of one (1) user and a maximum of three (3) users to each of the four (4) servers
5.	Create and send two (2) test packets
6.	Read from the inbox of the recipient users

Phase 2
1.	Implement Level 2 network (See fig. 2 in Appendix)
2.	Create four (4) more servers and connect to more than one (1) server each
3.	Randomize latency time
4.	Initiate a “conversation” between two users
5.	Create a “Unread” and “Read” stack for the user inbox
