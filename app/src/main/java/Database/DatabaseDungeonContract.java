package Database;

public final class DatabaseDungeonContract {
    public DatabaseDungeonContract(){}
    public static class ContractEntry {
        public static final String TABLE_NAME = "dungeon";
        public static final String DUNGEONID = "dungeon_id";
        public static final String MAXHEALTH = "max_health";
        public static final String HEALTH = "health";
        public static final String ULTIMATEREWARD = "ultimate_reward";
        public static final String ULTIMATEFAILURE = "ultimate_failure";
        public static final String REGULARREWARD = "regular_reward";
        public static final String REGULARPENALTY = "regular_penalty";
        public static final String DIFFICULTY = "difficulty";
        public static final String HEROMODE = "hero_mode";
    }
}
