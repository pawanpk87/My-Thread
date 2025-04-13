# Uber Ride

Imagine a group of Republicans and Democrats trying to leave a conference. They all want to share Uber rides, but to avoid conflicts, the Uber algorithm needs to ensure that:

* A ride has either all Republicans or all Democrats.
* Alternatively, a ride can have a balanced mix of exactly two Republicans and two Democrats.

Your job is to simulate this situation using threads, where each thread represents a person (either a Democrat or a Republican) trying to get a ride. The threads must wait until a valid combination (all from one party or two from each party) is ready before they can proceed to the ride.