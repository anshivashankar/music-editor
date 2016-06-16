package cs3500.music.model;

import java.util.List;

/**
 * The Model Controller, only allows the View(s) to have read-only access.
 */
public class MusicController {
  IMusicModel<Note> piece;

  /**
   * The MusicController constructor, specifies that the IMusicModel being played is
   * of type Piece.
   */
  public MusicController() {
    piece = new Piece();
    piece.add(new Note(Note.Pitch.D, Note.Octave.Four, 3, 2));
  }

  /**
   * Given a beat number, will return the Notes that appear at that specific beat.
   * @param beat an int of beat within the IMusicModel.
   * @return the Notes that fall within the given beat.
   */
  public List<Note> getBeatNotes(int beat) {
    return piece.getBeatNotes(beat);
  }

  /**
   * Gets all of the notes of the IMusicModel.
   * @return all of the notes of the IMusicModel.
   */
  public List<Note> getAllNotes() {
    return piece.getAllNotes();
  }

  /**
   * Gets the last beat of the given IMusicModel.
   * Important for determining when to end the visual representation.
   * @return an int containing the last beat of the IMusicModel.
   */
  public int lastBeat() {
    return piece.lastBeat();
  }

}
