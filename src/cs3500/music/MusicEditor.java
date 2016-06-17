package cs3500.music;

import cs3500.music.view.ConsoleView;
import cs3500.music.view.IView;

/**
 * Music Editor class, has the main method and takes in command-line arguments
 * to run the program.
 */
public class MusicEditor {
  // main method to execute the program

  public static void main(String[] args) {
    String fileName = args[0];
    String modelType = args[1];

    // console view
    if(modelType.equals("console")) {
      IView console = new ConsoleView(System.out);
      console.view();
    }
    else if(modelType.equals("midi")) {
      // use the midi view

    }

  }
}
