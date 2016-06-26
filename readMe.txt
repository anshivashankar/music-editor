
Controls for the Combined View:

Home - Jump to beginning
End - Jump to end
E - Open add/deletion window
    Note: The editor  window takes a midi pitch number (integer).
    Note: To add notes, press enter after all the fields are filled in. To delete press delete
    Note: If a note is added that is higher/lower pitch than the current highest/lowest pitch,
    the window will resize.
    In order to exit the window, please press 'Escape' on your keyboard.
Q - Exits the View.
Spacebar - Toggle pause/play
Up/Down/Left/Right arrow keys - Scrolling
The mouse does not do anything.

Note: the Combined View does NOT start automatically. Please hit 'space' to start playing.

Our Design

We have packages for the model, the view, the controller, and some utilities.

Our model keeps track of the music itself by keeping track of notes and the tempo of the song.
The Note class keeps track of info about all of the notes.
The model implements an interface that is parametrized by the kind of Note.
We did not change anything in our model. It is all the same as last assignment.
We added a Read-Only Model in our model class, however. We did not change anything in our model,
simply created a model that was read-only to allow for our controller to have both
read/write access.

The Controller has a an interface and two implementations.
The interface is parametrized over the type of Note.
The controller implementation serves as a wrapper for the music model and allows read and write
access. We changed our controller from read-only to have read/write access. This is because we
need keyboard functions and mouse functions for the composite view.

The utils contains classes for reading music files and building compositions based on them.
We changed the composition builder methods to void methods because we decided we did not need
to return a new composition builder every time an operation was done on it.
We did not change anything in the utils class from last assignment.

The View class contains an interface for views and the views themselves, as well as a few mocks
for testing.
From last time, we added a new Combined View, as well as a mock for GuiViewFrame so that we can
test Combined View and GuiView.
Each View contains a ReadOnlyModel so they have read only access to music data they need.

Finally there is the MusicEditor Class which is in the default package
It serves as both a factory for the views and a main method to start the program
To run the program include the file name as the first argument and one of visual, midi, or console
    To the run the view that you would like

    For Example, if I wanted to view mary-little-lamb.txt in gui form the arguments would be
    mary-little-lamb.txt visual

    Additionally, if I wanted to view mystery-2.txt in combined visual and midi form, the arguments
    would be: mystery-2.txt combined

Changes from Previous Assignment

We did not have to edit the IMusicModel interface or its implementation. We changed all of the
views to accept a ReadOnlyModel because it must continue to have read-only access. We changed our
controller to have read/write access to the model and the view. The controller now makes all of
the changes to the view and model as necessary. We changed the MidiView to have a sequencer.
This means we have a MockSequqnce and MockSequencer class instead of a MockSequencer and
MockReciever class.