SuikodenRM
==========

SuikodenRM is a game created by the SuikodenRM engine, under development by Orangee Games.

### How to create your own map

Look at the example maps (inner1.tmx and outer1.tmx). The layers that are used are:

Tile layers:
- foreground: The user will always be behind anything here.
- background: The user will always be on top of anything here.
- 2.5: Objects. When the user stands infront of the lowest point, it will be infront of it, else it will be behind it.

Object layers:
- characters: The interactable characters. Has to be square. Has "character" as required custom property.
- spawns: A spawn-place. Has to be square. Has "spawnNumber" as required custom property.
- doors: Going to a spawn-place. Has to be square. Has "toName" (mapName) and "toSpawnNumber" as required custom property.
- chests: Creating a chest. Has to be square. For now, useless. A object layer.
- box2D: Used in collision detection.

### How to create your own characters

Look at how the previous ones were created (Leknaat and Killey as examples) and you'll easily see how to create your own character. The images needs to be packed with "MyPacker.java" which is found under utilities. You always have to pack all the images used, so if you want new images, just add them to the "core/sprites"-folder and then run "MyPacker.java" (make sure you have the correct path). Add the atlases that's generated under "core/sprites/atlas" to "core/assets/data".

Then it's just adding them to your .tmx with adding a new square into the "characters" layer and setting the name to your character name. Finally you also have to add the name to the "CharacterGeneration.java"-file.

### Contact

If you want to tell me anything, please mail me at OrangeeGames [at] gmail.com or go to http://www.suikosource.com/phpBB3/viewtopic.php?f=68&t=13972 and send me a reply.



