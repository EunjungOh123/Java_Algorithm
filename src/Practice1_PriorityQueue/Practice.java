package Practice1_PriorityQueue;

import java.util.PriorityQueue;

public class Practice {
    public static void main(String[] args) {
        String [] name = {"양의지","박건우","박세혁","김인태","장원준"};
        int [] age= {87, 90, 90, 94, 85};
        char [] gender = {'M','M','M','M','M'};
        solution(name, age, gender);
    }

    public static void solution (String [] name, int [] age, char [] gender) {
        PriorityQueue<Student> priorityQueue = new PriorityQueue<>();
        for(int i = 0; i< name.length; i++) {
            priorityQueue.offer(new Student(name[i], age[i], gender[i]));
        }
        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll().toString());
        }
    }
}

class Student implements Comparable <Student>{ // 정렬 기준 구현
    String name;
    int age;
    char gender;

    Student (String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }


    @Override
    public int compareTo(Student o) {
        return o.age-this.age;
    }


    @Override
    public String toString() {
        return "이름: "+this.name+" 출생년도: "+this.age+" 성별: "+this.gender;
    }
}