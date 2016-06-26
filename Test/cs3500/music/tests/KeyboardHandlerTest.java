package cs3500.music.tests;

import org.junit.Test;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import cs3500.music.controller.GUIMusicControllerImpl;
import cs3500.music.controller.KeyboardHandler;
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
  boolean touched;
  KeyboardHandler kh = new KeyboardHandler();

  @Test
  public void testKeyPressedRunnable() throws Exception {

    touched = false;

    kh.addKeyPressedRunnable(KeyEvent.VK_SPACE, () -> {
      touched = true;
    });

    kh.keyPressed(new KeyEvent(new JFrame(), 0, 0, 0, KeyEvent.VK_E, 'E'));

    assertFalse(touched);

    kh.keyPressed(new KeyEvent(new JFrame(), 0, 0, 0, KeyEvent.VK_SPACE, ' '));

    assertTrue(touched);
  }

  @Test
  public void testKeyTypedRunnable() throws Exception {

    touched = false;

    kh.addKeyTypedRunnable(KeyEvent.VK_SPACE, () -> {
      touched = true;
    });

    kh.keyTyped(new KeyEvent(new JFrame(), 0, 0, 0, KeyEvent.VK_E, 'E'));

    assertFalse(touched);

    kh.keyPressed(new KeyEvent(new JFrame(), 0, 0, 0, KeyEvent.VK_SPACE, ' '));

    assertFalse(touched);

    kh.keyTyped(new KeyEvent(new JFrame(), 0, 0, 0, KeyEvent.VK_SPACE, ' '));

    assertTrue(touched);
  }

  @Test
  public void testKeyReleasedRunnable() throws Exception {

    touched = false;

    kh.addKeyReleasedRunnable(KeyEvent.VK_SPACE, () -> {
      touched = true;
    });

    kh.keyReleased(new KeyEvent(new JFrame(), 0, 0, 0, KeyEvent.VK_E, 'E'));

    assertFalse(touched);

    kh.keyReleased(new KeyEvent(new JFrame(), 0, 0, 0, KeyEvent.VK_SPACE, ' '));

    assertTrue(touched);
  }


}