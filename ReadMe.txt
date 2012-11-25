MBTA Travel Application
By Cosimo Leone, Jeremiah Froh, Peter Wilson

Contacts:		[@husky.neu.edu]
		leone.co
		froh.j
		wilson.p

Our project is pretty simple so far, the only thing you need to do to get our project going is:

Please cd into our project folder once you are done uncompressing it.
When you are located in the same directory as our MainWindowGUI.java file, simply type:

$ javac MainWindowGUI.java

Once the .class files have been generated, type the following:

$ java -cp /path/to/MainWindowGUI.class MainWindowGUI

We do not yet utilize Jackson in order access online data, choosing instead to parse the CSV files manually.

Once the application is running, simply press the "Fetch Train Data" button located at the bottom of the application window.

——————————————————————————————————————————————————————————————————



To run:

$ cd mbta_tree
$ make test <--- will compile and run JsonTest