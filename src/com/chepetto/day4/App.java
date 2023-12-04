package com.chepetto.day4;

import com.chepetto.util.Solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class App extends Solution {

    public App() {
        this(false);
    }

    public App(boolean useSample) {
        super(useSample);
    }

    public static void main(String[] args) {
        App app = new App(false);
        //System.out.println(app.part1());
        System.out.println(app.part2());
    }
    
    @Override
    public Object part1() {
        return lines.stream()
            .map(l -> l.substring(l.indexOf(":") + 1))
            .mapToInt(l -> {
              String[] tmp = l.split(" \\| ");
              Set<Integer> winningNumbers = Arrays.stream(tmp[0].split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toSet());

            Set<Integer> drawnNumbers = Arrays.stream(tmp[1].split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toSet());

            drawnNumbers.retainAll(winningNumbers);

            return (int) Math.pow(2, drawnNumbers.size() - 1);
            
        }).sum();
    }

    @Override
    public Object part2() {
        Map<Integer, Integer> cardCount = new HashMap<>();
        
        for (String line : lines) {
            int card = Integer.parseInt(line.replace(":", "").split("\\s+")[1]);

            String[] tmp = line.substring(line.indexOf(":") + 1).split(" \\| ");

              Set<Integer> winningNumbers = Arrays.stream(tmp[0].split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toSet());

            Set<Integer> drawnNumbers = Arrays.stream(tmp[1].split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toSet());

            drawnNumbers.retainAll(winningNumbers);

            System.out.println("card: " + card + " before: " + cardCount);
            //if (!drawnNumbers.isEmpty()) {
                cardCount.compute(card, (k, v) -> (v == null) ? 1: (v + 1));
            //}
            System.out.println("after: " + cardCount);


            final int availableCards = cardCount.getOrDefault(card, 1);
            for (int i = 1; i <= drawnNumbers.size(); i++) {                
                cardCount.compute(card + i, (k, v) -> (v == null) ? availableCards : v + availableCards);
            }
            System.out.println("after computations: " + cardCount);
        }
        return cardCount.values().stream().mapToInt(Integer::intValue).sum();
    }
}