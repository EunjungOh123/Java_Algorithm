package Practice7_Stream;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Ramda {
    public static void main(String[] args) {
        Predicate <Integer> p = i -> i % 2 == 0; // 짝수인지 아닌지 판별
        Supplier<Integer> s = () -> (int) (Math.random()*100) +1; // 1부터 100까지 임의의 수
        Consumer<Integer> c = i -> System.out.println(i); // 요소 출력
        Function<Integer,Integer> f = i -> i/10 * 10; // 일의 자리 없애기
        List<Integer> list = new ArrayList<>();
        makeRandomList(s, list);
        System.out.println(list);
        printEvenNum(p,c,list);
        List<Integer> newList = doSomething(f,list);
        System.out.println(newList);
    }

    public static <T> List<T> doSomething (Function<T,T> f, List<T> list) {
        List<T> newList = new ArrayList<>(list.size());
        for(T i : list) {
            newList.add(f.apply(i));
        }
        return newList;
    }
    public static <T> void makeRandomList (Supplier<T> s, List<T> list) {
        for(int i = 0; i<10; i++) {
            list.add(s.get());
        }
    }
    public static <T> void printEvenNum (Predicate<T> p, Consumer<T> c, List<T> list) {
        for(T i : list) {
            if(p.test(i)) {
                c.accept(i);
            }
        }
    }
}

