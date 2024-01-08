package helpz;

public class Constants {

    public static class Projectiles{
        public static final int ARROW=0;
        public static final int BOMB=2;
        public static final int CHAINS=1;


        public static float GetSpeed(int type){
            switch(type){
                case ARROW:
                    return 3f;
                case BOMB:
                    return 1f;
                case CHAINS:
                    return 2f;
            }
            return 0f;
        }
    }
    public static class Enemies{
        public static final int ORC=0;
        public static final int BAT=1;
        public static final int KNIGHT=2;
        public static final int WOLF=3;


        public static int GetReward(int enemyType){
            switch(enemyType){
                case ORC:
                    return 5;
                case BAT:
                    return 5;
                case KNIGHT:
                    return 25;
                case WOLF:
                    return 10;
            }
            return 0;

        }

        public static float GetSpeed(int enemyType){
            switch(enemyType){
                case ORC:
                    return 0.5f;
                case BAT:
                    return 0.65f;
                case KNIGHT:
                    return 0.3f;
                case WOLF:
                    return 0.75f;
            }
            return 0;
        }
        public static int GetStartHealth(int enemyType){
            switch(enemyType){
                case ORC:
                    return 50;
                case BAT:
                    return 60;
                case KNIGHT:
                    return 250;
                case WOLF:
                    return 85;
            }
            return 0;

        }
    }

    public static class Towers{
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

        public static int GetTowerCost(int towertype){
            switch (towertype){
                case CANNON:
                    return 65;
                case ARCHER:
                    return 30;
                case WIZARD:
                    return 45;
            }
            return 0;
        }

        public static String GetName(int towertype){
            switch (towertype){
                case CANNON:
                    return "Cannon";
                case ARCHER:
                    return "Archer";
                case WIZARD:
                    return "Wizard";
            }
            return"";
        }

        public static int getStartDmg(int towertype){
            switch (towertype){
                case CANNON:
                    return 25;
                case ARCHER:
                    return 15;
                case WIZARD:
                    return 5;
            }
            return 0;

        }

        public static float getDefaultCooldown(int towertype){
            switch (towertype){
                case CANNON:
                    return 80;
                case ARCHER:
                    return 40;
                case WIZARD:
                    return 10;
            }
            return 0;

        }


    }
}
