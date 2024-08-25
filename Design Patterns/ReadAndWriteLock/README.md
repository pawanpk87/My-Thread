A read/write lock allows multiple threads to read a resource concurrently but gives exclusive access to only one thread for writing.

It’s used when there are many readers and few writers. This way, readers can access the resource concurrently while still ensuring that writing is done exclusively.