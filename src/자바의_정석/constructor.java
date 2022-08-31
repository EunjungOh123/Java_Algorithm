package 자바의_정석;

public class constructor {
    public static void main(String[] args) {
        Car c1 = new Car();
        Car c2 = new Car("White","Manual",3500);
        Car c3 = new Car(c1); // c1을 복사하여 생성 > c1과 c3은 서로 독립적으로 존재하는 별도의 인스턴스
        Car c4 = new Car(c2); // c2를 복사하여 생성 > c2과 c4은 서로 독립적으로 존재하는 별도의 인스턴스
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
    }
}

class Car {
    String color;
    String gearType;
    int price;

    Car() {
        this("Black","Auto",4000); // 기본 생성자에서 다른 생성자 호출
    }
    Car(String color, String gearType, int price) { // 매개변수가 있는 생성자
        this.color = color;
        this.gearType = gearType;
        this.price = price;
    }
    Car(Car c) { // 참조변수를 매개변수로 선언한 생성자 > 인스턴스의 복사
        this(c.color, c.gearType, c.price);
    }

    @Override
    public String toString() {
        return  "color= " + color + " | gearType= " + gearType + " | price= " + price;
    }
}