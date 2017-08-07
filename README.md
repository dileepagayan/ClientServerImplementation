# TCP-Client-Server

<h2>Custom Client Server Implementation With Thread Pool And Queue</h2>

<h3>Server</h3>

* Theadpool

* Task Executor

* Request

* Linked List Queue

ThreadPool class creating fixed number of threads and runs TaskExecutor. New requests will be queued to the Linked List Queue and Threads are handling the requests accordingly

<h3>Client</h3>

Multi threaded client program

<h2>Instruction to run program</h2>

Client -sh startClient.sh {numberOfClients}

Ex :sh startClient.sh 20

Server -sh startServer.sh {serverPort} {queueSize} {numberOfThreads} {RequestWaitTimeOnServer}

Ex:- sh startServer.sh 9000 10 5 1

RequestWaitTimeOnServer - if this parameter is not 0 it will sleep the request task for number of seconds.


<h4>References :- </h4>

http://tutorials.jenkov.com/java-concurrency/thread-pools.html

http://tutorials.jenkov.com/java-multithreaded-servers/thread-pooled-server.html

http://www.makeinjava.com/custom-thread-pool-example-without-using-executor-framework/

http://oppansource.com/implementing-own-thread-pool-in-java/
