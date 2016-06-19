package cs3500.music.tests;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;

import cs3500.music.controller.MusicControllerImpl;
import cs3500.music.model.IMusicModel;
import cs3500.music.controller.MusicController;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.IView;

import static org.junit.Assert.*;

/**
 * The test for ConsoleView to see if it is working properly.
 */
public class ConsoleViewTest {
  @Test
  public void testViewNormal() throws Exception {
    Appendable build = new StringBuilder();
    IMusicModel<Note> piece = new Piece();
    piece.addAll(new ArrayList<Note>(
                    Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                            new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                            new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));
    MusicControllerImpl<Note> controller = new MusicControllerImpl<Note>(piece);
    IView console = new ConsoleView(build, controller);
    console.view();
    assertEquals(
                    "   D5  D#5   E5   F5  F#5   G5  G#5   A5 \n" +
                    "0  X                                  X  \n" +
                    "1  |                                  |  \n" +
                    "2                                        \n" +
                    "3       X                                \n" +
                    "4       |                                \n" +
                    "5       |                                \n" +
                    "6       |                                \n", build.toString());
  }

  @Test
  public void testViewOverlap() throws Exception {
    Appendable build = new StringBuilder();
    IMusicModel<Note> piece = new Piece();
    piece.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 2, 5))));
    MusicControllerImpl<Note> controller = new MusicControllerImpl<Note>(piece);
    IView console = new ConsoleView(build, controller);
    console.view();
    assertEquals(
                    "   D5  D#5   E5   F5  F#5   G5  G#5   A5 \n" +
                    "0  X                                  X  \n" +
                    "1  |                                  |  \n" +
                    "2                                        \n" +
                    "3       X                                \n" +
                    "4       |                                \n" +
                    "5       X                                \n" +
                    "6       |                                \n", build.toString());
  }

  @Test
  public void testViewChord() throws Exception {
    Appendable build = new StringBuilder();
    IMusicModel<Note> piece = new Piece();
    piece.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.C_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.C, Note.Octave.Five, 4, 3))));
    MusicControllerImpl<Note> controller = new MusicControllerImpl<Note>(piece);
    IView console = new ConsoleView(build, controller);
    console.view();
    assertEquals(
                            "   C5  C#5   D5  D#5 \n" +
                            "0                    \n" +
                            "1                    \n" +
                            "2                    \n" +
                            "3  X    X         X  \n" +
                            "4  |    |         |  \n" +
                            "5  |    |         |  \n" +
                            "6  |    |         |  \n", build.toString());
  }

/*
  @Test
  public void testShowPieceNormal() {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));

    assertEquals(
            "   D5  D#5   E5   F5  F#5   G5  G#5   A5 \n" +
                    "0  X                                  X  \n" +
                    "1  |                                  |  \n" +
                    "2                                        \n" +
                    "3       X                                \n" +
                    "4       |                                \n" +
                    "5       |                                \n" +
                    "6       |                                \n", m1.showPiece());
  }

  @Test
  public void testShowPieceOverLap() {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 2, 5))));

    assertEquals(
            "   D5  D#5   E5   F5  F#5   G5  G#5   A5 \n" +
                    "0  X                                  X  \n" +
                    "1  |                                  |  \n" +
                    "2                                        \n" +
                    "3       X                                \n" +
                    "4       |                                \n" +
                    "5       X                                \n" +
                    "6       |                                \n", m1.showPiece());
  }

  @Test
  public void testEmptyPiece() {
    IMusicModel<Note> m1 = new Piece();
    assertEquals(
            "", m1.showPiece());
  }
*/
}