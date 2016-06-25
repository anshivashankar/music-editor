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


    guiView.togglePausePlay();
    time = midiView.getTime();
    midiView.togglePausePlay();
  }

  @Override
  public void playAtTime(long sec) {
    guiView.playAtTime(sec);
    midiView.playAtTime(sec);
    time = sec;
  }

  @Override
  public void moveToBeginning() {
    time = 0;
    midiView.moveToBeginning();
    guiView.moveToBeginning();
  }

  @Override
  public void moveToEnd() {
    midiView.moveToEnd();
    guiView.moveToEnd();
    time = midiView.getTime();
  }

  @Override
  public void addKeyListener(KeyListener kl) {
    this.guiView.addKeyListener(kl);
  }

  @Override
  public Note getEditNote() throws IllegalArgumentException {
    return this.guiView.getEditNote();
  }

  @Override
  public void openEditWindow() throws IllegalArgumentException {
    this.guiView.openEditWindow();
  }

  @Override
  public void addEditWindowKeyListener(KeyListener kl) {
    this.guiView.addEditWindowKeyListener(kl);
  }

  @Override
  public void closeEditWindow() {
    this.guiView.closeEditWindow();
  }

  @Override
  public void scrollRight() {
    guiView.scrollRight();
  }

  @Override
  public void scrollLeft() {
    guiView.scrollLeft();
  }

  @Override
  public void scrollUp() {
    guiView.scrollUp();
  }

  @Override
  public void scrollDown() {
    guiView.scrollDown();
  }

  @Override
  public void update() {
    this.guiView.update();
    this.midiView.update();
  }

  @Override
  public void updateTime(long time2) {
    this.time = midiView.getTime();
    guiView.updateTime(this.time/1000);
  }

}
