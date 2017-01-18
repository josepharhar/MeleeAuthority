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

    /*writer = Files.newBufferedWriter(Paths.get("json/characters.json"));
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
          .collect(Collectors.toList())));*/

    //writer = Files.newBufferedWriter(Paths.get("json/character_attributes.json"));
    /*writer.write(toObject(charactersToAttributes.entrySet().stream()
          .flatMap(characterAndAttributes -> Stream.of(
              characterAndAttributes.getKey().name(),
              toObject(characterAndAttributes.getValue().entrySet().stream()
                .flatMap(attributeToNumber -> Stream.of(
                    attributeToNumber.getKey().name(),
                    attributeToNumber.getValue().toString()))
                .collect(Collectors.toList()))))
          .collect(Collectors.toList())));*/
    /*List<String> characterAttributes = new LinkedList<>();
    for (Map.Entry<Character, Map<Attribute, Number>> characterAndAttributes : charactersToAttributes.entrySet()) {
      List<String> attributes = new LinkedList<>();
      for (Map.Entry<Attribute, Number> attributeAndValue : characterAndAttributes.getValue().entrySet()) {
        attributes.add(attributeAndValue.getKey().name());
        attributes.add(attributeAndValue.getValue().toString());
      }

      characterAttributes.add(toObject(
            characterAndAttributes.getKey().name(),
            toObject(attributes)));
    }
    writer.write(toObject(characterAttributes));*/

    /*writer = Files.newBufferedWriter(Paths.get("json/animations.json"));
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
                      .flatMap(frameAndHitboxes -> {
                        return frameAndHitboxes.getValue().stream()
                          .map(hitbox -> new SimpleEntry<Integer, Hitbox>(frameAndHitboxes.getKey(), hitbox));
                      })
                      .map(frameAndHitbox -> toObject(
                          "Frame", String.valueOf(frameAndHitbox.getKey()),
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
                      .map(frame -> toObject(
                          "Hitbox", String.valueOf(frame.hitbox),
                          "IASA", String.valueOf(frame.iasa),
                          "Autocancel", String.valueOf(frame.autocancel)))
                      .collect(Collectors.toList()))))
                    // TODO animation commands here
                  .collect(Collectors.toList()))))
          .collect(Collectors.toList())));

    writer = Files.newBufferedWriter(Paths.get("json/animation_command_types.json"));*/
  }
}
