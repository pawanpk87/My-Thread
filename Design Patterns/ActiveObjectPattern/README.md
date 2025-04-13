# Active Object Pattern (Actor Pattern)

The Active Object Pattern is a design pattern that decouples method execution from method invocation to enhance concurrency. It achieves this by using a separate thread of control to execute the methods, which allows clients to make non-blocking method calls.

* Proxy: Provides an interface for clients to invoke methods on the active object. The proxy forwards these method calls to a Scheduler. 

* Scheduler: Responsible for managing and scheduling method requests. It holds a queue of method requests that are executed sequentially by a single thread. Method Request: Represents a method invocation. It contains the method to be executed along with its parameters. 

* Servant: The actual object that implements the desired functionality. It is called by the Scheduler to perform the operations. 

* Future: Represents the result of an asynchronous operation. It allows the client to retrieve the result of a method invocation once it's available. 

* Thread: A separate thread that processes method requests in the background, allowing the main thread to continue with other tasks.

![Screenshot 2024-08-26 001627](https://github.com/user-attachments/assets/87c0e76c-e48b-4503-9f15-1b28ea37c0b8)
