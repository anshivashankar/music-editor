package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Piece class.
 */
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

  @Test
  public void testLastBeat() {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));

    assertEquals(7, m1.lastBeat());
  }

  @Test
  public void testSetGetTempo() {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));

    assertEquals(5, m1.getTempo());

    m1.setTempo(7);
    assertEquals(7, m1.getTempo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBadTempo() {
    IMusicModel<Note> m1 = new Piece();
    m1.addAll(new ArrayList<Note>(
            Arrays.asList(new Note(Note.Pitch.D_SHARP, Note.Octave.Five, 4, 3),
                    new Note(Note.Pitch.D, Note.Octave.Five, 2, 0),
                    new Note(Note.Pitch.A, Note.Octave.Five, 2, 0))));

    m1.setTempo(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructBadTempo() {
    new Piece(-1);
  }

}