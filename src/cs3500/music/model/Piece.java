package cs3500.music.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Piece of music
 */
public class Piece implements IMusicModel<Note> {

  private final Map<Integer, List<Note>> music;

  public Piece() {
    this.music = new HashMap<Integer, List<Note>>();
  }

  public Piece(List<NoteStartPair<Note>> notes) {
    this();
    this.addAll(notes);
  }

  @Override
  public void add(NoteStartPair<Note> notePair) {
    // checks if an entry for the note's startBeat is already in the Map
    List<Note> startBeatNotes;
    if (this.music.containsKey(notePair.getStartBeat())) {
      startBeatNotes = this.music.get(notePair.getStartBeat());
    } else {
      startBeatNotes = new ArrayList();
      this.music.put(notePair.getStartBeat(), startBeatNotes);
    }
    startBeatNotes.add(notePair.getNote());
  }

  @Override
  public void addAll(List<NoteStartPair<Note>> notes) {
    for (NoteStartPair<Note> n : notes) {
      this.add(n);
    }
  }

  @Override
  public void remove(NoteStartPair<Note> notePair) {
    if (!this.music.get(notePair.getStartBeat()).remove(notePair.getNote())) {
      throw new IllegalArgumentException("Given note not in this Piece");
    }
  }

  @Override
  public IMusicModel<Note> combineSimultaneously(IMusicModel<Note> that) {
    IMusicModel<Note> newPiece = new Piece();

    newPiece.addAll(this.getAllNotes());
    newPiece.addAll(that.getAllNotes());

    return newPiece;
  }

  @Override
  public IMusicModel<Note> combineConsecutively(IMusicModel<Note> that) {
    IMusicModel<Note> newPiece = new Piece();
    newPiece.addAll(this.getAllNotes());

    // adding to the startTime of all notes in the IMusicModel
    int lastBeat = 0;

    for (NoteStartPair<Note> n : this.getAllNotes()) {
      int lastBeatThisNote = n.getStartBeat() + n.getNote().getDuration() - 1;
      if (lastBeatThisNote > lastBeat) {
        lastBeat = lastBeatThisNote;
      }
    }

    List<NoteStartPair<Note>> thatNotes = that.getAllNotes();
    for (int i = 0; i < thatNotes.size(); i++) {
      NoteStartPair<Note> note = thatNotes.get(i);
      thatNotes.set(i, new NoteStartPair<Note>(note.getNote(), note.getStartBeat() + lastBeat + 1)
      );
    }

    newPiece.addAll(thatNotes);

    return newPiece;
  }

  @Override
  public List<NoteStartPair<Note>> getBeatNotes(int beat) {
    List<NoteStartPair<Note>> beatNotes = new ArrayList<NoteStartPair<Note>>();

    for (NoteStartPair<Note> n : this.getAllNotes()) {
      int beatsFromStartBeat = beat - n.getStartBeat();

      // checks if the note plays during the given beat
      if (beatsFromStartBeat >= 0 && beatsFromStartBeat < n.getNote().getDuration()) {
        beatNotes.add(n);
      }
    }

    return beatNotes;
  }

  @Override
  public List<NoteStartPair<Note>> getAllNotes() {
    List<NoteStartPair<Note>> notes = new ArrayList<NoteStartPair<Note>>();

    // adds every note from music into the notes list
    for (Map.Entry<Integer, List<Note>> beat : this.music.entrySet()) {
      for (Note n : beat.getValue()) {
        notes.add(new NoteStartPair<Note>(n, beat.getKey()));
      }
    }

    return notes;
  }

  @Override
  public String showPiece() {
    String piece = "";

    List<NoteStartPair<Note>> notePairs = this.getAllNotes();

    if (notePairs.size() == 0) {
      return "╔╗\n" +
              "╚╝";
    }

    List<Note> notes = new ArrayList<Note>();

    for (NoteStartPair<Note> n : notePairs) {
      notes.add(n.getNote());
    }

    Note maxNote = Collections.max(notes);
    Note minNote = Collections.min(notes);

    int numberOfColumns = maxNote.compareTo(minNote) + 1;

    ArrayList<char[]> beatStrings = new ArrayList<char[]>();

    for (NoteStartPair<Note> n : notePairs) {
      // adds rows to beatStrings until there are enough represent the current note
      while (beatStrings.size() < n.getStartBeat() + n.getNote().getDuration()) {
        char[] toAdd = new char[numberOfColumns];
        Arrays.fill(toAdd, ' ');
        beatStrings.add(toAdd);
      }

      // the column used to represent the note currently being processed
      int column = n.getNote().compareTo(minNote);

      // writes the start character of the note
      beatStrings.get(n.getStartBeat())[column] = 'X';

      // writes the characters following the start character only if the space was already empty
      for (int i = n.getStartBeat() + 1; i < n.getStartBeat() + n.getNote().getDuration(); i++) {
        if (beatStrings.get(i)[column] == ' ') {
          beatStrings.get(i)[column] = '|';
        }
      }
    }

    int numberOfBeats = beatStrings.size();

    int beatNumberLength = Integer.toString(numberOfBeats - 1).length();

    // the first line
    piece += '╔' + this.stringMultiply("=", beatNumberLength + numberOfColumns * 5) + '╗' +
            '\n';

    // the second line: the notes printed
    piece += '║' + this.stringMultiply(" ", beatNumberLength);

    int octave = minNote.getOctave();
    int pitchValue = minNote.getPitch().ordinal();

    for (int i = 0; i < numberOfColumns; i++) {
      String noteToString = new Note(Note.Pitch.values()[pitchValue], octave, 1).toString();

      if (noteToString.length() == 2) {
        piece += "  " + noteToString + " ";
      } else if (noteToString.length() == 3) {
        piece += " " + noteToString + " ";
      } else {
        throw new RuntimeException("Note toString giving wrong size");
      }

      if (pitchValue == Note.Pitch.values().length - 1) {
        octave += 1;
        pitchValue = 0;
      } else {
        pitchValue += 1;
      }
    }

    piece += "║\n";

    // adds all the values of the beats
    for (int i = 0; i < numberOfBeats; i++) {
      piece += '║';

      String number = Integer.toString(i);
      // pads the number string until it is the required number of characters
      while (number.length() < beatNumberLength) {
        number = ' ' + number;
      }

      piece += number;

      for (char c : beatStrings.get(i)) {
        piece += "  " + c + "  ";
      }

      piece += "║\n";
    }

    // the last line
    piece += '╚' + this.stringMultiply("=", beatNumberLength + numberOfColumns * 5) + '╝';

    return piece;
  }

  // returns string that repeats the given string s factor number times
  private String stringMultiply(String s, int factor) {
    String multiplied = "";

    for (int i = 0; i < factor; i++) {
      multiplied += s;
    }

    return multiplied;
  }


}
