# Traffic Light using Spring State Machine
Single Set of Traffic Light implementation using Spring State Machine.
The States of the Traffic Light are - Green, Amber and Red. The Transition Events are - Go, Stop and Caution.
Spring State Machine is used to configure the different States and how they Transition based on the transition event received. The transition time by default is set to 2 seconds. The Transition Intervals for each of the light phases can be programmed. The application uses in-memory H2 DB to store the transitions in the Transition_interval table as below:

ID  |  DAY_OF_WEEK  |  TIME_OF_DAY  |  TRAFFIC_LIGHT_EVENT  |  TRANSITION_TIME  

1   |  MONDAY	    |  0	        |  GO	                |  2

2	|  MONDAY	    |  0	        |  STOP	                |  1

Endpoints:

1. Use http://localhost:8080/trafficLight to get to the Traffic Light UI
2. Use http://localhost:8080/trafficLight/sampleData to load sample data to Transition_Interval table.