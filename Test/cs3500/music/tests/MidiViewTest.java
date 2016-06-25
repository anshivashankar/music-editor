package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.controller.MusicControllerImpl;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.ReadOnlyModelImpl;
import cs3500.music.view.MidiView;

import static org.junit.Assert.assertEquals;

/**
 * The test for MidiView to see if it is working properly.
 */
public class MidiViewTest {
  @Test
  public void togglePausePlay() {
    IMusicModel<Note> piece = new Piece();
    StringBuilder build = new StringBuilder();
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.C, Note.Octave.Eight, 5, 0, 60, 0));
    ReadOnlyModelImpl<Note> controller = new ReadOnlyModelImpl<Note>(piece);
    MidiView view = new MidiView(controller, build);
    view.view();
    view.togglePausePlay();
    assertEquals(build.toString(), "midi opened\n" +
            "set Sequence\n" +
            "midi started\n" +
            "accessed track\n" +
            "[-112, 110, 60] 0\n" +
            "[-112, 110, 60] 0\n" +
            "[-112, 108, 60] 0\n" +
            "[-128, 110, 60] 25\n" +
            "[-128, 110, 60] 25\n" +
            "[-128, 108, 60] 25\n" +
            "[-1, 47, 0] 25\n");
  }

  @Test
  public void playAtTime() {
    IMusicModel<Note> piece = new Piece();
    StringBuilder build = new StringBuilder();
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.C, Note.Octave.Eight, 5, 0, 60, 0));
    ReadOnlyModelImpl<Note> controller = new ReadOnlyModelImpl<Note>(piece);
    MidiView view = new MidiView(controller, build);
    view.view();
    view.togglePausePlay();
    assertEquals(build.toString(), "midi opened\n" +
            "set Sequence\n" +
            "midi started\n" +
            "accessed track\n" +
            "[-112, 110, 60] 0\n" +
            "[-112, 110, 60] 0\n" +
            "[-112, 108, 60] 0\n" +
            "[-128, 110, 60] 25\n" +
            "[-128, 110, 60] 25\n" +
            "[-128, 108, 60] 25\n" +
            "[-1, 47, 0] 25\n");
  }

  @Test
  public void moveToBeginning() {
    IMusicModel<Note> piece = new Piece();
    StringBuilder build = new StringBuilder();
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.C, Note.Octave.Eight, 5, 0, 60, 0));
    ReadOnlyModelImpl<Note> controller = new ReadOnlyModelImpl<Note>(piece);
    MidiView view = new MidiView(controller, build);
    view.view();
    view.moveToEnd();
    view.moveToBeginning();
    //view.togglePausePlay();
    assertEquals(view.getTime(), 0);
  }

  @Test
  public void moveToEnd() {
    IMusicModel<Note> piece = new Piece();
    StringBuilder build = new StringBuilder();
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.C, Note.Octave.Eight, 5, 0, 60, 0));
    ReadOnlyModelImpl<Note> controller = new ReadOnlyModelImpl<Note>(piece);
    MidiView view = new MidiView(controller, build);
    view.view();
    view.moveToBeginning();
    view.moveToEnd();
    assertEquals(view.getTime(), 25);
  }

  @Test
  public void update() {

  }

  @Test
  public void getTime() {
    IMusicModel<Note> piece = new Piece();
    StringBuilder build = new StringBuilder();
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.C, Note.Octave.Eight, 5, 0, 60, 0));
    ReadOnlyModelImpl<Note> controller = new ReadOnlyModelImpl<Note>(piece);
    MidiView view = new MidiView(controller, build);
    view.view();
    view.moveToBeginning();
    view.moveToEnd();
    assertEquals(view.getTime(), 25);
    view.moveToBeginning();
    assertEquals(view.getTime(), 0);

    piece = new Piece();
    build = new StringBuilder();

    controller = new ReadOnlyModelImpl<Note>(piece);
    view = new MidiView(controller, build);
    view.view();
    assertEquals(view.getTime(), 0);
  }

  @Test
  public void testView() {
    // test with multiple of the same note
    IMusicModel<Note> piece = new Piece();
    StringBuilder build = new StringBuilder();
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.C, Note.Octave.Eight, 5, 0, 60, 0));
    ReadOnlyModelImpl<Note> controller = new ReadOnlyModelImpl<Note>(piece);
    MidiView view = new MidiView(controller, build);
    view.view();
    view.playAtTime(-1);
    assertEquals(build.toString(), "midi opened\n" +
            "set Sequence\n" +
            "midi started\n" +
            "accessed track\n" +
            "[-112, 110, 60] 0\n" +
            "[-112, 110, 60] 0\n" +
            "[-112, 108, 60] 0\n" +
            "[-128, 110, 60] 25\n" +
            "[-128, 110, 60] 25\n" +
            "[-128, 108, 60] 25\n" +
            "[-1, 47, 0] 25\n");

    // test with no notes
    piece = new Piece();
    build = new StringBuilder();


    controller = new ReadOnlyModelImpl<Note>(piece);
    view = new MidiView(controller, build);
    view.view();
    view.playAtTime(-1);
    assertEquals(build.toString(), "midi opened\n" +
            "set Sequence\n" +
            "midi started\n" +
            "accessed track\n" +
            "[-1, 47, 0] 0\n");

    // test with a singular note
    piece = new Piece();
    build = new StringBuilder();

    piece.add(new Note(60, 5, 5, 60, 1));

    controller = new ReadOnlyModelImpl<Note>(piece);
    view = new MidiView(controller, build);
    view.view();
    view.playAtTime(-1);
    assertEquals(build.toString(), "midi opened\n" +
            "set Sequence\n" +
            "midi started\n" +
            "accessed track\n" +
            "[-111, 60, 60] 25\n" +
            "[-127, 60, 60] 50\n" +
            "[-1, 47, 0] 50\n");

    // test with a chord of notes
    piece = new Piece();
    build = new StringBuilder();

    piece.add(new Note(60, 5, 5, 60, 1));
    piece.add(new Note(65, 5, 5, 60, 1));
    piece.add(new Note(69, 5, 5, 60, 1));

    controller = new ReadOnlyModelImpl<Note>(piece);
    view = new MidiView(controller, build);
    view.view();
    view.playAtTime(-1);
    assertEquals(build.toString(), "midi opened\n" +
            "set Sequence\n" +
            "midi started\n" +
            "accessed track\n" +
            "[-111, 60, 60] 25\n" +
            "[-111, 65, 60] 25\n" +
            "[-111, 69, 60] 25\n" +
            "[-127, 60, 60] 50\n" +
            "[-127, 65, 60] 50\n" +
            "[-127, 69, 60] 50\n" +
            "[-1, 47, 0] 50\n");


    // test with consecutive notes, and different kinds of note constructors
    piece = new Piece();
    build = new StringBuilder();

    piece.add(new Note(60, 5, 5, 60, 1));
    piece.add(new Note(65, 5, 5, 60, 1));
    piece.add(new Note(69, 5, 5, 60, 1));

    piece.add(new Note(Note.Pitch.C_SHARP, Note.Octave.Eight, 10, 10, 20, 0));

    piece.add(new Note(60, 10, 20, 60, 1));
    piece.add(new Note(65, 10, 20, 60, 1));
    piece.add(new Note(69, 10, 20, 60, 1));

    controller = new ReadOnlyModelImpl<Note>(piece);
    view = new MidiView(controller, build);
    view.view();
    view.playAtTime(-1);
    assertEquals(build.toString(), "midi opened\n" +
            "set Sequence\n" +
            "midi started\n" +
            "accessed track\n" +
            "[-111, 60, 60] 25\n" +
            "[-111, 65, 60] 25\n" +
            "[-111, 69, 60] 25\n" +
            "[-127, 60, 60] 50\n" +
            "[-127, 65, 60] 50\n" +
            "[-127, 69, 60] 50\n" +
            "[-112, 109, 20] 50\n" +
            "[-128, 109, 20] 100\n" +
            "[-111, 60, 60] 100\n" +
            "[-111, 65, 60] 100\n" +
            "[-111, 69, 60] 100\n" +
            "[-127, 60, 60] 150\n" +
            "[-127, 65, 60] 150\n" +
            "[-127, 69, 60] 150\n" +
            "[-1, 47, 0] 150\n");
  }
}
