package cs3500.music.view;

import java.awt.event.KeyListener;

import cs3500.music.model.Note;

/**
 * A CombinedView to combine both the MidiView and GuiViewFrame.
 */
public class CombinedView implements GuiView<Note> {

  GuiViewFrame guiView;
  MidiView midiView;


  public CombinedView(GuiViewFrame guiView, MidiView midiView) {
    this.guiView = guiView;
    this.midiView = midiView;
    new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          if (midiView.isPlaying()) {
            guiView.updateTime(midiView.getTime());
          }
        }
      }
    }).start();
  }

  @Override
  public void view() {
    guiView.view();
    midiView.view();
  }

  @Override
  public void togglePausePlay() {
    guiView.togglePausePlay();
    midiView.togglePausePlay();
  }

  @Override
  public void playAtTime(long sec) {
    guiView.playAtTime(sec);
    midiView.playAtTime(sec);
  }

  @Override
  public void moveToBeginning() {
    midiView.moveToBeginning();
    guiView.moveToBeginning();
  }

  @Override
  public void moveToEnd() {
    midiView.moveToEnd();
    guiView.moveToEnd();
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
    if (midiView.isPlaying()) {
      this.togglePausePlay();
    }
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


}
