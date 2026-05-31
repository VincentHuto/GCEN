# Gluttonous Canines Eating Nightshades

**Gluttonous Canines Eating Nightshades** is a NeoForge mod about hungry dogs, spaghetti, and tomato gardening.

The mod adds custom dog breeds that interact with the new food blocks in different ways: Labradors love stealing ripe tomatoes, while Chihuahuas will hunt down placed spaghetti piles. Both dogs are tamed with spaghetti instead of bones.

## Why This Mod Exists

Because dogs and spaghetti are apparently a cursed combination, and tomato-stealing dogs deserve representation too.

<div class="tenor-gif-embed" data-postid="27107504" data-share-method="host" data-aspect-ratio="1.33333" data-width="100%"><a href="https://tenor.com/view/dog-vomiting-spaghetti-gif-27107504">Dog Vomiting Spaghetti GIF</a>from <a href="https://tenor.com/search/dog+vomiting+spaghetti-gifs">Dog Vomiting Spaghetti GIFs</a></div> <script type="text/javascript" async src="https://tenor.com/embed.js"></script>

<div class="tenor-gif-embed" data-postid="11860295" data-share-method="host" data-aspect-ratio="1" data-width="100%"><a href="https://tenor.com/view/dog-spaghetti-coughing-choking-spaghettidog-gif-11860295">Dog Spaghetti GIF</a>from <a href="https://tenor.com/search/dog-gifs">Dog GIFs</a></div> <script type="text/javascript" async src="https://tenor.com/embed.js"></script>

<div class="tenor-gif-embed" data-postid="24024621" data-share-method="host" data-aspect-ratio="0.5625" data-width="100%"><a href="https://tenor.com/view/dog-eating-tomato-dog-tomato-dog-eating-farmer-dog-gif-24024621">Dog Eating Tomato Dog GIF</a>from <a href="https://tenor.com/search/dog+eating+tomato-gifs">Dog Eating Tomato GIFs</a></div> <script type="text/javascript" async src="https://tenor.com/embed.js"></script>

## Mod Info

- **Mod ID:** `gcen`
- **Version:** `1.0.0`
- **Loader:** NeoForge
- **Target Minecraft/NeoForge line:** `26.1.2`
- **Package:** `com.vincenthuto.gcen`

## Features

### Dogs

#### Labrador Dog

- Custom wolf-like dog entity.
- Larger body and custom Labrador model/texture.
- Custom spawn egg.
- Tamed with **spaghetti**, not bones.
- Supports wolf-style collars and armor behavior.
- Has a custom collar render layer so collars align with the Labrador model.
- Searches for fully grown tomato crops and eats the tomatoes off the plant.

When a Labrador eats a mature tomato crop, it does **not** break the crop. Instead, it resets the crop from age `3` back to age `2`, similar to harvesting berries from a bush.

#### Chihuahua Dog

- Custom small wolf-like dog entity.
- Smaller body with a larger head silhouette.
- White Chihuahua texture.
- Custom spawn egg.
- Tamed with **spaghetti**, not bones.
- Searches for placed spaghetti pile blocks and eats them.

When a Chihuahua eats a spaghetti pile, the placed block is removed without dropping an item.

## Food and Farming

### Spaghetti

- Edible food item.
- Can also be placed as a decorative **spaghetti pile** block.
- Used to tame Labradors and Chihuahuas.
- Placed spaghetti piles can be eaten by Chihuahuas.
- Crafted shapelessly with **steak**, **wheat**, and a **tomato**.

Spaghetti recipe:

```text
minecraft:cooked_beef + minecraft:wheat + gcen:tomato -> gcen:spaghetti
```

### Tomatoes

- Edible tomato item.
- Tomato seeds plant a custom tomato crop.
- Tomato crops have four growth stages: `0` through `3`.
- Fully grown tomato crops can be harvested by players.
- Labradors can also eat ripe tomatoes from the plant, resetting the crop to stage `2`.

## Creative Tab

The mod adds a creative tab named **Gluttonous Canines Eating Nightshades** containing:

- Labrador Dog Spawn Egg
- Chihuahua Dog Spawn Egg
- Spaghetti
- Tomato
- Tomato Seeds

## Gameplay Notes

- Use **spaghetti** to tame both custom dog breeds.
- Bones intentionally do not tame these dogs.
- Newly tamed dogs may sit like vanilla wolves; make them stand if you want their food-seeking goals to run.
- Labradors look for mature tomato crops nearby.
- Chihuahuas look for placed spaghetti piles nearby.
- Dog food-seeking goals do not run while the dog is sitting, angry, leashed, riding, or targeting something.

## Installation

1. Install the matching NeoForge version for your Minecraft setup.
2. Build or download the mod jar.
3. Place the jar into your Minecraft `mods` folder.
4. Launch the game with NeoForge.

## Building From Source

From the project root on Windows PowerShell:

```powershell
.\gradlew.bat build
```

The built jar will be placed in:

```text
build/libs/
```

Useful development commands:

```powershell
.\gradlew.bat compileJava
.\gradlew.bat build
.\gradlew.bat --refresh-dependencies
.\gradlew.bat clean
```

## Project Structure

Main code lives under:

```text
src/main/java/com/vincenthuto/gcen/
```

Assets and data live under:

```text
src/main/resources/assets/gcen/
src/main/resources/data/gcen/
```

Important areas:

- `entity/` - Labrador and Chihuahua entity classes.
- `entity/ai/` - custom dog food-seeking goals.
- `client/model/` - custom dog models.
- `client/renderer/` - custom dog renderers and render layers.
- `block/` - tomato crop and spaghetti pile blocks.
- `assets/gcen/textures/` - item, block, and entity textures.
- `assets/gcen/models/` - item and block model JSON files.

## License

This project currently uses the license specified in the Gradle properties/template metadata.

## Credits

Created by Vincent Huto.

Built with NeoForge.
