package net.arhar.meleeauthorityscanner;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.*;

public class JSONWriter {

  private static final String INDENT = "  ";

  private final Path outputPath;

  private BufferedWriter writer;

  public JSONWriter(Path outputPath) {
    this.outputPath = outputPath;
  }

  private void writeArray(List<String> values) throws IOException {
    writer.write("[\n");
    for (int i = 0; i < values.size(); i++) {
      String format;
      if (i == values.size() - 1) {
        format = INDENT + "\"%s\"\n";
      } else {
        format = INDENT + "\"%s\",\n";
      }
      writer.write(String.format(format, values.get(i)));
    }
    writer.write("]\n");
    writer.flush();
  }

  private String toObject(Map<String, String> properties) {
    StringBuilder builder = new StringBuilder();
    builder.append("{\n");
    Iterator<Map.Entry<String, String>> iterator = properties.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> property = iterator.next();
      String format;
      if (iterator.hasNext()) {
        format = INDENT + "\"%s\": \"%s\",\n";
      } else {
        format = INDENT + "\"%s\": \"%s\"\n";
      }
      builder.append(String.format(format, property.getKey(), property.getValue()));
    }
    builder.append("}\n");
    return builder.toString();
  }

  private String toObject(String... values) {
    return toObject(Arrays.asList(values));
  }

  private String toObject(List<String> values) {
    StringBuilder builder = new StringBuilder();
    builder.append("{\n");
    for (int i = 0; i < values.size(); i += 2) {
      String format;
      if (i == values.size() - 2) {
        format = INDENT + "\"%s\": \"%s\"\n";
      } else {
        format = INDENT + "\"%s\": \"%s\",\n";
      }
      builder.append(String.format(format, values.get(i), values.get(i + 1)));
    }
    builder.append("}\n");
    return builder.toString();
  }

  public void write(
      Map<Character, Map<Attribute, Number>> charactersToAttributes,
      Map<Character, List<Animation>> charactersToAnimations) throws IOException {
    BufferedWriter writer;

    writer = Files.newBufferedWriter(Paths.get("json/characters.json"));
    writer.write(toObject(Arrays.stream(Character.values())
          .flatMap(character -> Stream.of(character.name(), character.fullName))
          .collect(Collectors.toList())));
    writer.close();

    writer = Files.newBufferedWriter(Paths.get("json/attributes.json"));
    writer.write(toObject(Arrays.stream(Attribute.values())
          .map(attribute -> toObject(
              "fullName", attribute.fullName,
              "viewCategory", attribute.viewCategory.name()))
          .collect(Collectors.toList())));
    writer.close();

    writer = Files.newBufferedWriter(Paths.get("json/character_attributes.json"));
    /*writer.write("{\n");
    charactersToAttributes.forEach
    writer.write("}\n");
    writer.flush();
    writer.close();*/

    writer = Files.newBufferedWriter(Paths.get("json/animations.json"));

    writer = Files.newBufferedWriter(Paths.get("json/animation_command_types.json"));

    writer = Files.newBufferedWriter(Paths.get("json/character_animation_commands.json"));

    writer = Files.newBufferedWriter(Paths.get("json/frame_strips.json"));

    writer = Files.newBufferedWriter(Paths.get("json/hitboxes.json"));
  }
}
