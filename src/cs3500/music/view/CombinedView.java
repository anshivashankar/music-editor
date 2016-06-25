package cs3500.music.view;

import java.awt.event.KeyListener;

import cs3500.music.model.Note;
import cs3500.music.model.Piece;

/**
 * A CombinedView to combine both the MidiView and GuiViewFrame.
 */
public class CombinedView implements GuiView<Note> {

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
    //this.pause();
  }

  @Override
  public void togglePausePlay() {

  }

  /*
  @Override
  public void play() {
    guiView.play();
    midiView.play();
  }*/

  @Override
  public void playAtTime(long sec) {
    guiView.playAtTime(sec);
    midiView.playAtTime(sec);
    time = sec;
  }

  @Override
  public void moveToBeginning() {
    time = 0;
  }

  @Override
  public void moveToEnd() {
    midiView.moveToEnd();
    guiView.moveToEnd();
  }

  @Override
  public void addKeyListener(KeyListener kl) {

  }

  @Override
  public Note getEditNote() throws IllegalArgumentException {
    return null;
  }

  @Override
  public void openEditWindow(KeyListener kl) throws IllegalArgumentException {

  }

  /*
  @Override
  public void pause() {
    guiView.pause();
    midiView.pause();
  }*/
}
