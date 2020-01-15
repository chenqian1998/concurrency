package CAS;

import java.util.concurrent.atomic.AtomicReference;

class User{
    String username;
    int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user1 = new User("cq",22);
        User user2 = new User("lkk",18);


        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(user1);

        System.out.println(userAtomicReference.compareAndSet(user1, user2)+"\t"+userAtomicReference.get().toString());
    }
}
