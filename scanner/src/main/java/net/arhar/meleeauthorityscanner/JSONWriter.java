package net.arhar.meleeauthorityscanner;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JSONWriter {

  private final Path outputPath;

  private BufferedWriter writer;

  public JSONWriter(Path outputPath) {
    this.outputPath = outputPath;
  }

  private void writeObject(Map<String, String> properties) throws IOException {
    writer.write("{\n");
    for (int i = 0; i < properties.size(); i++) {
      Map.Entry<String, String> property = properties.get(i);
      String format;
      if (i == properties.size() - 1) {
        format = INDENT + "\"%s\": \"%s\"\n";
      } else {
        format = INDENT + "\"%s\": \"%s\",\n";
      }
      writer.write(String.format(format, property.getKey(), property.getValue()));
    }
    writer.write("}\n");
    writer.flush();
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

  private void writeStringProperty(String key, String value) throws IOException {
    writer.write(String.format(INDENT + "\"%s\": \"%s\",\n", key, value));
  }

  private String asdf(String... rofl) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < rofl.length; i++) {
      builder.append(
    }
  }

  public void write(
      Map<Character, Map<Attribute, Number>> charactersToAttributes,
      Map<Character, List<Animation>> charactersToAnimations) throws IOException {
    BufferedWriter writer;

    writer = Files.newBufferedWriter(Paths.get("json/characters.json"));
    writeObject(Arrays.stream(Character.values())
        .map(character -> new SimpleEntry(character.name(), character.fullName))
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
    writer.close();

    writer = Files.newBufferedWriter(Paths.get("json/attributes.json"));
    writeObject(Arrays.stream(Attribute.values())
        .map(attribute -> String.format("\"%s\": { \"%s\": \"%s\"
          
        }

    writer.write("{\n");
    for (Attribute attribute : Attribute.values()) {
      writer.write(String.format("\"%s\": {\n" attribute.name()));
      writeStringProperty("fullName", attribute.fullName);
      writeStringProperty("viewCategory", attribute.viewCategory.name());
      writer.write("}\n");
    }
    writer.write("}\n");
    writer.flush();
    writer.close();

    writer = Files.newBufferedWriter(Paths.get("json/character_attributes.json"));
    writer.write("{\n");
    charactersToAttributes.forEach
    writer.write("}\n");
    writer.flush();
    writer.close();

    writer = Files.newBufferedWriter(Paths.get("json/animations.json"));

    writer = Files.newBufferedWriter(Paths.get("json/animation_command_types.json"));

    writer = Files.newBufferedWriter(Paths.get("json/character_animation_commands.json"));

    writer = Files.newBufferedWriter(Paths.get("json/frame_strips.json"));

    writer = Files.newBufferedWriter(Paths.get("json/hitboxes.json"));
  }

  private void writeCharacters() {

  }
}
