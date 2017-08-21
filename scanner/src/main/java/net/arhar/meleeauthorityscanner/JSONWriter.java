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

  /*private String toHexString(byte[] data) {
    StringBuilder builder = new StringBuilder();
    builder.append("0x");
    for (int i = 0; i < data.length; i++) {
      builder.append(String.format("%02X", data[i]));
    }
    return builder.toString();
  }*/

  // TODO delet this
  private Map<String, Object> convertAnimation(Animation animation) {
    Map<String, Object> map = new LinkedHashMap<>();

    /*List<Map<String, Object>> frameStrip = new ArrayList<>();
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
    map.put("frameStrip", frameStrip);*/
    map.put("frameStrip", animation.getFrameStrip());

    /*List<Map<String, Object>> commands = new ArrayList<>();
    for (int i = 0; i < animation.commands.size(); i++) {
      Map<String, Object> commandMap = new LinkedHashMap<>();
      commandMap.put("Command Type", animation.commands.get(i).type.name());
      commandMap.put("Location", String.format("0x%08X", animation.commands.get(i).location));
      // TODO mark data as monospaced somehow?
      commandMap.put("Data", toHexString(animation.commands.get(i).data));
      commands.add(commandMap);
    }
    map.put("commands", commands);*/
    map.put("commands", animation.getCommands());

    map.put("frameToHitboxes", animation.frameToHitboxes);

    map.put("subActionId", animation.subActionId);
    map.put("internalName", animation.internalName);

    map.put("stats", animation.getStats());

    map.put("description", animation.description);

    return map;
  }

  public void write(
      Map<Character, Map<Attribute, Number>> charactersToAttributes,
      Map<Character, List<Animation>> charactersToAnimations) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    objectMapper.writeValue(new File("json/charIdToName.json"),
        Arrays.stream(Character.values())
          .collect(Collectors.toMap(
              character -> character.name(),
              character -> character.fullName)));

    // TODO charIdToAttributes

    objectMapper.writeValue(new File("json/charIdToSubactionIdToInfo.json"),
        charactersToAnimations.entrySet().stream()
          .collect(Collectors.toMap(
              entry -> entry.getKey().name(),
              entry -> entry.getValue().stream()
                .collect(Collectors.toMap(
                    animation -> animation.subActionId,
                    animation -> animation.getInfo())))));


    objectMapper.writeValue(new File("json/charIdToSubactionIdToStats.json"),
        charactersToAnimations.entrySet().stream()
          .collect(Collectors.toMap(
              entry -> entry.getKey().name(),
              entry -> entry.getValue().stream()
                .collect(Collectors.toMap(
                    animation -> animation.subActionId,
                    animation -> animation.getStats())))));

    objectMapper.writeValue(new File("json/charIdToSubactionIdToFrameStrip.json"),
        charactersToAnimations.entrySet().stream()
          .collect(Collectors.toMap(
              entry -> entry.getKey().name(),
              entry -> entry.getValue().stream()
                .collect(Collectors.toMap(
                    animation -> animation.subActionId,
                    animation -> animation.getFrameStrip())))));

    // TODO remove these jsons in favor of smaller ones above
    objectMapper.writeValue(new File("json/characters.json"),
        Arrays.stream(Character.values())
          .collect(Collectors.toMap(character -> character.name(), character -> character.fullName)));
    objectMapper.writeValue(new File("json/attributeKeys.json"), // TODO this is gross
        Attribute.getViewOrder());
    objectMapper.writeValue(new File("json/attributeDefinitions.json"),
        Arrays.stream(Attribute.values())
          .collect(Collectors.toMap(
              attribute -> attribute.name(),
              attribute -> ImmutableMap.of(
                "fullName", attribute.fullName,
                "viewCategory", attribute.viewCategory.name()))));
    objectMapper.writeValue(new File("json/attributes.json"), charactersToAttributes);
    objectMapper.writeValue(new File("json/animations.json"),
        charactersToAnimations.entrySet().stream()
          .map(entry -> new AbstractMap.SimpleEntry(entry.getKey().name(), entry.getValue().stream()
              .map(animation -> convertAnimation(animation))
              .collect(Collectors.toList())
          ))
          .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
    );

    // map<entryId, map<columnId, value>>
    // map<charId-animationId, map<statName, statValue>>
    // statName/statValue also needs Character->charname and Animation->animationname
    /*Map<String, Map<String, String>> entryIdToStatNameToStatValue = new LinkedHashMap<>();
    objectMapper.writeValue(new File("json/move-stats.json"),
        charactersToAnimations.entrySet().stream()
          .map(entry -> */
  }
}
