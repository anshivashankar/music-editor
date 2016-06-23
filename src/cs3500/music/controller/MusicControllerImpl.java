package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.IMusicModel;
import cs3500.music.view.IView;

/**
 * The Model Controller, only allows the View(s) to have read-only access.
 */
public class MusicControllerImpl<K> implements MusicController<K> {
  private IMusicModel<K> piece;
  private IView view;

  /**
   * The MusicControllerImpl constructor, specifies that the IMusicModel being played is of type
   * Piece.
   */
  public MusicControllerImpl(IMusicModel<K> piece) {
    this.piece = piece;
    view = null;
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

  @Override
  public void add(K k) {
    piece.add(k);
  }

  @Override
  public void remove(K k) {
    try {
      piece.remove(k);
    }
    catch(IllegalArgumentException e) {
      e.printStackTrace();
      System.err.println("Note to be removed not in Model");
    }
  }

  @Override
  public void play() {
    view.play();
  }

  @Override
  public void pause() {
    view.pause();
  }

  @Override
  public void setView(IView view) {
    this.view = view;
  }


}
