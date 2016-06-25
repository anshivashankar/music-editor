package cs3500.music.tests;

import org.junit.Test;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import cs3500.music.controller.GUIMusicControllerImpl;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.ReadOnlyModelImpl;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;

import static org.junit.Assert.*;

/**
 * Tests for KeyPressedRunnable.
 */
public class KeyboardHandlerTest {
  @Test
  public void addKeyPressedRunnable() throws Exception {

  }

  @Test
  public void keyTyped() throws Exception {
    //IMusicModel<Note> piece = new Piece();
    //ReadOnlyModelImpl roModel = new ReadOnlyModelImpl(piece);
    //GuiView view =  new GuiViewFrame(roModel);
    //GUIMusicControllerImpl controller = new GUIMusicControllerImpl(piece, view);
    //InputEvent event = new KeyEvent();
  }

  @Test
  public void keyPressed() throws Exception {

  }

  @Test
  public void keyReleased() throws Exception {

  }

}