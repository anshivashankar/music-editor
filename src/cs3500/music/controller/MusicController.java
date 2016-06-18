package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.Note;

/**
 * A Controller for an IMusicModel.  Parametrized over the type of Note
 */
public interface MusicController<K> {

  /**
   * Given a beat number, will return the Notes that appear at that specific beat.
   *
   * @param beat an int of beat within the IMusicModel.
   * @return the Notes that fall within the given beat.
   */
  List<K> getBeatNotes(int beat);

  /**
   * Gets all of the notes of the IMusicModel.
   *
   * @return all of the notes of the IMusicModel.
   */
  List<K> getAllNotes();

  /**
   * Gets the last beat of the given IMusicModel. Important for determining when to end the visual
   * representation.
   *
   * @return an int containing the last beat of the IMusicModel.
   */
  int lastBeat();

  /**
   * Gets the tempo from the given IMusicModel.
   * @return the tempo from the given IMusicModel
   */
  int getTempo();
}
