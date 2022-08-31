package 자바의_정석;

// 기본형 매개변수와 참조형 매개변수

public class parameter {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;
        d.y = 20;
        System.out.println("main(): "+d.x+" "+d.y);
        rvChange(d.x, d.y);
        System.out.println("After change method call");
        System.out.println("main(): "+d.x+" "+d.y);
        System.out.println("===================================");
        System.out.println("main(): "+d.x+" "+d.y);
        pvchange(d);
        System.out.println("After change method call");
        System.out.println("main(): "+d.x+" "+d.y);
    }

    static void rvChange (int a, int b) {
        a = 100;
        b = 80;
        System.out.println("change(): "+a+" "+b);
    }
    static void pvchange (Data data) {
        data.x = 100;
        data.y = 80;
        System.out.println("change(): "+ data.x +" "+data.y);
    }
}

class Data {
    int x;
    int y;
}