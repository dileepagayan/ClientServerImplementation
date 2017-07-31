# Custom Client Server Implementation With Thread Pool And Queue


Server
* Theadpool 
* Task Executor
* Request
* Linked List Queue


This program is creating fixed number of threads at the starting the server with fixed Queue size for given parameters.


<h4>Client</h4>

multi threaded client program


Instruction to Run Program

Client -sh startClient.sh {numberOfClients}    

Ex :sh startClient.sh 20

<h4>Server</h4>


Server -sh startServer.sh {serverPort} {queueSize} {numberOfThreads} {RequestWaitTimeOnServer}


Ex:- sh startServer.sh 9000 10 5 1


RequestWaitTimeOnServer - if this parameter is not 0 it will sleep the request task for number of seconds.





<h2>References :-</h2>

http://tutorials.jenkov.com/java-concurrency/thread-pools.html

http://tutorials.jenkov.com/java-multithreaded-servers/thread-pooled-server.html

http://www.makeinjava.com/custom-thread-pool-example-without-using-executor-framework/

http://oppansource.com/implementing-own-thread-pool-in-java/




