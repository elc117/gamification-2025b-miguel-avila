public class Achievement {
    private String name;
    private String description;
    private int tier; // 0: bronze, 1: silver, 2: gold ou algo assim

    public Achievement(String name, String description) {
        this.name        = name;
        this.description = description;
        this.tier        = 0;
    }

    // name
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    // description
    public String getDescription() {
        return this.description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    // tier
    public int getTier() {
        return this.tier;
    }

    private void setTier(int tier) {
        this.tier = tier;
    }

    // aumenta o nível de uma conquista
    // retorna o nível do novo tier
    private int upgradeTier() {
        if (this.tier < 2) this.tier = this.tier+1;
        return this.tier;
    }
}
