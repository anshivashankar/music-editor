package cs3500.music.view;


import java.awt.FlowLayout;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * A window for selecting a new Note
 */
public class EditorFrame extends JFrame {
  private final JTextField pitch;
  private final JTextField startBeat;
  private final JTextField duration;

  public EditorFrame() {

    this.setLayout(new FlowLayout());
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setTitle("Add or Remove a Note");

    this.pitch = new JTextField(3);
    this.startBeat = new JTextField(5);
    this.duration = new JTextField(5);

    this.add(new JLabel("Pitch: "));
    this.add(this.pitch);

    this.add(new JLabel("StartBeat: "));
    this.add(this.startBeat);

    this.add(new JLabel("Duration: "));
    this.add(this.duration);

    this.add(new JLabel("Press a to add a note and r to remove a note"));

    this.pack();
  }

  public void addKeyListenerToFields(KeyListener kl) {
    this.pitch.addKeyListener(kl);
    this.startBeat.addKeyListener(kl);
    this.duration.addKeyListener(kl);
  }

  // for testing, delete later
  public static void main(String[] args) {
    JFrame frame = new EditorFrame();
    frame.setVisible(true);
  }
}
