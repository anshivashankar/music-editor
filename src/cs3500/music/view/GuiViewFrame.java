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

  private final JScrollPane scrollPane;

  private final EditorFrame editWindow;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(ReadOnlyModelImpl<Note> piece) {
    this.setLayout(new BorderLayout());

    this.displayPanel = new ConcreteGuiViewPanel(piece);

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.scrollPane = new JScrollPane(displayPanel);
    this.getContentPane().add(scrollPane);
    this.pack();

    this.editWindow = new EditorFrame();
  }

  @Override
  public void view() {
    this.setVisible(true);
    this.requestFocus();
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
    JScrollBar horizontal = this.scrollPane.getHorizontalScrollBar();
    horizontal.setValue(0);

  }

  @Override
  public void moveToEnd() {
    JScrollBar horizontal = this.scrollPane.getHorizontalScrollBar();
    horizontal.setValue(horizontal.getMaximum());
  }

  @Override
  public Dimension getPreferredSize() {
    return displayPanel.getPreferredSize();
  }

  @Override
  public Note getEditNote() throws IllegalArgumentException {
    return this.editWindow.getNote();
  }

  @Override
  public void addEditWindowKeyListener(KeyListener kl) {
    this.editWindow.addKeyListenerToFields(kl);
    this.editWindow.addKeyListener(kl);
  }

  @Override
  public void openEditWindow() throws IllegalArgumentException {
    this.editWindow.setVisible(true);
    this.setFocusable(false);
    editWindow.requestFocus();
  }

  @Override
  public void closeEditWindow() {
    this.editWindow.setVisible(false);
    this.setFocusable(true);
    this.editWindow.clearFields();
    this.requestFocus();
  }

  @Override
  public void scrollRight() {
    JScrollBar horizontal = this.scrollPane.getHorizontalScrollBar();
    horizontal.setValue(horizontal.getValue() + 10);
  }

  @Override
  public void scrollLeft() {
    JScrollBar horizontal = this.scrollPane.getHorizontalScrollBar();
    horizontal.setValue(horizontal.getValue() - 10);
  }

  @Override
  public void scrollUp() {
    JScrollBar vertical = this.scrollPane.getVerticalScrollBar();
    vertical.setValue(vertical.getValue() - 10);
  }

  @Override
  public void scrollDown() {
    JScrollBar vertical = this.scrollPane.getVerticalScrollBar();
    vertical.setValue(vertical.getValue() + 10);
  }

  @Override
  public void update() {
    this.repaint();
    // TODO: Not required but we can make scrollbars when updating instead of letting is happen by itself
  }
}
