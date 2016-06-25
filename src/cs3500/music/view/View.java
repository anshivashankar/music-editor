package cs3500.music.view;

/**
 * The View class, the interface that represents a View, such as the ConsoleView, MidiView, and
 * GUIView.
 */
public interface View<K> {

  /**
   * Call this method  when you would like to view the kind of View specified.
   */
  void view();

  /**
   * Call this method when you would like to switch between playing and pausing the music
   */
  void togglePausePlay();

  /**
   * Plays the song starting from a specific time stamp.
   * @param sec of type long, in microseconds.
   */
  void playAtTime(long sec);

  /**
   * makes the current view move back to the beginning of the piece
   */
  void moveToBeginning();

  /**
   * makes the current view move back to the end of the piece
   */
  void moveToEnd();

  /**
   * updates the view according to the models
   */
  void update();
}
