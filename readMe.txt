Our Design

We have packages for the model, the view, the controller, and some utils.

Our model keeps track of the music itself by keeping track of notes and the tempo of the song.
The Note class keeps track of info about all of the notes
The model implements an interface that is parametrized by the kind of Note

The Controller has a an interface and an implementation as well
The interface is parametrized over the type of Note as well
The controller implementation serves as a wrapper for the music model and allows read only
    access to the model it contains.

The utils contains classes for reading music files and building compositions based on them
We changed the composition builder methods to void methods because we decided we did not need to
    return a new composition builder every time an operation was done on it

The View class contains an interface for views and the views themselves, as well as a few mocks
    for testing
The View interface simply has one which displays the view when it is called
Each View contains a MusicController so they have read only access to music data they need

Finally there is the MusicEditor Class which is in the default package
It serves as both a factory for the views and a main method to start the program
To run the program include the file name as the first argument and one of visual, midi, or console
    To the run the view that you would like

    For Example, if I wanted to view mary-little-lamb.txt in gui form the arguments would be
    mary-little-lamb.txt visual


Changes from Previous Assignment

We had to add things to the Note and Piece classes that were introduced this assignment such as
tempo, volume, and instrument.

Note now has a method to return it's midi pitch value and also to construct a note based on one

We combined out music models so that we have representations for notes in both a list format
and a map format where it is a map from an int beat to a list of notes playing at that beat
