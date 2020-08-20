package single;
//饿汉式
public class Hungry {

    private static final Hungry hungry = new Hungry();
    //构造器私有
    private Hungry() {

    }

    public static Hungry getInstance() {
        return hungry;
    }

}
