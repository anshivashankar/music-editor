package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyModelImpl;

/**
 * Mock GuiViewFrame class, simply used for testing purposes.
 */
public class MockGuiViewFrame extends GuiViewFrame {
  /**
   * Creates new GuiView
   */
  public MockGuiViewFrame(ReadOnlyModelImpl<Note> piece) {
    super(piece);
  }

  @Override
  public void view() {
    this.setVisible(false);
    this.requestFocus();
  }
}
