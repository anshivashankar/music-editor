package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Interface for the combined GUI editor
 */
public interface GuiView<K> extends View<K> {

  /**
   * adds a keyboard listener to the GUI
   *
   * @param kl the keyboard listener to be added
   */
  void addKeyListener(KeyListener kl);

  /**
   * gets the note the user inputted into the EditorFrame and closes the Editor Frame
   *
   * @return the note that needs to be edited
   * @throws IllegalArgumentException when the input into the Editor Window is incorrect
   */
  K getEditNote() throws IllegalArgumentException;

  /**
   * opens a new Editor Frame with the given KeyListener
   *
   * @param kl the KeyListener to be given to the Editor Frame
   * @throws IllegalArgumentException if there is already an Editor Frame open
   */
  void openEditWindow(KeyListener kl) throws IllegalArgumentException;

  /**
   * Scrolls the GUI Right.
   */
  void scrollRight();

  /**
   * Scrolls the GUI Left.
   */
  void scrollLeft();
}
