package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.IMusicModel;

/**
 * The Model Controller, only allows the View(s) to have read-only access.
 */
public class MusicControllerImpl<K> implements MusicController<K> {
  private IMusicModel<K> piece;

  /**
   * The MusicControllerImpl constructor, specifies that the IMusicModel being played is of type
   * Piece.
   */
  public MusicControllerImpl(IMusicModel<K> piece) {
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
