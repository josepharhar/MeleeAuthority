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

  private String toObject(String... values) {
    return toObject(Arrays.asList(values));
  }

  private String toObject(List<String> values) {
    StringBuilder builder = new StringBuilder();
    builder.append("{\n");
    for (int i = 0; i < values.size(); i += 2) {
      String format;
      if (i == values.size() - 2) {
        format = "\"%s\": %s\n";
      } else {
        format = "\"%s\": %s,\n";
      }
      builder.append(String.format(format, values.get(i), values.get(i + 1)));
    }
    builder.append("}");
    return builder.toString();
  }

  private String toArray(List<String> values) {
    StringBuilder builder = new StringBuilder();
    builder.append("[\n");
    for (int i = 0; i < values.size(); i++) {
      String format;
      if (i == values.size() - 1) {
        format = "%s\n";
      } else {
        format = "%s,\n";
      }
      builder.append(String.format(format, values.get(i), values.get(i + 1)));
    }
    builder.append("]");
    return builder.toString();
  }

  public void write(
      Map<Character, Map<Attribute, Number>> charactersToAttributes,
      Map<Character, List<Animation>> charactersToAnimations) throws IOException {
    BufferedWriter writer;

    writer = Files.newBufferedWriter(Paths.get("json/characters.json"));
    writer.write(toObject(Arrays.stream(Character.values())
          .flatMap(character -> Stream.of(character.name(), "\"" + character.fullName + "\""))
          .collect(Collectors.toList())));

    writer = Files.newBufferedWriter(Paths.get("json/attributes.json"));
    writer.write(toObject(Arrays.stream(Attribute.values())
          .flatMap(attribute -> Stream.of(
              attribute.name(),
              toObject(
                "fullName", "\"" + attribute.fullName + "\"",
                "viewCategory", "\"" + attribute.viewCategory.name() + "\"")))
          .collect(Collectors.toList())));

    writer = Files.newBufferedWriter(Paths.get("json/character_attributes.json"));
    writer.write(toObject(charactersToAttributes.entrySet().stream()
          .flatMap(characterAndAttributes -> Stream.of(
              characterAndAttributes.getKey().name(),
              toObject(characterAndAttributes.getValue().entrySet().stream()
                .flatMap(attributeToNumber -> Stream.of(
                    attributeToNumber.getKey().name(),
                    attributeToNumber.getValue().toString()))
                .collect(Collectors.toList()))))
          .collect(Collectors.toList())));

    writer = Files.newBufferedWriter(Paths.get("json/animations.json"));
    writer.write(toObject(charactersToAnimations.entrySet().stream()
          .flatMap(characterAndAnimations -> Stream.of(
              characterAndAnimations.getKey().name(),
              toObject(characterAndAnimations.getValue().stream()
                .flatMap(animation -> Stream.of(
                    "subActionId", String.valueOf(animation.subActionId),
                    "internalName", "\"" + animation.internalName + "\"",
                    //"description", "\"" + animation.description + "\""))
                    "description", "\"" + animation.description.description + "\"",
                    "animationCategory", "\"" + animation.description.category.name() + "\"",
                    "viewCategory", "\"" + animation.description.viewCategory.name() + "\"",
                    "hitboxes", toArray(animation.frameToHitboxes.entrySet().stream()
                      .flatMap(frameAndHitbox -> frameAndHitbox.getValue().stream())
                      .flatMap(frameAndHitbox -> toObject(
                          "Frame", frameAndHitbox.getKey(),
                          "ID", String.valueOf(frameAndHitbox.getValue().id),
                          "Bone", String.valueOf(frameAndHitbox.getValue().bone),
                          "Damage", String.valueOf(frameAndHitbox.getValue().damage),
                          "Z Offset", String.valueOf(frameAndHitbox.getValue().zoffset),
                          "Y Offset", String.valueOf(frameAndHitbox.getValue().yoffset),
                          "X Offset", String.valueOf(frameAndHitbox.getValue().xoffset),
                          "Angle", String.valueOf(frameAndHitbox.getValue().angle),
                          "Knockback Scaling", String.valueOf(frameAndHitbox.getValue().knockbackScaling),
                          "Fixed Knockback", String.valueOf(frameAndHitbox.getValue().fixedKnockback),
                          "Base Knockback", String.valueOf(frameAndHitbox.getValue().baseKnockback),
                          "Shield Damage", String.valueOf(frameAndHitbox.getValue().shieldDamage)))
                      .collect(Collectors.toList())),
                    "frameStrip", toArray(animation.frameStrip.stream()
                      .flatMap(frame -> toObject(
                          "Hitbox", String.valueOf(frame.hitbox),
                          "IASA", String.valueOf(frame.iasa),
                          "Autocancel", String.valueOf(frame.autocancel)))
                      .collect(Collectors.toList()))))
                    // TODO animation commands here
                  .collect(Collectors.toList()))))
          .collect(Collectors.toList())));

    writer = Files.newBufferedWriter(Paths.get("json/animation_command_types.json"));

    writer = Files.newBufferedWriter(Paths.get("json/character_animation_commands.json"));

    writer = Files.newBufferedWriter(Paths.get("json/frame_strips.json"));

    writer = Files.newBufferedWriter(Paths.get("json/hitboxes.json"));
  }
}
