package co.coldflow.depot_music;

public class Account {
    private String name;
    private int age;
    private String nickName;

    public Account(String name, int age, String nickName) {
        this.name = name;
        this.age = age;
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
