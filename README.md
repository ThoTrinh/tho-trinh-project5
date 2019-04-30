# tho-trinh-project5

Project 5 documentation

Have 2 panels:
Panel 0: containing all the required components such as the ability to Enter a Hamming Distance, view all the stations that have the same hamming distance as a selected station, view the amount of stations with a certain hamming distance for each one, add station to be selected
Free Panel: contains a gif of loki to be played, and a gif of thanos snapping his fingers to be played

Panel 0 will contain 4 different panels:
Panel1: will contain a slider to choose a hamming distance and a show button to display stations with that hamming distance
Panel2: will Contain a scrollable list that shows all the stations with a certain hamming distance
Panel3: Has a DropDown box for user to choose a station to be compared with and a button to show amount of stations with each specified hamming distance
Panel4: Contains Displays that show the amount of stations with specified hamming distance and a way to add a station to stations to be picked

Have a text file to keep all the stations

Steal Logic from Previous projects to use

UNDERSTANDING – 
I have to create a GUI that contains a Slider to choose Hamming Distance desired. I have to have a display showing the number that has been chosen. I have to have a list of stations that has a certain hamming distance from another chosen station. I have to have a drop down box that allows the user to select a certain station to be compared to. I have to have a button that shows the amount of stations with certain hamming distances. I have to have a button and entry box for a station the user wishes to add.

METHOD INPUTS AND OUTPUTS – 
MesoEqual Class:
The constructor uses the readFile method, and assigns the stationID parameter to the private member. The readFile method takes in a filename , reads it, stores it in the arraylist of station IDS, and returns nothing; The getStationIDS returns the private member stationID as a string array. The calcStations takes a hamming distance wanted and a stationID and returns a list of stations with a hamming distance from that stationID. The compareID method returns a hamming distance number when you compare the parameters(fromStationID and toStationID); 
MesoAscii class:
The constructor takes the station ID and makes it into a character array and assigns it to the private member. 
HDFrame class:
The constructor initializes all the needed Components and adds the actionlisteners for the buttons. It also sets up the size of the frame and adds all the panels to the frame. The panel0setup sets the layout and size for panel 0 and adds all the panels to that panel. The freePanelSetup takes two JComponents in the freePanel and sets the layout and adds the components to the panel. Panel3setup adds the components parameters to the panel. Panel2setup adds the component to the panel. Panel 4 setup adds all the component parameters to the panel. Panel1Setup adds all the component parameters to the panel under gridbagconstraints. Main method instantiates a new HDframe();

LEARNED –
I learned more about panel layouts and how a GUI is structured. I learned more about the different kind of layouts and to choose one before you just dive in. The layout is very important in how you want it to look. I know about gridbaglayouts, cardlayouts, flowlayous, gridlayouts, and setlayouts.


Thought Process 




Enter Hamming Distance TextField, can’t be edited
Slider, sets the value of enter hamming distance text field

Show Station Button shows Stations with hamming distances equal to that in textfield
It uses the input from compare with to find stations
Shows in a list (cardLayout)?? Can be scrolled

Calculate HD clears the stations list
Shows how many stations with the following numbers

aDD STATIONS adds station to drop down box

Steps:

Make a frame,
3 buttons so need mouse listeners
Need a jslider
Need 7 text fields, perhaps a big one for the stationlist
Need a dropdown box JComboBox

LayOuts?
GridLayout? 
fIRST PANEL gridbaglayout()
gridLayout (1,0)
gridLayout(8,0)
possibly springlayout to have small label and big text field but might be too much work

Logic?
How will it know what stations and how many?
Perhaps file of statiosn be read in, and have code from previous project used?
Logic must have calculate hamming distance of each station, and load stations with specific hamming distance

Have a HDPanel() that implements mouselistener and draw

https://raw.githubusercontent.com/ThoTrinh/tho-trinh-project5/master/Screen%20Shot%202019-04-29%20at%201.27.56%20PM.png?token=AKLZM4266GTAOMSAEXLPMSK4ZCDPE 
