package cs3500.music.view;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.controller.MusicController;
import cs3500.music.model.Note;

/**
 * MIDI view of an IMusicModel. Displays it visually, through JFrame.
 */
public class MidiView implements IView {
  private final MusicController<Note> controller;
  private final Synthesizer synth;
  private final Receiver receiver;

  /**
   * Constructor of a MidiView.
   *
   * @param piece of type MusicControllerImpl, given the piece that we would like to test.
   */
  public MidiView(MusicController<Note> piece) {
    Synthesizer synth2 = null;
    Receiver rec = null;
    controller = piece;
    try {
      synth2 = MidiSystem.getSynthesizer();
      rec = synth2.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    receiver = rec;
    synth = synth2;

    try {
      synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Convenience constructor to test MidiView.
   *
   * @param piece  of type MusicControllerImpl, given the piece that we would like to test.
   * @param string of type StringBuilder, so that we can log the calls of start(), open(), close(),
   *               and stop().
   */
  public MidiView(MusicController<Note> piece, StringBuilder string) {
    Synthesizer synth2 = null;
    Receiver rec = null;
    controller = piece;
    try {
      synth2 = new MockSynth(string);
      rec = synth2.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    receiver = rec;
    synth = synth2;

    try {
      synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  public void view() {
    List<Note> notes = controller.getAllNotes();
    for (Note note : notes) {
      try {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
                note.notePlace(), note.getVolume());
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
                note.notePlace(), note.getVolume());
        this.receiver.send(start, note.getStartBeat() * controller.getTempo());
        this.receiver.send(stop, (note.getStartBeat() + note.getDuration())
                * controller.getTempo());
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
    try {
      // divide my 1000 because of the microsecond to millisecond conversion
      Thread.sleep((long) controller.lastBeat() * (long) controller.getTempo() / 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    synth.close();
    this.receiver.close();
  }
}
