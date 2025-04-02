

import java.util.ArrayList;

public class WildcardsDemo {

    public static class Student {
        void print() {
            System.out.println("Student");
        }
    }

    public static class EnggStudent extends Student {
        void print() {
            System.out.println("EnggStudent");
        }
    }

    public static class MgmtStudent extends Student {
        void print() {
            System.out.println("MgmtStudent");
        }
    }

    static void printStudents(ArrayList<? extends Student> al) {
        for (Student s : al) {
            s.print();
        }
    }

    public static void main(String[] args) {
        ArrayList<EnggStudent> al1 = new ArrayList<>();

        al1.add(new EnggStudent());
        al1.add(new EnggStudent());

        ArrayList<MgmtStudent> al2 = new ArrayList<>();

        al2.add(new MgmtStudent());

        printStudents(al1);
        printStudents(al2);

    }
}
