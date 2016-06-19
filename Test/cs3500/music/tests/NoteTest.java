package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.Note;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NoteTest {

  Note n1 = new Note(Note.Pitch.A, Note.Octave.Five, Note.BEATS_IN_WHOLE_NOTE, 1);
  Note n2 = new Note(Note.Pitch.D_SHARP, Note.Octave.Four, Note.BEATS_IN_WHOLE_NOTE, 1);
  Note n3 = new Note(Note.Pitch.A, Note.Octave.Five, Note.BEATS_IN_WHOLE_NOTE, 1);
  Note n4 = new Note(Note.Pitch.D_SHARP, Note.Octave.Four, Note.BEATS_IN_WHOLE_NOTE, 1);

  @Test(expected = IllegalArgumentException.class)
  public void testConstructNullPitch() {
    new Note(null, Note.Octave.Five, Note.BEATS_IN_WHOLE_NOTE, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructNullOctave() {
    new Note(Note.Pitch.A, null, Note.BEATS_IN_WHOLE_NOTE, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructLowDuration() {
    new Note(Note.Pitch.A, Note.Octave.Five, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructLowStartBeat() {
    new Note(Note.Pitch.A, Note.Octave.Five, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorLowNotePlace() {
    new Note(11, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHighNotePlace() {
    new Note(Note.Octave.values().length * Note.Pitch.values().length + 8, 5, 5);
  }

  @Test
  public void testGetPitch() {
    assertEquals(Note.Pitch.A, n1.getPitch());
  }

  @Test
  public void testGetOctave() {
    assertEquals(Note.Octave.Five, n1.getOctave());
  }

  @Test
  public void testGetDuration() throws Exception {
    assertEquals(Note.BEATS_IN_WHOLE_NOTE, n1.getDuration());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLowDuration() {
    new Note(60, -1, 2);
  }

  @Test
  public void testEquals() {
    assertEquals(n1, n1);
    assertEquals(n2, n2);
    assertEquals(n1, n3);
    assertEquals(n3, n1);
    assertEquals(n2, n4);
    assertEquals(n4, n2);

    assertNotEquals(n1, n2);
    assertNotEquals(n2, n1);
    assertNotEquals(n3, n4);
    assertNotEquals(n4, n3);
  }

  @Test
  public void compareTo() {
    assertEquals(n1, n1);
    assertEquals(0, n1.compareTo(n1));
    assertEquals(n1, n3);
    assertEquals(0, n1.compareTo(n3));
    assertEquals(n3, n1);
    assertEquals(0, n3.compareTo(n1));

    assertEquals(18, n1.compareTo(n2));
    assertEquals(-18, n2.compareTo(n1));

    // changing duration shouldn't change the comparison
    Note n = new Note(Note.Pitch.A, Note.Octave.Five, Note.BEATS_IN_WHOLE_NOTE + 1, 5);
    assertEquals(0, n1.compareTo(n));

    // changing startBeat shouldn't change the comparison
    Note nn = new Note(Note.Pitch.A, Note.Octave.Five, Note.BEATS_IN_WHOLE_NOTE, 5 + 1);
    assertEquals(0, n1.compareTo(nn));
  }

  @Test
  public void testHashCode() {
    assertEquals(n1, n3);
    assertEquals(n1.hashCode(), n3.hashCode());
    assertEquals(n2, n4);
    assertEquals(n2.hashCode(), n4.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("A5", n1.toString());
    assertEquals("D#4", n2.toString());
  }

  @Test
  public void notePlaceIsCorrectPlace() {
    Note n1 = new Note(60, 1, 2);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.Four, 1, 2);
    assertEquals(n1, n2);
    assertEquals(60, n1.notePlace());
    assertEquals(60, n2.notePlace());

    Note n3 = new Note(12, 1, 2);
    Note n4 = new Note(Note.Pitch.C, Note.Octave.Zero, 1, 2);
    assertEquals(n3, n4);
    assertEquals(12, n3.notePlace());
    assertEquals(12, n4.notePlace());

    Note n5 = new Note(127, 1, 2);
    Note n6 = new Note(Note.Pitch.G, Note.Octave.Nine, 1, 2);
    assertEquals(n5, n6);
    assertEquals(127, n5.notePlace());
    assertEquals(127, n6.notePlace());
  }

}