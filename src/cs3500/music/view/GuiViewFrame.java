package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

import cs3500.music.controller.MusicController;
import cs3500.music.model.Note;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IView {

  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(MusicController<Note> piece) {
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

  @Override
  public Dimension getPreferredSize() {
    return displayPanel.getPreferredSize();
  }
}
