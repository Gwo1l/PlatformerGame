package utils;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants {
        public static final int RUNNING = 1;
        public static final int RUN_N_GUN = 2;
        public static final int IDLE = 0;
        public static final int JUMP = 3;
        public static final int SIT = 4;
        public static final int DYING = 7;

        public static int getSpriteAmount(int action) {
            return switch (action) {
                case RUNNING, RUN_N_GUN -> 6;
                case IDLE -> 8;
                case JUMP -> 3;
                case SIT -> 2;
                case DYING -> 4;
                default -> 1;
            };
        }
    }
}
