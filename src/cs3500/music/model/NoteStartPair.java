package cs3500.music.model;

/**
 * Stores a pairing of a note and a beat that it starts on parametrized over the kind of note
 */
public class NoteStartPair<K> {
  private final K k;
  private final int startBeat;

  public NoteStartPair(K k, int startBeat) {
    this.k = k;
    this.startBeat = startBeat;
  }

  public K getNote() {
    return this.k;
  }

  public int getStartBeat() {
    return this.startBeat;
  }
}
