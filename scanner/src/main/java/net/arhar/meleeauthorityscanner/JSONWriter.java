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

  private String toHexString(byte[] data) {
    StringBuilder builder = new StringBuilder();
    builder.append("0x");
    for (int i = 0; i < data.length; i++) {
      builder.append(String.format("%02X", data[i]));
    }
    return builder.toString();
  }

  private Map<String, Object> convertAnimation(Animation animation) {
    Map<String, Object> map = new LinkedHashMap<>();

    List<Map<String, Object>> frameStrip = new ArrayList<>();
    for (int i = 0; i < animation.frameStrip.size(); i++) {
      Map<String, Object> frameStripMap = new LinkedHashMap<>();
      frameStripMap.put("Frame", i);
      frameStripMap.put("Hitbox", animation.frameStrip.get(i).hitbox);
      frameStripMap.put("IASA", animation.frameStrip.get(i).iasa);
      frameStripMap.put("Autocancel", animation.frameStrip.get(i).autocancel);
      frameStripMap.put("Invulnerable", animation.frameStrip.get(i).invulnerable);
      frameStripMap.put("Intangible", animation.frameStrip.get(i).intangible);
      frameStrip.add(frameStripMap);
    }
    map.put("frameStrip", frameStrip);

    List<Map<String, Object>> commands = new ArrayList<>();
    for (int i = 0; i < animation.commands.size(); i++) {
      Map<String, Object> commandMap = new LinkedHashMap<>();
      commandMap.put("Command Type", animation.commands.get(i).type.name());
      commandMap.put("Location", String.format("0x%08X", animation.commands.get(i).location));
      // TODO mark data as monospaced somehow?
      commandMap.put("Data", toHexString(animation.commands.get(i).data));
      commands.add(commandMap);
    }
    map.put("commands", commands);

    map.put("frameToHitboxes", animation.frameToHitboxes);

    Map<String, Object> stats = new LinkedHashMap<>();
    stats.put("internalName", animation.internalName);
    stats.put("subActionId", animation.subActionId);
    stats.put("frameCount", animation.frameCount);
    stats.put("motherCommand.undefined0x10", String.format("0x%08X", animation.motherCommand.undefined0x10));
    stats.put("motherCommand.undefined0x14", String.format("0x%08X", animation.motherCommand.undefined0x14));
    stats.put("ajDataHeader.undefined0x10", String.format("0x%08X", animation.ajDataHeader.undefined0x10));
    stats.put("ajDataHeader.undefined0x14", String.format("0x%08X", animation.ajDataHeader.undefined0x14));
    stats.put("ajDataHeader.undefined0x18", String.format("0x%08X", animation.ajDataHeader.undefined0x18));
    stats.put("ajDataHeader.undefined0x1C", String.format("0x%08X", animation.ajDataHeader.undefined0x1C));
    map.put("stats", stats);

    map.put("description", animation.description);

    return map;
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
    //objectMapper.writeValue(new File("json/animations.json"), charactersToAnimations);
    objectMapper.writeValue(new File("json/animations.json"),
        charactersToAnimations.entrySet().stream()
          .map(entry -> new AbstractMap.SimpleEntry(entry.getKey().name(), entry.getValue().stream()
              .map(animation -> convertAnimation(animation))
              .collect(Collectors.toList())
          ))
          .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
    );
  }
}
