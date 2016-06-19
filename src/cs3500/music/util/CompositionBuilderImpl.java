package cs3500.music.util;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;

/**
 * An implementation of Composition
 */
public final class CompositionBuilderImpl implements CompositionBuilder<IMusicModel<Note>> {
  private IMusicModel<Note> song;


  public CompositionBuilderImpl() {
    song = new Piece();
  }

  @Override
  public IMusicModel<Note> build() {
    return song;
  }

  @Override
  public void setTempo(int tempo) {
    song.setTempo(tempo);
  }

  @Override
  public void addNote(int start, int end, int instrument,
                                           int pitch, int volume) {
    song.add(new Note(pitch, end-start, start, volume, instrument-1));
  }
}
