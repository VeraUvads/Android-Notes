package oop.ru;

import java.awt.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;


public class OOPPracticeQuestions {
    /**
     * 1) Переопределите equals для ColorPoint
     **/
    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point))
                return false;
            Point p = (Point) o;
            return p.x == x && p.y == y;
        }

        // ... остальная часть кода опущена
    }

    public static class ColorPoint extends Point {
        private final Color color;

        public ColorPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        // ... остальная часть кода опущена

    }


    public static void main(String[] args) {
        new TryCatchTask().start();
    }


    /**
     * 2) Что выведет код?
     **/
    public static class TryCatchTask {

        public void start() {
            try {
                println("try");
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                println("catch");
                throw new NullPointerException();
            } finally {
                println("finally");
                throw new IndexOutOfBoundsException();
            }
        }

    }










    /**
     * 3) Что выведется и в каком порядке?
     **/

    public static class HierarchyTask {

        public static class Parent {
            public void start() {
                println("parent start");
            }
        }

        public static class Child extends Parent {

            public void start() {
                println("child start");
            }
        }

        public static class Launcher {

            public void launch(Parent parent) {
                println("launching parent");
                parent.start();
            }

            public void launch(Child child) {
                println("launching child");
                child.start();
            }
        }

        public static void main(String[] args) {
            Parent obj = new Child();
            Launcher launcher = new Launcher();
            launcher.launch(obj);



            Child obj2 = new Child();
            launcher.launch(obj2);
        }

    }


}

