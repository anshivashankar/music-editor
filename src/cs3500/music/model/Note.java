package cs3500.music.model;

import java.util.Objects;

/**
 * Represents a Note that can go in a piece of Music Notes are meant to be final once created so
 * editing a note is not allowed
 */
// INVARIANT: octave will always be between 1 and 10 inclusive
public final class Note implements Comparable<Note> {
  private final Pitch pitch;
  private final int octave;
  private final int duration;
  public static final int BEATS_IN_WHOLE_NOTE = 4;

  public Note(Pitch pitch, int octave, int duration) {
    if (pitch == null) {
      throw new IllegalArgumentException("Pitch must not be null");
    }
    this.pitch = pitch;
    if (octave >= 1 && octave <= 10) {
      this.octave = octave;
    } else {
      throw new IllegalArgumentException("Only the octaves 1 - 10 are supported. Given: "
              + octave);
    }
    this.duration = duration;
  }

  public Pitch getPitch() {
    return pitch;
  }

  public int getOctave() {
    return octave;
  }

  public int getDuration() {
    return duration;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Note)) {
      return false;
    } else {
      Note that = (Note) other;
      return this.pitch == that.pitch && this.octave == that.octave &&
              this.duration == that.duration;
    }
  }

  // returns the place of the note in the ordering of notes from low to high
  // used in comparison
  private int notePlace() {
    return ((this.octave - 1) * Pitch.values().length) + this.pitch.ordinal();
  }

  @Override
  /*
   * compares the difference in pitch and octave first,
   * if they are the same then compares the duration of the note
   */
  public int compareTo(Note that) {
    int diff = this.notePlace() - that.notePlace();
    if (diff != 0) {
      return diff;
    } else {
      return this.duration - that.duration;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(pitch, octave, duration);
  }

  @Override
  public String toString() {
    return pitch.toString() + Integer.toString(octave);
  }

  /**
   * Represents the various pitches that are allowed in pieces of music
   */
  public enum Pitch {
    C("C"), C_SHARP("C#"), D("D"), D_SHARP("D#"), E("E"), F("F"), F_SHARP("F#"), G("G"),
    G_SHARP("G#"), A("A"), A_SHARP("A#"), B("B");

    private final String value;

    private Pitch(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

}
