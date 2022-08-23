package hello.core.member;

public class Member {
    private Long id;
    private String name;
    private Gradle gradle;

    public Member(Long id, String name, Gradle gradle) {
        this.id = id;
        this.name = name;
        this.gradle = gradle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gradle getGradle() {
        return gradle;
    }

    public void setGradle(Gradle gradle) {
        this.gradle = gradle;
    }
}
