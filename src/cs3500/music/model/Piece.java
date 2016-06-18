package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Piece of music
 */
public class Piece implements IMusicModel<Note> {

  private final Map<Integer, ArrayList<Note>> notesMap;

  private final ArrayList<Note> notesList;
  private int tempo;

  public Piece() {
    this.notesMap = new HashMap<Integer, ArrayList<Note>>();
    this.notesList = new ArrayList<Note>();
    this.tempo = 5;
  }

  public Piece(int tempo) {
    this();
    if (tempo <= 0) {
      throw new IllegalArgumentException("Tempo cannot be negative");
    }
    this.tempo = tempo;
  }

  public Piece(List<Note> notes) {
    this();
    this.addAll(notes);
  }

  public Piece(List<Note> notes, int tempo) {
    this(tempo);
    this.addAll(notes);
  }

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
    IMusicModel<Note> newPiece = new Piece(this.tempo);

    newPiece.addAll(this.getAllNotes());
    newPiece.addAll(that.getAllNotes());

    return newPiece;
  }

  @Override
  public IMusicModel<Note> combineConsecutively(IMusicModel<Note> that) {
    IMusicModel<Note> newPiece = new Piece(this.tempo);

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
      return (List<Note>) (this.notesMap.get(beat).clone());
    } else {
      return new ArrayList<Note>();
    }
  }

  @Override
  public List<Note> getAllNotes() {
    // the following cast should never fail because it is cloning an ArrayList<Note> as notesList
    // is defined
    return (List<Note>) (this.notesList.clone());
  }


  @Override
  public int lastBeat() {
    int lastBeat = 0;

    for (Note n : this.notesList) {
      int noteLastBeat = n.getStartBeat() + n.getDuration();
      if (noteLastBeat > lastBeat) {
        lastBeat = noteLastBeat;
      }
    }

    return lastBeat;
  }

  @Override
  public int getTempo() {
    return tempo;
  }

  @Override
  public void setTempo(int t) {
    if (t <= 0) {
      throw new IllegalArgumentException("Tempo cannot be negative");
    }
    this.tempo = t;
  }



}
