package cs3500.music.view;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.controller.MusicController;
import cs3500.music.model.Note;

/**
 * MIDI view of an IMusicModel. Displays it visually, through JFrame.
 */
public class MidiView implements View {
  private final MusicController<Note> controller;
  private final Sequencer seq;
  private final Sequence sequence;

  /**
   * Constructor of a MidiView.
   *
   * @param piece of type MusicControllerImpl, given the piece that we would like to test.
   */
  public MidiView(MusicController<Note> piece) {
    Sequencer seq2 = null;
    Sequence sequence2 = null;
    controller = piece;
    try {
      seq2 = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    seq = seq2;
    try {
      sequence2 = new Sequence(Sequence.PPQ, 1000000 / 2);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    sequence = sequence2;

    try {
      seq.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    piece.setView(this);
  }

  /**
   * Convenience constructor to test MidiView.
   *
   * @param piece  of type MusicControllerImpl, given the piece that we would like to test.
   * @param string of type StringBuilder, so that we can log the calls of start(), open(), close(),
   *               and stop().
   */
  public MidiView(MusicController<Note> piece, StringBuilder string) {
    controller = piece;
    seq = new MockSequencer(string);
    Sequence sequence2 = null;
    try {
      sequence2 = new MockSequence(Sequence.PPQ, 1000000 / 2, string);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    sequence = sequence2;
    try {
      seq.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  public void view() {


    Track track = sequence.createTrack();


    try {
      seq.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }


    List<Note> notes = controller.getAllNotes();
    for (Note note : notes) {
      try {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
                note.notePlace(), note.getVolume());
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
                note.notePlace(), note.getVolume());


        // sequencer
        track.add(new MidiEvent(start, note.getStartBeat() * controller.getTempo()));
        track.add(new MidiEvent(stop, (note.getStartBeat() + note.getDuration())
                * controller.getTempo()));

      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }

    seq.start();

    try {
      // divide my 1000 because of the microsecond to millisecond conversion
      Thread.sleep((long) controller.lastBeat() * (long) controller.getTempo() / 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    seq.stop();
    seq.close();

  }

  @Override
  public void play() {
    seq.start();
  }

  @Override
  public void pause() {
    seq.stop();
  }
}
