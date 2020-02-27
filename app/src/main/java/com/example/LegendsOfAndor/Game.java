package com.example.LegendsOfAndor;

import java.util.ArrayList;

enum TurnOptions{
    Move, Fight, None
}

public class Game {
    private int maxNumPlayers;
    private int currentNumPlayers;
    private int goldenShields;
    private Player[] players;
    private String gameName;
    private boolean isActive;
    private boolean itemsDistributed;
    private String itemsDistributedMessage;
    private RegionDatabase regionDatabase;
    private Hero currentHero;
    private Hero firstHeroInNextDay;
    private TurnOptions currentHeroSelectedOption;

    public Game() {}

    public Game(Player p, int maxNumPlayers, String gameName) {
        this.gameName = gameName;
        this.maxNumPlayers = maxNumPlayers;
        this.currentNumPlayers = 1;
        this.players = new Player[maxNumPlayers];
        this.players[0] = p;
        regionDatabase = new RegionDatabase(Difficulty.Hard); // hardcoded change when add Difficulty attribute
    }

    public int getMaxNumPlayers() {
        return maxNumPlayers;
    }

    public void setMaxNumPlayers(int maxNumPlayers) {
        this.maxNumPlayers = maxNumPlayers;
    }

    public int getCurrentNumPlayers() {
        return currentNumPlayers;
    }

    public void setCurrentNumPlayers(int currentNumPlayers) {
        this.currentNumPlayers = currentNumPlayers;
    }

    public int getGoldenShields() {
        return goldenShields;
    }

    public void setGoldenShields(int goldenShields) {
        this.goldenShields = goldenShields;
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isItemsDistributed() {
        return itemsDistributed;
    }

    public void setItemsDistributed(boolean itemsDistributed) {
        this.itemsDistributed = itemsDistributed;
    }

    public Player getSinglePlayer(String username) {
        for (Player p : players) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public void addPlayer(Player p) {
        for (int i = 0; i < maxNumPlayers; i++) {
            if (players[i] == null) {
                players[i] = p;
                currentNumPlayers++;
                break;
            }
        }
    }

    public void removePlayer(String username) {
        for (int i = 0; i < currentNumPlayers; i++) {
            if (players[i].getUsername().equals(username)) {
                players[i].setHero(null);
                players[i].setReady(false);
                for (int j = i; j < maxNumPlayers; j++) {
                    if (j == maxNumPlayers-1) {
                        players[j] = null;
                    } else {
                        players[j] = players[j + 1]; // shift every element down by one unit
                    }
                }
                currentNumPlayers--;
                return;
            }
        }
    }

    public ArrayList<HeroClass> getAllHeroes() {
        ArrayList<HeroClass> heroes = new ArrayList<>();
        for (int i = 0; i < currentNumPlayers; i++) {
            if (players[i].getHero() != null) {
                heroes.add(players[i].getHero().getHeroClass());
            }
        }
        return heroes;
    }

    public boolean allReady() {
        for (int i = 0; i < currentNumPlayers; i++) {
            if (!players[i].isReady()) {
                return false;
            }
        }
        return true;
    }

    public RegionDatabase getRegionDatabase() {
        return regionDatabase;
    }

    public Hero getCurrentHero() {
        return currentHero;
    }

    public void setCurrentHero(Hero currentHero) {
        this.currentHero = currentHero;
    }

    public Hero getFirstHeroInNextDay() {
        return firstHeroInNextDay;
    }

    public void setFirstHeroInNextDay(Hero firstHeroInNextDay) {
        this.firstHeroInNextDay = firstHeroInNextDay;
    }

    public TurnOptions getCurrentHeroSelectedOption() {
        return currentHeroSelectedOption;
    }

    public void setCurrentHeroSelectedOption(TurnOptions currentHeroSelectedOption) {
        this.currentHeroSelectedOption = currentHeroSelectedOption;
    }

    public void appendToDistributedItemsMessage(String message){
        this.itemsDistributedMessage += message + "\n";
    }

    public String getItemsDistributedMessage(){
        return itemsDistributedMessage;
    }
}
