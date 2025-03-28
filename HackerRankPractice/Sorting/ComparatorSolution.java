package HackerRankPractice.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem

Given an array of n Player objects, write a comparator that sorts them in order of decreasing score.
If  or more players have the same score, sort those players alphabetically ascending by name.
 */
class Player{
    String name;
    int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
class Checker implements Comparator<Player> {
    // complete this method
    public int compare(Player a, Player b) { //descending by score
        if (a.score > b.score) {
            return -1;
        }
        if (a.score < b.score) {
            return 1;
        }
        // equal score

        return a.name.compareTo(b.name); // string natural ordering
    }
}
public class ComparatorSolution<P> {


    public static void main(String[] args) {

        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Jack", 100));
        playerList.add(new Player("amy", 100));
        playerList.add(new Player("david", 100));
        playerList.add(new Player("heraldo", 50));
        playerList.add(new Player("aakansha", 75));
        playerList.add(new Player("alexa", 150));

        Player[] player = playerList.stream().toArray(Player[]::new);
        Arrays.sort(player, new Checker());
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }

    }
}


