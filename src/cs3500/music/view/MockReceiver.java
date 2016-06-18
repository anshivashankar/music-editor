package cs3500.music.view;

import java.util.Arrays;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * MockReceiver extends Receiver. It implements send() and close(), and is used purely for testing
 * purposes. It writes to the StringBuilder given to it, and all instances of send() and close()
 * given to the Receiver.
 */
public class MockReceiver implements Receiver {

  StringBuilder string;

  public MockReceiver(StringBuilder string) {
    this.string = string;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    string.append(Arrays.toString(message.getMessage())).append(" ").append(timeStamp).append("\n");
  }

  @Override
  public void close() {
    string.append("receiver closing");
  }
}
