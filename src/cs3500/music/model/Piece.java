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

  private final Map<Integer, ArrayList<Note>> notesMap;

  private final ArrayList<Note> notesList;

  public Piece() {
    this.notesMap = new HashMap<Integer, ArrayList<Note>>();
    this.notesList = new ArrayList<Note>();
  }

  public Piece(List<Note> notes) {
    this();
    this.addAll(notes);
  }

  // TODO remove this test comment
  @Override
  public void add(Note note) {
    // adding note to notesList
    this.notesList.add(note);

    // adding note to notesMap
    // adds it to every beat the note is played
    for (int i = note.getStartBeat(); i < note.getStartBeat() + note.getDuration(); i++) {
      // checks if an entry for the current beat is already in the Map
      ArrayList<Note> beatNotes;
      if (this.notesMap.containsKey(i)) {
        beatNotes = this.notesMap.get(i);
      } else {
        beatNotes = new ArrayList<Note>();
        this.notesMap.put(i, beatNotes);
      }
      beatNotes.add(note);
    }
  }

  @Override
  public void addAll(List<Note> notes) {
    for (Note n : notes) {
      this.add(n);
    }
  }

  @Override
  public void remove(Note note) {
    if (this.notesList.remove(note)) {
      for (int i = note.getStartBeat(); i < note.getStartBeat() + note.getDuration(); i++) {
        this.notesMap.get(i).remove(note);
      }
    } else {
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

    // add notes from this piece
    newPiece.addAll(this.getAllNotes());

    // add notes from the other piece after incrementing their startTimes by the lastBeat played
    // in this song
    int startBeat = this.lastBeat() + 1;

    for (Note n : that.getAllNotes()) {
      newPiece.add(new Note(n.getPitch(), n.getOctave(),
              n.getDuration(), n.getStartBeat() + startBeat));
    }

    return newPiece;
  }

  @Override
  public List<Note> getBeatNotes(int beat) {
    if (this.notesMap.containsKey(beat)) {
      // the following cast should never fail because it is cloning an ArrayList<Note> from
      // the notesMap
      return (ArrayList<Note>) (this.notesMap.get(beat).clone());
    } else {
      return new ArrayList<Note>();
    }
  }

  @Override
  public List<Note> getAllNotes() {
    // the following cast should never fail because it is cloning an ArrayList<Note> as notesList
    // is defined
    return (ArrayList<Note>) (this.notesList.clone());
  }

  @Override
  public String showPiece() {
    if (this.notesList.size() == 0) {
      return "╔╗\n" +
              "╚╝";
    }

    StringBuilder pieceString = new StringBuilder();

    Note maxNote = Collections.max(this.notesList);
    Note minNote = Collections.min(this.notesList);

    int numberOfColumns = maxNote.compareTo(minNote) + 1;
    int lastBeat = this.lastBeat();
    int beatNumberLength = Integer.toString(lastBeat - 1).length();

    // the first line
    pieceString.append('╔');
    pieceString.append(this.charMultiply('=', beatNumberLength + numberOfColumns * 5));
    pieceString.append("╗\n");

    // the second line: the notes printed
    pieceString.append('║');
    pieceString.append(this.charMultiply(' ', beatNumberLength));

    for (int i = minNote.notePlace(); i <= maxNote.notePlace(); i++) {
      String noteToString = new Note(i, 1, 0).toString();

      switch (noteToString.length()) {
        case 2:
          pieceString.append("  ");
          pieceString.append(noteToString);
          pieceString.append(" ");
          break;

        case 3:
          pieceString.append(" ");
          pieceString.append(noteToString);
          pieceString.append(" ");
          break;

        case 4:
          pieceString.append(" ");
          pieceString.append(noteToString);
          break;

        default:
          throw new RuntimeException("Note toString giving wrong size");
      }
    }

    pieceString.append("║\n");

    // adds all the values of the beats
    for (int i = 0; i <= lastBeat; i++) {
      pieceString.append('║');

      String number = String.format("%" + beatNumberLength + "d", i);
      pieceString.append(number);

      for (char c : this.processBeat(i, minNote.notePlace(), numberOfColumns)) {
        pieceString.append("  ");
        pieceString.append(c);
        pieceString.append("  ");
      }

      pieceString.append("║\n");
    }

    // the last line
    pieceString.append('╚');
    pieceString.append(this.charMultiply('=', beatNumberLength + numberOfColumns * 5));
    pieceString.append('╝');

    return pieceString.toString();
  }

  // returns string that repeats the given string s factor number times
  private char[] charMultiply(char c, int factor) {
    char[] multiplied = new char[factor];
    Arrays.fill(multiplied, c);

    return multiplied;
  }

  private char[] processBeat(int beat, int minNotePlace, int numberOfColumns) {
    char[] beatValues = new char[numberOfColumns];
    Arrays.fill(beatValues, ' ');

    if (this.notesMap.containsKey(beat)) {
      for (Note n : this.notesMap.get(beat)) {
        // the column that this note gets placed into
        int column = n.notePlace() - minNotePlace;

        if (n.getStartBeat() == beat) {
          beatValues[column] = 'X';
        } else {
          if (beatValues[column] == ' ') {
            beatValues[column] = '|';
          }
        }
      }
    }

    return beatValues;
  }

  @Override
  public int lastBeat() {
    int lastBeat = 0;

    for (Note n : this.notesList) {
      int noteLastBeat = n.getStartBeat() + n.getDuration() - 1;
      if (noteLastBeat > lastBeat) {
        lastBeat = noteLastBeat;
      }
    }

    return lastBeat;
  }

}
