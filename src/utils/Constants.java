package utils;

import main.Game;

public class Constants {
    public static class UserInterface {
        public static class Buttons{
            public static final int BUTT0N_WIDTH_DEFUALT = 140;
            public static final int BUTT0N_HEIGHT_DEFUALT = 56;
            public static final int BUTT0N_WIDTH = (int) (BUTT0N_WIDTH_DEFUALT * Game.SCALE);
            public static final int BUTT0N_HEIGHT = (int) (BUTT0N_HEIGHT_DEFUALT * Game.SCALE);
        }
        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }
        public static class URMbuttons {
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);
        }
        public static class VolumeButtons {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;
            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }

    }


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
        public static final int FALLING = 5;
        public static final int SIT = 4;
        public static final int DYING = 7;

        public static int getSpriteAmount(int action) {
            return switch (action) {
                case RUNNING, RUN_N_GUN -> 6;
                case IDLE -> 8;
                case JUMP -> 3;
                case SIT -> 2;
                case DYING -> 4;
                //case FALLING -> 5;
                default -> 1;
            };
        }
    }
}
