package cs3500.music.model;

import java.util.List;

/**
 * interface for a model that represents a piece of music. Let's user write music a note at a time,
 * edit notes, and combine two pieces of music to play simultaneously or consecutively.
 * Parametrized over the type of Note that the user wishes to use
 */
public interface IMusicModel<K> {

  /**
   * adds a note to the piece of music
   *
   * @param k the note to be added to the piece
   */
  void add(NoteStartPair<K> k);

  /**
   * adds a list of notes to the Piece
   */
  void addAll(List<NoteStartPair<K>> notes);

  /**
   * removes a note from the piece
   *
   * @param k the note to be removed
   */
  void remove(NoteStartPair<K> k);

  /**
   * returns a new music model that combines this piece and the other piece with simultaneous
   * beats
   *
   * @param other the other piece of music
   * @return a piece of music that represents the two pieces of music combined simultaneously
   * @throws IllegalArgumentException if the given note is Note in the Music Model
   */
  IMusicModel<K> combineSimultaneously(IMusicModel<K> other);

  /**
   * returns a new music model that combines this piece and the other piece with consecutive beats,
   * the piece's notes come first
   *
   * @param other the other piece of music
   * @return a piece of music that represents the two pieces of music combined consecutively
   */
  IMusicModel<K> combineConsecutively(IMusicModel<K> other);


  /**
   * returns a list of NoteStartPairs that are represented in the piece at a certain beat
   *
   * @return a list of NoteStartPairs that are represented in the piece at a certain beat
   */
  List<NoteStartPair<K>> getBeatNotes(int beat);

  /**
   * returns a list of NoteStartPairs that are represented in the Piece
   *
   * @return a list of NoteStartPairs that are represented in the Piece
   */
  List<NoteStartPair<K>> getAllNotes();

  /**
   * returns a String that represents the piece of music in and only shows the min to max notes
   * required
   */
  String showPiece();
}
