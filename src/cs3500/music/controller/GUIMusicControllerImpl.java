package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.util.List;

import cs3500.music.model.IMusicModel;
import cs3500.music.view.GuiView;

/**
 * Represents a controller for a GUI Music Editor
 */
public class GUIMusicControllerImpl<K> implements MusicController<K> {
  private final IMusicModel<K> piece;
  private final GuiView<K> view;

  public GUIMusicControllerImpl(IMusicModel<K> model, GuiView<K> view) {
    this.piece = model;
    this.view = view;
    this.view.addKeyListener(getNormalFrameKeyHandler());
    this.view.view();
  }

  private KeyboardHandler getEditFrameKeyHandler() {
    // adds the key bindings to the KeyBoardHandlers
    KeyboardHandler kh = new KeyboardHandler();

    kh.addKeyPressedRunnable(KeyEvent.VK_ENTER, () -> {
      K note;
      try {
        note = view.getEditNote();
      } catch (IllegalArgumentException e) {
        return;
      }

      piece.add(note);
      view.closeEditWindow();
    });

    kh.addKeyPressedRunnable(KeyEvent.VK_DELETE, () -> {
      K note;
      try {
        note = view.getEditNote();
      } catch (IllegalArgumentException e) {
        return;
      }

      try {
        piece.remove(note);
      } catch (IllegalArgumentException ignored) {}

      view.closeEditWindow();
    });

    return kh;
  }

  private KeyboardHandler getNormalFrameKeyHandler() {
    // adds the key bindings to the KeyBoardHandlers
    KeyboardHandler kh = new KeyboardHandler();

    kh.addKeyPressedRunnable(KeyEvent.VK_N, () -> {
      view.openEditWindow(getEditFrameKeyHandler());
    });

    kh.addKeyPressedRunnable(KeyEvent.VK_SPACE, () -> {
      view.togglePausePlay();
    });

    kh.addKeyPressedRunnable(KeyEvent.VK_HOME, () -> {
      view.moveToBeginning();
    });

    kh.addKeyPressedRunnable(KeyEvent.VK_END, () -> {
      view.moveToEnd();
    });

    kh.addKeyPressedRunnable(KeyEvent.VK_RIGHT, () -> {
      view.scrollRight();
    });

    kh.addKeyPressedRunnable(KeyEvent.VK_LEFT, () -> {
      view.scrollLeft();
    });

    kh.addKeyPressedRunnable(KeyEvent.VK_DOWN, () -> {
      view.scrollDown();
    });

    kh.addKeyPressedRunnable(KeyEvent.VK_UP, () -> {
      view.scrollUp();
    });

    return kh;
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
