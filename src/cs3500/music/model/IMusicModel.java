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
  void add(K k);

  /**
   * adds a list of notes to the Piece
   */
  void addAll(List<K> notes);

  /**
   * removes a note from the piece
   *
   * @param k the note to be removed
   */
  void remove(K k);

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
   * this piece's notes come first
   *
   * @param other the other piece of music
   * @return a piece of music that represents the two pieces of music combined consecutively
   */
  IMusicModel<K> combineConsecutively(IMusicModel<K> other);

  /**
   * returns a list of notes that are represented in the piece at a certain beat
   *
   * @return a list of notes that are represented in the piece at a certain beat
   */
  List<K> getBeatNotes(int beat);

  /**
   * returns a list of notes that are represented in the piece
   *
   * @return a list of notes that are represented in the piece
   */
  List<K> getAllNotes();


  /**
   * returns the last beat that is played in the piece of music
   *
   * @return the last beat that is played in the piece of music
   */
  int lastBeat();

  /**
   * returns the tempo of the IMusicModel.
   *
   * @return tempo of the IMusicModel
   */
  int getTempo();

  /**
   * Changes the tempo in a IMusicModel to the value specified, in microseconds
   *
   * @param time a long that specifies how long each beat will last for.
   */
  void setTempo(int time);
}
