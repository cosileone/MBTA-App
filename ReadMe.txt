MBTA Travel Application
By Cosimo Leone, Jeremiah Froh, Peter Wilson

Contacts:		[@husky.neu.edu]
		leone.co
		froh.j
		wilson.p


Our project is pretty simple so far, and to get our application running:

Please cd into our project folder once you are done uncompressing it.

INSTALL:
——————————————————————————————————————————————————————————————————
Please change directories into our project folder once you are done uncompressing it.

$ cd MBTA_App

COMPILE & RUN:
——————————————————————————————————————————————————————————————————
Just type "make run" in the CLI - no quotes

$ make run

After you have entered your destinations using the drop-down menus and/or by entering them by hand, and also selected the departing/arriving by times, if you want the trip sorted (the locking down the path), or unsorted, and also selected a drop down menu choice of wanting the earliest departure/arrival, fastest time - simply press the "Plan My Trip!" button located at the bottom of the application window. You will be taken to the Itinerary view, your train information will show up in the command line.

——————————————————————————————————————————————————————————————————

To make sure the data collection is working, simply type:

$ make test

and watch as the data gets displayed before you.


TO USE: - Once the application is running
——————————————————————————————————————————————————————————————————
[X]The user wants to know where she can go using the T. (Essential)

 Each box is color coded per line and has all available stops listed sequentially.
 
 User: Selects dropbox (Red, Blue, Green) for destinations by line.
 System: Produces selectable list of all available stations based on line chosen. 
 User: Repeats above step for all destinations.

[/]The user wants to know the current location of all trains. (Essential)

 Train location are displayed on live map and is the first screen present upon startup 
 - Train locations are currently displayed in list format back end

[/]The user wants to know when the next trains get to stop A. (Essential)

User: Types desired station into text form; clicks add.
System: Click handler adds form value to the trip planner queue.
User: Clicks on the ‘plan trip’ button to display arrival times of next train.
System: Returns next arrival from desired station.

[/]The user wants to know her options for getting from stop A to stop B. (Essential)

User: Selects start station from one of the drop-down menus.  
System: Adds form value to the trip planner queue.
User: Selects end station from one of the drop-down menus.
System: Adds form value to the trip planner queue.
User: Clicks on the ‘plan trip’ button
System: Returns arrival times and route


[ ] The user wants to know her options for getting to an ordered list of stops. (Desirable)

[ ] Algorithm in development
[ ] Attach to GUI

[ ]The user wants to know her options for getting to an unordered list of stops. (Optional)

[]The user wants to know her options for getting to an unordered list of stops with specified starting and/or ending points. (Optional)

[]For any trip on the T, the user wants the option to specify departure and/or arrival times. (Desirable)

[X]For any trip on the T, the user wants to know the fastest route, the earliest departure, the earliest arrival, and fewest transfers. (Desirable)
   

REQUIREMENTS:
——————————————————————————————————————————————————————————————————
[X] The client wants to test the system with old data. (Essential)
[X] The system should be based on live data from the MBTA (http://www.mbta.com/uploadedfiles/Description.pdf).
[ ] The only information the system should access online is MBTA data.
[X] The system should be written in Java and run on CCIS Linux machines with only their standard software and JSON library in the course directory.
[X] The system should accept test data from file input.
[X] User Interface
[X] The system should include a user-friendly GUI. (Optional)


If you want test data:
JsonTest.java file - in the fastestPath method --- 
        g = getGraphFromInternet();
        //g = getGraphFromFiles(fileLocations); << Uncomment this, and add hardcode the files into fileLocations (an ArrayList).


