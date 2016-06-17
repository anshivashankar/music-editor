package cs3500.music.view;

import java.util.List;

import cs3500.music.model.MusicController;
import cs3500.music.model.Note;

import javax.sound.midi.*;

/**
 * MIDI view of an IMusicModel. Displays it visually, through JFrame.
 */
public class MIDIView implements IView {
  MusicController controller;
  private final Synthesizer synth;
  private final Receiver receiver;

  public static void main(String[] args) {
    MIDIView view = new MIDIView();
    try {
      view.playNote();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  public MIDIView() {
    Synthesizer synth2 = null;
    Receiver rec = null;
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

  public void playNote() throws InvalidMidiDataException {

    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 1, 60, 64);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 1, 60, 64);
    this.receiver.send(start, -1);
    this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.receiver.close(); // Only call this once you're done playing *all* notes

  }

  public void view() {
    List<Note> notes = controller.getAllNotes();
    for(Note note : notes) {
      try {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
                note.notePlace(), note.getVolume());
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
                note.notePlace(), note.getVolume());
        this.receiver.send(start, note.getStartBeat() * controller.getTempo());
        this.receiver.send(stop, (note.getStartBeat() + note.getDuration())
                * controller.getTempo() );

        try {
          Thread.sleep(controller.lastBeat() * controller.getTempo());
        } catch(InterruptedException e) {
          e.printStackTrace();
        }

      }
      catch(InvalidMidiDataException e) {
        //System.out.println("shouldn't come here");
        e.printStackTrace();
      }
      this.receiver.close();
    }
  }
}
