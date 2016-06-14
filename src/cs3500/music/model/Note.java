package cs3500.music.model;

import java.util.Objects;

/**
 * Represents a Note that can go in a piece of Music Notes are meant to be final once created so
 * editing a note is not allowed
 */
public final class Note implements Comparable<Note> {
  private final Pitch pitch;
  private final Octave octave;
  private final int duration;
  private final int startBeat;
  public static final int BEATS_IN_WHOLE_NOTE = 4;

  public Note(Pitch pitch, Octave octave, int duration, int startBeat) {
    if (pitch == null) {
      throw new IllegalArgumentException("Pitch must not be null");
    }
    this.pitch = pitch;

    if (octave == null) {
      throw new IllegalArgumentException("Octave must not be null");
    }
    this.octave = octave;

    if (duration <= 0) {
      throw new IllegalArgumentException("Duration must be positive");
    }
    this.duration = duration;

    if (startBeat < 0) {
      throw new IllegalArgumentException("Duration must be positive");
    }
    this.startBeat = startBeat;
  }

  /**
   * allows a Note to be constructed by a value integer which is described in the notePlace
   * function
   */
  public Note(int notePlace, int duration, int startBeat) {
    if (notePlace < 0 || notePlace >= Octave.values().length * Pitch.values().length) {
      throw new IllegalArgumentException("Bad notePlace value");
    }

    this.pitch = Pitch.values()[notePlace % Pitch.values().length];
    this.octave = Octave.values()[notePlace / Pitch.values().length];

    if (duration <= 0) {
      throw new IllegalArgumentException("Duration must be positive");
    }
    this.duration = duration;

    if (startBeat < 0) {
      throw new IllegalArgumentException("Duration must be positive");
    }
    this.startBeat = startBeat;
  }

  public Pitch getPitch() {
    return pitch;
  }

  public Octave getOctave() {
    return octave;
  }

  public int getDuration() {
    return duration;
  }

  public int getStartBeat() {
    return startBeat;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Note)) {
      return false;
    } else {
      Note that = (Note) other;
      return this.pitch == that.pitch && this.octave == that.octave &&
              this.duration == that.duration && this.startBeat == that.startBeat;
    }
  }

  /**
   * returns the place of the note in the ordering of notes based on octave and pitch value where
   * the ordinal value takes higher precedence
   *
   * @return the place of the note in the ordering of notes based on octave and pitch value where
   * the ordinal value takes higher precedence
   */
  protected int notePlace() {
    return (this.octave.ordinal() * Pitch.values().length) + this.pitch.ordinal();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.pitch, this.octave, this.duration, this.startBeat);
  }

  @Override
  public String toString() {
    return this.pitch.toString() + this.octave.toString();
  }

  /**
   * compares notes by comparing their octave and pitch only
   *
   * @param that other note to compare to
   * @return the distance of the two notes in an ordering based on octave and pitch value where the
   * ordinal value takes higher precedence
   */
  @Override
  public int compareTo(Note that) {
    return this.notePlace() - that.notePlace();
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

  /**
   * Represents the various octaves that are allowed in pieces of music
   */
  public enum Octave {
    One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10);

    private final int value;

    private Octave(int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return Integer.toString(this.value);
    }
  }

}
