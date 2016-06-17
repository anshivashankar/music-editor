package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;

import static org.junit.Assert.assertEquals;

public class PieceTest {

  @Test
  public void testAdd() {
    IMusicModel<Note> m1 = new Piece();
    m1.add(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3));

    List<Note> notes = m1.getAllNotes();

    assertEquals(1, notes.size());
    assertEquals(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3), notes.get(0));
  }

  @Test
  public void testAddAllAndGetAllNotes() throws Exception {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));

    List<Note> notes = m1.getAllNotes();

    assertEquals(3, notes.size());
    assertEquals(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3), notes.get(0));
    assertEquals(new Note(Note.Pitch.D, Note.Octave.Five, 2, 0), notes.get(1));
    assertEquals(new Note(Note.Pitch.A, Note.Octave.Five, 2, 0), notes.get(2));
  }

  @Test
  public void testRemove() {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));

    m1.remove(new Note(Note.Pitch.D, Note.Octave.Five, 2, 0));
    m1.remove(new Note(Note.Pitch.A, Note.Octave.Five, 2, 0));

    List<Note> notes = m1.getAllNotes();

    assertEquals(1, notes.size());
    assertEquals(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3), notes.get(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRemove() {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));

    m1.remove(new Note(Note.Pitch.D, Note.Octave.Three, 2, 0));
  }

  /*
  @Test
  public void combineSimultaneously() {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));

    IMusicModel<Note> m2 = new Piece();
    m2.add(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 2, 5));

    IMusicModel<Note> m3 = m1.combineSimultaneously(m2);

    assertEquals(
                    "   D5  D#5   E5   F5  F#5   G5  G#5   A5 \n" +
                    "0  X                                  X  \n" +
                    "1  |                                  |  \n" +
                    "2                                        \n" +
                    "3       X                                \n" +
                    "4       |                                \n" +
                    "5       X                                \n" +
                    "6       |                                \n", m3.showPiece());

    IMusicModel<Note> m4 = m2.combineSimultaneously(m1);

    assertEquals(
                    "   D5  D#5   E5   F5  F#5   G5  G#5   A5 \n" +
                    "0  X                                  X  \n" +
                    "1  |                                  |  \n" +
                    "2                                        \n" +
                    "3       X                                \n" +
                    "4       |                                \n" +
                    "5       X                                \n" +
                    "6       |                                \n", m4.showPiece());
  }

  @Test
  public void combineConsecutively() throws Exception {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));

    IMusicModel<Note> m2 = new Piece();
    m2.add(new Note(Note.Pitch.E, Note.Octave.Five, 1, 0));

    IMusicModel<Note> m3 = m1.combineConsecutively(m2);

    assertEquals(
                    "   D5  D#5   E5   F5  F#5   G5  G#5   A5 \n" +
                    "0  X                                  X  \n" +
                    "1  |                                  |  \n" +
                    "2                                        \n" +
                    "3       X                                \n" +
                    "4       |                                \n" +
                    "5       |                                \n" +
                    "6       |                                \n" +
                    "7            X                           \n", m3.showPiece());
  }

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
  }*/
}