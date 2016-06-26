package cs3500.music;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.GUIMusicControllerImpl;
import cs3500.music.controller.MusicController;
import cs3500.music.controller.MusicControllerImpl;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyModelImpl;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.CompositionBuilderImpl;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CombinedView;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiView;
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
    if (modelType.equals("console")) {
      try {
        View<Note> view = new ConsoleView(System.out, new ReadOnlyModelImpl<Note>(
                MusicReader.parseFile(new FileReader(fileName), comp)));
        MusicController<Note> controller = new MusicControllerImpl<Note>(MusicReader.parseFile(
                new FileReader(fileName), comp), view);
        controller.view();
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    } else if (modelType.equals("midi")) {
      // use the midi view
      try {
        View<Note> view = new MidiView(new ReadOnlyModelImpl<Note>(
                MusicReader.parseFile(new FileReader(fileName), comp)));
        MusicController<Note> controller = new MusicControllerImpl<Note>(MusicReader.parseFile(
                new FileReader(fileName), comp), view);
        controller.view();
        view.playAtTime(0);
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    } else if (modelType.equals("visual")) {
      try {
        IMusicModel<Note> model = MusicReader.parseFile(
                new FileReader(fileName), comp);
        ReadOnlyModelImpl<Note> roModel =
                new ReadOnlyModelImpl<Note>(model);

        GuiView<Note> guiView = new GuiViewFrame(roModel);
        GUIMusicControllerImpl<Note> controller = new GUIMusicControllerImpl<Note>(model, guiView);
        controller.view();
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    } else if (modelType.equals("combined")) {
      try {
        IMusicModel<Note> model = MusicReader.parseFile(
                new FileReader(fileName), comp);
        ReadOnlyModelImpl<Note> roModel =
                new ReadOnlyModelImpl<Note>(model);

        GuiViewFrame guiView = new GuiViewFrame(roModel);
        MidiView midiView = new MidiView(roModel);
        GuiView<Note> view = new CombinedView(guiView, midiView);
        GUIMusicControllerImpl<Note> controller = new GUIMusicControllerImpl<Note>(model, view);
        controller.view();
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    }
  }
}
