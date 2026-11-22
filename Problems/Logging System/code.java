import code.Singleton;

class Singleton {
    private static final Singleton INSTANCE;

    private Singleton() {
        INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        if(INSTANCE == null) {
            
        }
    }
}