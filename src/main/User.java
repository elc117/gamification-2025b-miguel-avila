public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private int points;
    // private List<Achievement> achievements;
    // private List<Achievement> displayedAchievements;
    // private Watchlist watchlist;

    public User(String username) {
        this.id = 0;
        this.username = username;
    }

    // id
    public int getId() {
        return this.id;
    }
    
    private void setId(int id) {
        this.id = id;
    }

    // name
    public String getName() {
        return this.name;
    }
    
    private void setName(String name) {
        this.name = name;
    }
    
    // username 
    public String getUsername() {
        return this.username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    //email
    public String getEmail() {
        return this.email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    // passwords
    public String getPassword() {
        return this.password;
    }

    private void setPassword(String password){
        this.password = password;
    }

    // points
    public int getPoints() {
        return this.points;
    }
    
    private void setPoints(int points) {
        this.points = points;
    }

}