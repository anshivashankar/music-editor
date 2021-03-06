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
  private final int volume;
  private final int instrument;

  /**
   * Constructor for a Note, without volume and instrument
   *
   * @param pitch     of type Pitch
   * @param octave    of type Octave
   * @param duration  of type int
   * @param startBeat of type int
   */
  public Note(Pitch pitch, Octave octave, int duration, int startBeat) {
    this(pitch, octave, duration, startBeat, 10, 0);
  }

  /**
   * Constructor for a Note, with Pitch, octave, volume and instrument
   *
   * @param pitch      of type Pitch
   * @param octave     of type Octave
   * @param duration   of type dint
   * @param startBeat  of type int
   * @param volume     of type int
   * @param instrument of type int
   */
  public Note(Pitch pitch, Octave octave, int duration,
              int startBeat, int volume, int instrument) {
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

    if (volume < 0) {
      throw new IllegalArgumentException("Volume must be positive");
    }
    this.volume = volume;

    if (instrument < 0 || instrument > 15) {
      throw new IllegalArgumentException("Instrument must be in the correct range");
    }
    this.instrument = instrument;
  }

  /**
   * allows a Note to be constructed by a value integer which is described in the notePlace
   * function
   */
  public Note(int notePlace, int duration, int startBeat) {
    this(notePlace, duration, startBeat, 100, 0);
  }

  /**
   * Constructor for a Note, using a Midi-like int.
   *
   * @param notePlace  of type int
   * @param duration   of type int
   * @param startBeat  of type int
   * @param volume     of type int
   * @param instrument of type int
   */
  public Note(int notePlace, int duration, int startBeat, int volume, int instrument) {
    // notePlace cannot be less than 12 because that would be a negative
    if (notePlace < 12 || notePlace > Octave.values().length * Pitch.values().length + 7) {
      throw new IllegalArgumentException("Bad notePlace value");
    }

    // subtract 12 to match up with the midi pitch value
    notePlace -= 12;

    this.pitch = Pitch.values()[notePlace % Pitch.values().length];
    this.octave = Octave.values()[notePlace / Pitch.values().length];

    if (duration < 0) {
      throw new IllegalArgumentException("Duration must be positive");
    }
    this.duration = duration;

    if (startBeat < 0) {
      throw new IllegalArgumentException("Duration must be positive");
    }
    this.startBeat = startBeat;

    if (volume < 0) {
      throw new IllegalArgumentException("Volume must be positive");
    }
    this.volume = volume;

    if (instrument < 0 || instrument > 15) {
      throw new IllegalArgumentException("Instrument must be in the correct range");
    }
    this.instrument = instrument;
  }

  /**
   * Gets the Pitch of this Note.
   *
   * @return the Pitch of this Note.
   */
  public Pitch getPitch() {
    return pitch;
  }

  /**
   * Gets the Octave of this Note.
   *
   * @return Gets the Octave of this Note.
   */
  public Octave getOctave() {
    return octave;
  }

  /**
   * Gets the Duration of this Note.
   *
   * @return the Duration of this Note.
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Gets the start beat of this Note.
   *
   * @return the start beat of this Note.
   */
  public int getStartBeat() {
    return startBeat;
  }

  /**
   * Gets the Volume of this Note.
   *
   * @return the Volume of this Note.
   */
  public int getVolume() {
    return volume;
  }

  /**
   * Gets the Instrument of this Note.
   *
   * @return the Instrument of this Note.
   */
  public int getInstrument() {
    return instrument;
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
   * NOTE: made notePlace() public, so that it can be used in the views.
   *
   * @return the place of the note in the ordering of notes based on octave and pitch value where
   * the ordinal value takes higher precedence
   */
  public int notePlace() {
    // add the 12 so it matches up with midi pitch values
    return (this.octave.ordinal() * Pitch.values().length) + this.pitch.ordinal() + 12;
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
    Zero(0), One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9);

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
