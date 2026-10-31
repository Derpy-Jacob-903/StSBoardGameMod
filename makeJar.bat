echo "this is the repo"
cd /D "C:\Users\wxpno\Documents\BTD6 Mod Sources\StSBoardGameMod\target\classes"

echo "this points to a 7-Zip install"
"E:\Program Files\7-Zip\7z" a StSBoardGameMod.jar BoardGame\
"E:\Program Files\7-Zip\7z" a StSBoardGameMod.jar BoardGameResources\
"E:\Program Files\7-Zip\7z" a StSBoardGameMod.jar IDCheckStringsDONT-EDIT-AT-ALL.json
"E:\Program Files\7-Zip\7z" a StSBoardGameMod.jar ModTheSpire.json

echo "these point to a Steam install (and Slay The Spire)"
echo "For launching from Steam:"
copy /y StSBoardGameMod.jar "E:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\"

echo "For launching from a .bat file:"
copy /y StSBoardGameMod.jar "E:\Program Files (x86)\Steam\steamapps\workshop\content\646570\1605060445\mods\"

echo "For launching from IntelliJ IDEA:"
copy /y StSBoardGameMod.jar "C:\Users\wxpno\Documents\BTD6 Mod Sources\Dreadbloon-StS\mods"

