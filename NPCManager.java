import java.util.ArrayList;

public class NPCManager {
    public static ArrayList<NPC> npcs = new ArrayList<>();


    public static NPC eliasNPC;

    // Cutscene
    public static boolean ashHollowCutscenePlayed = false;
    public static boolean marshalCutscenePlayed = false;
    public static boolean eliasMineCutscenePlayed = false;
    public static boolean eliasThornCutscenePlayed = false;
    public static boolean kestrelRescueCutscenePlayed = false;
    public static boolean ashHollowInnWakeupPlayed = false;
    public static boolean forestIntroCutscenePlayed = false;
    public static boolean tessaraCutscenePlayed = false;
    public static boolean tessaraCyrillMeetingPlayed = false;
    public static boolean crystalChamberIntroPlayed = false;
    public static boolean cyrillCutscenePlayed = false;
    public static boolean cyrillSpawnedInCavern = false;
    public static boolean ghostTownIntroPlayed = false;
    public static boolean cassianCutscenePlayed = false;




    public static NPC cyrillNPC = null;

    // Mechanics
    public static boolean minesUnlocked = false;
    public static boolean starStolen = false;
    public static boolean eliasFollowing = false;
    public static boolean forestUnlocked = false;
    public static boolean crystalchamberUnlocked = false;
    public static boolean cyrillFreed = false;
    public static boolean ghostTownUnlocked = false;
    public static boolean tessaraMovedToCavern = false;




    public static void interactWithMarshalKestrel(Player player) {
        CutScene.playMarshalKestrelCutscene(player);
    }

    public static void interactWithTessara(Player player) {
        CutScene.playTessaraIntro(player);
    }

    public static void interactWithCassian(Player player) {
        CutScene.playCassianIntro(player);
    }

    public static void updateEliasFollower(Player player) {

        // Stop following after betrayal
        if (eliasThornCutscenePlayed) {
            eliasFollowing = false;
            return;
        }

        // Find Elias NPC
        NPC elias = null;
        for (NPC npc : npcs) {
            if (npc.getName().equals("Elias Thorn")) {
                elias = npc;
                break;
            }
        }
        if (elias == null) return;

        // Only follow in mine-related maps
        int map = player.getMapID();
        boolean inMines =
                map == 4 || map == 28 || map == 29 ||
                        map == 30 || map == 31 || map == 32;

        if (!inMines) return;

        // Make Elias teleport to the player's previous position
        // This avoids collision problems and keeps Elias right behind you
        int px = player.getX();
        int py = player.getY();

        // Distance check — if too far, snap him closer
        int dx = Math.abs(px - elias.getX());
        int dy = Math.abs(py - elias.getY());

        if (dx > 2 || dy > 2) {
            elias.setMapID(map);
            elias.setPosition(px, py - 1); // appears behind player
            return;
        }

        // Simple follow movement
        if (elias.getX() < px) elias.move(1, 0);
        else if (elias.getX() > px) elias.move(-1, 0);
        else if (elias.getY() < py) elias.move(0, 1);
        else if (elias.getY() > py) elias.move(0, -1);

        // Ensure map consistency
        elias.setMapID(map);
    }



    public static void moveEliasBeforePlayer(Player player) {

        if (!eliasFollowing) return;
        NPC elias = eliasNPC;

        // Elias must be on same map
        if (elias == null || elias.getMapID() != player.getMapID()) return;

        int px = player.getX();
        int py = player.getY();

        // Move Elias up BEFORE player moves up
        elias.setPosition(px, py - 2);
    }




    public static void initNPCs() {
        npcs.clear(); // <<< VERY IMPORTANT!

        // Marshal
        npcs.add(new NPC(
                "Marshal Kestrel",
                'K',
                2,
                2, 3,
                ""
        ));

        // Elias (stored directly)
        eliasNPC = new NPC(
                "Elias Thorn",
                'E',
                2,
                6, 8,
                ""
        );
        npcs.add(eliasNPC);

        // Merchant
        npcs.add(new NPC(
                "The Merchant",
                'S',
                2,
                5, 1,
                "Welcome! Everything is for sale, but choose wisely."
        ));

        // Inn
        npcs.add(new NPC(
                "Inn Manager",
                'I',
                2,
                7, 1,
                "Welcome! Please enjoy your stay."
        ));

        // Blacksmith
        npcs.add(new NPC(
                "BlackSmith",
                'F',
                2,
                9, 1,
                "Wanna upgrade something?"
        ));

        // Tessara Windspire (Forest1)
        if (!tessaraMovedToCavern) {
            npcs.add(new NPC(
                    "Tessara Windspire",
                    'T',
                    3, 4, 4,
                    "“The forest speaks… if you’re willing to listen.”"
            ));
        }

        // Cassian “Ash” Vale — Ghost Town NPC
        npcs.add(new NPC(
                "Cassian 'Ash' Vale",
                'C',
                13,      // Ghost Town map ID
                3, 3,    // position (adjust if needed)
                ""       // dialogue handled by cutscene
        ));





    }


    // Spawn Cyrill ONLY if freed (after her boss fight)
    public static void checkTessaraCyrillMeeting(Player player) {

        if (tessaraCyrillMeetingPlayed) return;

        // Condition:
        // Player is in Forest Core (map 6) AND Cyrill has been freed AND Tessara quest is active
        if (player.getMapID() == 6 &&
                cyrillSpawnedInCavern &&
                player.tessaraQuestStarted &&
                !player.tessaraQuestCompleted) {

            CutScene.playTessaraCyrillReunion(player);
            tessaraCyrillMeetingPlayed = true;
            player.tessaraQuestCompleted = true;
        }
    }


    public static void spawnCyrillInCrystalCavern() {

        if (cyrillSpawnedInCavern) return;
        cyrillSpawnedInCavern = true;

        cyrillNPC = new NPC(
                "Cyrill",
                'C',
                10,
                5, 4,
                "The corruption is gone… I can finally think again."

        );

        npcs.add(cyrillNPC);
    }



}
