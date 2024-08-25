The Guarded Suspension Pattern is used to manage access to an object that is shared among multiple threads. It allows a thread to wait for a specific condition before proceeding with its execution. This pattern is useful when threads need to coordinate their actions, such as waiting for a resource to become available.

Example Scenario:
Consider a PrintQueue system where multiple threads (print jobs) want to print documents, but only one can print at a time. If the printer is busy, other print jobs must wait until it becomes available.
