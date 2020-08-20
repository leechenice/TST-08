package single;

//懒汉模式
public class LazyMan {
    private static volatile LazyMan lazyMan;
    private LazyMan() {
        System.out.println(Thread.currentThread().getName()+ "ok");
    }
    public static LazyMan getInstance() {
        if(lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();/*
                    要使用volatile防止指令重排序 保证原子性
                    创建对象顺序：
                    1.分配内存空间
                    2.执行构造方法，初始化对象
                    3.将对象指向这个内存空间
                    */
                }
            }
        }
        return lazyMan;//未完成构造 执行
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan.getInstance();
            }).start();
        }
    }

}
