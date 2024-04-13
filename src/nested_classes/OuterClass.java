package nested_classes;

public class OuterClass {

    public int x = 42;

    public void method1() {

        class LocalClass {
            public void localPrint() {
                System.out.println("In local class");
                System.out.println(x);
            }
        }
        LocalClass lc = new LocalClass();
        lc.localPrint();
    }

    public void method2() {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                System.out.println("In an anonymous local class method");
                System.out.println(x);
            }
        };
        r.run();
    }
    public Runnable r = new Runnable() {

        @Override
        public void run() {
            System.out.println("In an anonymous class method");
            System.out.println(x);
        }
    };
    Object o = new Object() {

        @Override
        public String toString() {
            return "In an anonymous class method";
        }
    };

    public class InnerClass {
        public int x = 43;
        public static final int y = 44;

        public void innerPrint() {
            System.out.println("In a inner class method");
            System.out.println(x);
        }
    }

    public static class StaticNestedClass {

        public void staticNestedPrint() {
            System.out.println("In a static nested class method");
            //compile error
            //System.out.println(x);

        }
    }

    public class A {

        public class B {

            public void method() {
                System.out.println("A.B.method()...");
            }
        }
    }
}