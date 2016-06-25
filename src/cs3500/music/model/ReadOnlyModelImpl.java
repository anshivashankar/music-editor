package cs3500.music.model;

import java.util.List;

/**
 * The Read-Only Model Implementation, only allows the View(s) to have read-only access.
 */
public class ReadOnlyModelImpl<K> implements ReadOnlyModel {

  private IMusicModel<K> piece;

  /**
   * The ReadOnlyModelImpl constructor, specifies that the IMusicModel being played is of type
   * Piece.
   */
  public ReadOnlyModelImpl(IMusicModel<K> piece) {
    this.piece = piece;
  }

  @Override
  public List<K> getBeatNotes(int beat) {
    return piece.getBeatNotes(beat);
  }

  @Override
  public List<K> getAllNotes() {
    return piece.getAllNotes();
  }

  @Override
  public int lastBeat() {
    return piece.lastBeat();
  }

  @Override
  public int getTempo() {
    return piece.getTempo();
  }
}
