package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Interface for the combined GUI editor
 */
public interface GuiView extends View {

  /**
   * adds a mouse listener to the GUI
   *
   * @param ml the mouse listener to be added
   */
  void addMouseListener(MouseListener ml);

  /**
   * adds a keyboard listener to the GUI
   *
   * @param kl the keyboard listener to be added
   */
  void addKeyListener(KeyListener kl);
}
