package cs3500.music.controller;

import java.util.List;

import javax.swing.plaf.basic.BasicComboBoxUI;

import cs3500.music.model.IMusicModel;
import cs3500.music.view.GuiView;
import cs3500.music.view.View;

/**
 * The Model Controller, handles the editing and reading of notes and tempo.
 */
public class MusicControllerImpl<K> implements MusicController<K> {
  // these fields should all be final at the end
  // the setView is not required
  private IMusicModel<K> piece;
  private View<K> view;

  /**
   * The MusicControllerImpl constructor, specifies that the IMusicModel being played is of type
   * Piece.
   */
  public MusicControllerImpl(IMusicModel<K> piece, View<K> view) {
    this.piece = piece;
    this.view = view;
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
  public void view() {
    view.view();
  }

}
