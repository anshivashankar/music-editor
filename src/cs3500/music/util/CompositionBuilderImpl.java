package cs3500.music.util;

import cs3500.music.model.Note;
import cs3500.music.model.Piece;

/**
 * Created by ashwinshivashankar on 6/16/16.
 */
public class CompositionBuilderImpl implements CompositionBuilder<Piece> {
  private Piece song;


  protected CompositionBuilderImpl() {
    song = new Piece();
  }

  @Override
  public Piece build() {
    return song;
  }

  @Override
  public CompositionBuilder<Piece> setTempo(int tempo) {
    song.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilder<Piece> addNote(int start, int end, int instrument,
                                           int pitch, int volume) {
    song.add(new Note(pitch, end-start, start, volume, instrument));
    return this;
  }
}
