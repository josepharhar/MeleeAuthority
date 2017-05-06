import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class Search {
  public static void main(String[] args) throws Exception {
    System.out.println("ByteOrder.nativeOrder(): " + ByteOrder.nativeOrder());

    if (args.length < 1) {
      System.out.println("usage: java Search file.txt");
      return;
    }

    // search file is comma deliminated list of int16s
    String searchLine = new Scanner(new File(args[0])).nextLine();
    String[] shortStringArray = searchLine.split(",");
    int searchLengthBytes = shortStringArray.length * 2;
    ByteBuffer searchBytes = ByteBuffer.allocate(searchLengthBytes);
    for (String string : shortStringArray) {
      short shortValue = Short.parseShort(string);
      // little endian -> big endian
      byte firstByte = (byte) ((shortValue >> 8) & 0xFF);
      byte secondByte = (byte) (shortValue & 0xFF);
      searchBytes.put(firstByte);
      searchBytes.put(secondByte);
      System.out.printf("Converted short " + shortValue + " to 0x%02X%02X\n", firstByte, secondByte);
    }

    FileChannel meleeFileChannel = new RandomAccessFile("melee.iso", "r").getChannel();
    /*ByteBuffer meleeBytes = ByteBuffer.allocate((int) meleeFileChannel.size());
    meleeFileChannel.read(meleeBytes);
    meleeBytes.flip();*/
    MappedByteBuffer meleeBytes = meleeFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, meleeFileChannel.size());
    meleeBytes.load();
    
    int searchIndex = 0;
    int meleeIndex = 0;
    int baseMeleeIndex = 0;
    int longestStreak = 0;
    while (searchIndex < searchLengthBytes) {
      if (searchBytes.get(searchIndex)
          == meleeBytes.get(meleeIndex)) {
        searchIndex++;
        if (searchIndex > longestStreak) {
          longestStreak = searchIndex;
          System.out.println("new longest streak: " + longestStreak);
        }
      } else {
        searchIndex = 0;
        baseMeleeIndex = meleeIndex;
      }
      meleeIndex++;
    }

    if (searchIndex == searchLengthBytes) {
      // found it
      System.out.println("found search starting at melee index " + baseMeleeIndex);
    } else {
      // didn't find
      System.out.println("didn't find search in melee");
    }
  }
}
