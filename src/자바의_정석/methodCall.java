package 자바의_정석;

// 메서드 호출 과정

public class methodCall {
    public static void main(String[] args) {
        System.out.println("main(String[] args)이 시작되었음");
        firstMethod();
        System.out.println("main(String[] args)이 끝났음");
    }

    static void firstMethod() {
        System.out.println("firstMethod()이 시작되었음");
        secondMethod();
        System.out.println("firstMethod()이 끝났음");
    }
    static void secondMethod() {
        System.out.println("secondMethod()이 시작되었음");
        System.out.println("secondMethod()이 끝났음");
    }
}
