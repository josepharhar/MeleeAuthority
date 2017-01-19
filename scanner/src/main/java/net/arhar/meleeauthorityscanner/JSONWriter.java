package net.arhar.meleeauthorityscanner;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import com.fasterxml.jackson.databind.*;
import com.google.common.collect.*;

public class JSONWriter {

  private final Path outputPath;

  public JSONWriter(Path outputPath) {
    this.outputPath = outputPath;
  }

  public void write(
      Map<Character, Map<Attribute, Number>> charactersToAttributes,
      Map<Character, List<Animation>> charactersToAnimations) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    objectMapper.writeValue(new File("json/characters.json"),
        Arrays.stream(Character.values())
          .collect(Collectors.toMap(character -> character.name(), character -> character.fullName)));
    objectMapper.writeValue(new File("json/attributes.json"),
        Arrays.stream(Attribute.values())
          .collect(Collectors.toMap(
              attribute -> attribute.name(),
              attribute -> ImmutableMap.of(
                "fullName", attribute.fullName,
                "viewCategory", attribute.viewCategory.name()))));
    objectMapper.writeValue(new File("json/character_attributes.json"), charactersToAttributes);
    objectMapper.writeValue(new File("json/animations.json"), charactersToAnimations);
  }
}
