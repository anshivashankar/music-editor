package cs3500.music.view;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MusicController;
import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyModelImpl;

/**
 * A Frame that houses the visual component of a song
 */
public class GuiViewFrame extends JFrame implements GuiView<Note> {

  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(ReadOnlyModelImpl<Note> piece) {
    this.setLayout(new BorderLayout());
    this.displayPanel = new ConcreteGuiViewPanel(piece);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(new JScrollPane(displayPanel));
    this.pack();
  }

  @Override
  public void view() {
    this.setVisible(true);
  }

  // TODO: rest of these need to be implemented
  @Override
  public void togglePausePlay() {

  }

  @Override
  public void playAtTime(long sec) {
    // Make sure to place the red 'line' at this point, it is given in microseconds.
  }

  @Override
  public void moveToBeginning() {

  }

  @Override
  public void moveToEnd() {

  }

  @Override
  public Dimension getPreferredSize() {
    return displayPanel.getPreferredSize();
  }

  @Override
  public Note getEditNote() throws IllegalArgumentException {
    return null;
  }

  @Override
  public void openEditWindow(KeyListener kl) throws IllegalArgumentException {

  }
}
