package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.Piece;

/**
 * A CombinedView to combine both the MidiView and GuiViewFrame.
 */
public class CombinedView implements View {

  long time;
  GuiViewFrame guiView;
  MidiView midiView;


  public CombinedView(GuiViewFrame guiView, MidiView midiView) {
    this.time = 0;
    this.guiView = guiView;
    this.midiView = midiView;
  }

  @Override
  public void view() {
    guiView.view();
    midiView.view();
    this.pause();
  }

  @Override
  public void play() {
    guiView.play();
    midiView.play();
  }

  @Override
  public void playAtTime(long sec) {
    guiView.playAtTime(sec);
    midiView.playAtTime(sec);
  }

  @Override
  public void pause() {
    guiView.pause();
    midiView.pause();
  }
}
