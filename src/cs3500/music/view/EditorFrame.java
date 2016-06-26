package cs3500.music.view;


import java.awt.FlowLayout;
import java.awt.event.KeyListener;

import javax.swing.*;

import cs3500.music.model.Note;

/**
 * A window for selecting a new Note
 */
public class EditorFrame extends JFrame {
  private final JTextField pitch;
  private final JTextField startBeat;
  private final JTextField duration;
  private final JTextField instrument;
  private final JTextField volume;


  /**
   * The constructor for our EditorFrame, which allows the user to select a new Note. Instantiates
   * the variables above.
   */
  protected EditorFrame() {

    this.setLayout(new FlowLayout());
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    this.setTitle("Add or Remove a Note");

    this.pitch = new JTextField(3);
    this.startBeat = new JTextField(5);
    this.duration = new JTextField(5);
    this.instrument = new JTextField(2);
    this.volume = new JTextField(3);

    this.add(new JLabel("Pitch: "));
    this.add(this.pitch);

    this.add(new JLabel("StartBeat: "));
    this.add(this.startBeat);

    this.add(new JLabel("Duration: "));
    this.add(this.duration);

    this.add(new JLabel("Volume: "));
    this.add(this.volume);

    this.add(new JLabel("Instrument: "));
    this.add(this.instrument);

    this.add(new JLabel("Press enter to add a note and delete to remove a note"));

    this.pack();
  }

  protected void addKeyListenerToFields(KeyListener kl) {
    this.pitch.addKeyListener(kl);
    this.startBeat.addKeyListener(kl);
    this.duration.addKeyListener(kl);
    this.volume.addKeyListener(kl);
    this.instrument.addKeyListener(kl);
    this.addKeyListener(kl);
  }

  /**
   * Gets the Note.
   *
   * @return of type Note.
   */
  public Note getNote() {
    Note n = null;
    try {
      n = new Note(Integer.parseInt(this.pitch.getText()),
              Integer.parseInt(this.duration.getText()),
              Integer.parseInt(this.startBeat.getText()),
              Integer.parseInt(this.volume.getText()),
              Integer.parseInt(this.instrument.getText()) - 1);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Bad Edit Window Inputs");
    }

    return n;
  }

  /**
   * Clears the fields after an input has been received.
   */
  protected void clearFields() {
    this.pitch.setText("");
    this.startBeat.setText("");
    this.duration.setText("");
    this.volume.setText("");
    this.instrument.setText("");
  }
}
