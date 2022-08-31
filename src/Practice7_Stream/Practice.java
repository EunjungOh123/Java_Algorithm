package Practice7_Stream;

// 스트림 : 다양한 데이터 소스를 표준화된 방법으로 다루기 위한 것
// 스트림 만들기 > 중간 연산 > 최종 연산
// 중간 연산: 연산 결과가 스트림인 연산, 스트림에 연속해서 중간 연산할 수 있음
// 최종 연산: 연산 결과가 스트림이 아닌 연산, 스트림의 요소를 소모하므로 단 한 번만 가능

/*
 스트림은 데이터 소스를 변경하지 않는다
 스트림은 Iterator 처럼 일회용이다 (필요 시 스트림 다시 생성해야 한다)
 스트림은 작업을 내부 반복으로 처리한다
 최종 연산 전까지 중간 연산이 수행되지 않는다 (지연 연산) > 중간 연산을 호출해도 즉각적인 연산이 수행되는 것은 아니다
 중간 연산을 호출하는 것은 단지 어떤 작업이 수행되어야 하는지 지정해주는 것일 뿐이다
 최종 연산이 수행되어야 비로소 스트림의 요소들이 중간 연산을 거쳐 최종 연산에서 소모된다
*/

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Practice {
    public static void main(String[] args) {
        // 스트림 생성 (컬렉션)
        List<Integer> intList = Arrays.asList(3,2,5,6,1,7,10,9,8,12,20,16,15);
        Stream <Integer> intStream1 = intList.stream();
        intStream1.forEach((i)-> System.out.print(i+" "));
        System.out.println();
        // 스트림 생성 (배열)
        Stream<Integer> intStream2 = Stream.of(1,2,3,4,5,6,7,8,9);
        intStream2.forEach((i)-> System.out.print(i+" "));
        System.out.println();
        // 기본형 스트림 생성
        int [] intArr = new int[] {1,2,3,4,5,6,7,8,9};
        IntStream intStream3 = Arrays.stream(intArr);
        intStream3.forEach((i)-> System.out.print(i+" "));
        System.out.println();
        // 임의의 수를 요소로 갖는 스트림 생성하기 > IntStream : ints(), LongStream : longs(), DoubleStream : doubles()
        // 해당 메서드들이 반환하는 스트림은 크기가 정해지지 않은 무한 스트림이므로 limit() 사용해서 스트림 크기 제한해야 한다
        IntStream random1 = new Random().ints(1, 46); // 1부터 45까지 범위 제한
        random1.limit(6).distinct().forEach((i)-> System.out.print(i+" ")); // 중복된 요소들 제거, 6개 요소만 출력

        System.out.println();
        List<Student> list = new ArrayList<>();
        list.add(new Student("김윤수",22, 905));
        list.add(new Student("이주현",24, 810));
        list.add(new Student("박지연",26, 970));
        list.add(new Student("정주원",21, 880));
        list.add(new Student("강이준",20, 760));
        list.add(new Student("민지현",28, 845));
        Stream<Student> stream1 = list.stream(); // 점수 내림차순 정렬
        stream1.sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.score-o1.score;
            }
        }).forEach((student) -> System.out.println(student));
        System.out.println();
        Stream<Student> stream2 = list.stream(); // 나이 오름차순 정렬
        stream2.sorted((s1, s2) -> s1.age-s2.age).forEach((student) -> System.out.println(student));
        // map() : 스트림의 요소에 저장된 값 중에서 원하는 필드만 뽑아내거나 특정 형태로 변환해야 할 때 사용
        // flatMap() : Stream<T[]>를 Stream<T>로 변환할 때 사용

        String [] lineArr = {"Believe or not It is true", "Do or do not There is no try"};
        Stream <String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line ->Stream.of(line.split(" +"))).map(String :: toLowerCase)
                .distinct().sorted().forEach(System.out::println);
        try {
            // 예외가 발생할 가능성이 있는 문장들을 넣는다
        } catch (Exception e) {
            // Exception 발생했을 경우, 이를 처리하기 위한 문장을 넣는다
        }
    }
}

class Student {
    String name;
    int age;
    int score;

    Student (String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "이름: "+ name + " 나이: " + age + " 점수: " + score;
    }
}


/*
<기본형 스트림> : IntStream, LongStream, DoubleStream
데이터 소스의 요소를 기본형으로 다루는 스트림
오토박싱과 언박싱의 비효율이 제거된다 (Stream <Integer>보다 IntStream 사용)
Stream<T>보다 숫자와 관련된 유용한 메서드를 포함하고 있다 (sum, count, average 등)
 */

/*
<중간 연산>
스트림 자르기 : skip(), limit()
ex) 10개의 요소를 가지고 있는 스트림에 skip(4)와 limit(5)를 적용하면 처음 4개의 요소를 건너뛰고 5개의 요소를 가진 스트림 반환
스트림의 요소 걸러내기 : filter(), distinct()
filter()는 중복된 요소들을 제거하고 filter()는 주어진 조건에 맞지 않는 요소들을 걸러낸다
스트림 정렬 : sorted()
 */