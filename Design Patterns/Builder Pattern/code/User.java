public class User {
    private final String name;
    private final int age;
    private final String email;

    User(UserBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public class UserBuilder {
        private final String name;
        private int age;
        private String email;

        public UserBuilder(String name) {
            this.name = name;
        }

        public UserBuilder age(int age) {
            if (age < 0)
                throw new IllegalArgumentException();
            this.age = age;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
