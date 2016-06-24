package cs3500.music.view;

/**
 * The View class, the interface that represents a View, such as the ConsoleView, MidiView, and
 * GUIView.
 */
public interface View {

  /**
   * Call this method  when you would like to view the kind of View specified.
   */
  void view();

  /**
   * Call this method when you would like to play the View
   */
  void play();

  /**
   * Call this method when you would like to pause the View
   */
  void pause();
}