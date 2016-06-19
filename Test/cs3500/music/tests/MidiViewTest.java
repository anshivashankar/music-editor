package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.controller.MusicControllerImpl;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.view.MidiView;

import static org.junit.Assert.assertEquals;

/**
 * The test for MidiView to see if it is working properly.
 */
public class MidiViewTest {

  @Test
  public void testView() {
    // test with multiple of the same note
    IMusicModel<Note> piece = new Piece();
    StringBuilder build = new StringBuilder();
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.D, Note.Octave.Eight, 5, 0, 60, 0));
    piece.add(new Note(Note.Pitch.C, Note.Octave.Eight, 5, 0, 60, 0));
    MusicControllerImpl<Note> controller = new MusicControllerImpl<Note>(piece);
    MidiView view = new MidiView(controller, build);
    view.view();
    assertEquals(build.toString(), "got receiver\n" +
            "midi opened\n" +
            "[-112, 110, 60] 0\n" +
            "[-128, 110, 60] 25\n" +
            "[-112, 110, 60] 0\n" +
            "[-128, 110, 60] 25\n" +
            "[-112, 108, 60] 0\n" +
            "[-128, 108, 60] 25\n" +
            "midi closed\n" +
            "receiver closing");

    // test with no notes
    piece = new Piece();
    build = new StringBuilder();


    controller = new MusicControllerImpl<Note>(piece);
    view = new MidiView(controller, build);
    view.view();
    assertEquals(build.toString(), "got receiver\n" +
            "midi opened\n" +
            "midi closed\n" +
            "receiver closing");

    // test with a singular note
    piece = new Piece();
    build = new StringBuilder();

    piece.add(new Note(60, 5, 5, 60, 1));

    controller = new MusicControllerImpl<Note>(piece);
    view = new MidiView(controller, build);
    view.view();
    assertEquals(build.toString(), "got receiver\n" +
            "midi opened\n" +
            "[-111, 60, 60] 25\n" +
            "[-127, 60, 60] 50\n" +
            "midi closed\n" +
            "receiver closing");

    // test with a chord of notes
    piece = new Piece();
    build = new StringBuilder();

    piece.add(new Note(60, 5, 5, 60, 1));
    piece.add(new Note(65, 5, 5, 60, 1));
    piece.add(new Note(69, 5, 5, 60, 1));

    controller = new MusicControllerImpl<Note>(piece);
    view = new MidiView(controller, build);
    view.view();
    assertEquals(build.toString(), "got receiver\n" +
            "midi opened\n" +
            "[-111, 60, 60] 25\n" +
            "[-127, 60, 60] 50\n" +
            "[-111, 65, 60] 25\n" +
            "[-127, 65, 60] 50\n" +
            "[-111, 69, 60] 25\n" +
            "[-127, 69, 60] 50\n" +
            "midi closed\n" +
            "receiver closing");


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

    controller = new MusicControllerImpl<Note>(piece);
    view = new MidiView(controller, build);
    view.view();
    assertEquals(build.toString(), "got receiver\n" +
            "midi opened\n" +
            "[-111, 60, 60] 25\n" +
            "[-127, 60, 60] 50\n" +
            "[-111, 65, 60] 25\n" +
            "[-127, 65, 60] 50\n" +
            "[-111, 69, 60] 25\n" +
            "[-127, 69, 60] 50\n" +
            "[-112, 109, 20] 50\n" +
            "[-128, 109, 20] 100\n" +
            "[-111, 60, 60] 100\n" +
            "[-127, 60, 60] 150\n" +
            "[-111, 65, 60] 100\n" +
            "[-127, 65, 60] 150\n" +
            "[-111, 69, 60] 100\n" +
            "[-127, 69, 60] 150\n" +
            "midi closed\n" +
            "receiver closing");
  }
}
