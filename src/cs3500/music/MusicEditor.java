package cs3500.music;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.MusicControllerImpl;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.CompositionBuilderImpl;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.View;
import cs3500.music.view.MidiView;

/**
 * Music Editor class, has the main method and takes in command-line arguments to run the program.
 */
public class MusicEditor {
  // main method to execute the program

  public static void main(String[] args) {
    String fileName = args[0];
    String modelType = args[1];
    CompositionBuilder<IMusicModel<Note>> comp = new CompositionBuilderImpl();
    View view;
    if (modelType.equals("console")) {
      try {
        view = new ConsoleView(System.out, new MusicControllerImpl<Note>(
                MusicReader.parseFile(new FileReader(fileName), comp)));
        view.view();
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    } else if (modelType.equals("midi")) {
      // use the midi view
      try {
        view = new MidiView(new MusicControllerImpl<Note>(
                MusicReader.parseFile(new FileReader(fileName), comp)));
        view.view();
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    } else if (modelType.equals("visual")) {
      try {
        view = new GuiViewFrame(new MusicControllerImpl<Note>(
                MusicReader.parseFile(new FileReader(fileName), comp)));
        view.view();
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    }
  }
}
