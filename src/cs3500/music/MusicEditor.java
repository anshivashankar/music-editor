package cs3500.music;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.MusicController;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.CompositionBuilderImpl;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.IView;
import cs3500.music.view.MIDIView;

/**
 * Music Editor class, has the main method and takes in command-line arguments
 * to run the program.
 */
public class MusicEditor {
  // main method to execute the program

  public static void main(String[] args) {
    String fileName = args[0];
    String modelType = args[1];


    if(modelType.equals("console")) {
      CompositionBuilder<Piece> comp = new CompositionBuilderImpl();
      try {
        IView console = new ConsoleView(System.out, new MusicController(MusicReader.parseFile(new FileReader(fileName), comp)));
        console.view();
      }
      catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
      //console.view();
    }
    else if(modelType.equals("midi")) {
      // use the midi view
      CompositionBuilder<Piece> comp = new CompositionBuilderImpl();
      try {
      IView MIDI = new MIDIView(new MusicController(MusicReader.parseFile(new FileReader(fileName), comp)));
        MIDI.view();
      }
      catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    }
    else if(modelType.equals("GUI")) {
      CompositionBuilder<Piece> comp = new CompositionBuilderImpl();
      try {
        IView gui = new GuiViewFrame(new MusicController(MusicReader.parseFile(new FileReader(fileName), comp)));
        gui.view();
      }
      catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    }
  }
}
