package com.chepetto.util;

import com.chepetto.util.common.DefaultMapper;
import com.chepetto.util.common.MazePair;
import com.chepetto.util.common.Point;
import com.chepetto.util.common.logging.MyLogger;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * assumption => always reads maze as char grid
 */
public class Maze {
    private static final Logger LOGGER = new MyLogger(Level.FINER);
    private static final String CLASS_NAME = Maze.class.getName();

    char[][] grid;
    BiPredicate<MazePair<Point, Character>, MazePair<Point, Character>> predicate;
    UnaryOperator<Character> mapper;

    Graph<Point, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

    public Maze(BiPredicate<MazePair<Point, Character>, MazePair<Point, Character>> predicate, UnaryOperator<Character> mapper) {
        this.predicate = predicate;
        this.mapper = mapper;
    }

    public Maze(BiPredicate<MazePair<Point, Character>, MazePair<Point, Character>> predicate) {
        this(predicate, new DefaultMapper());
    }

    public GraphPath<Point, DefaultEdge> findShortestPath(Set<Point> starts, Point target) {
        return starts.stream()
                //.map(p -> BFSShortestPath.findPathBetween(graph, p, target))
                .map(p -> DijkstraShortestPath.findPathBetween(graph, p, target))
                .filter(Objects::nonNull)
                .min(Comparator.comparingInt(GraphPath::getLength)).orElse(null);
    }

    private boolean test(int x1, int y1, int x2, int y2) {
        //LOGGER.log(() -> "");
        return x2 >= 0 && x2 < grid[y1].length && y2 >= 0 && y2 < grid.length;
    }

    private void addEdge(int x1, int y1, int x2, int y2) {
        LOGGER.entering(getClass().getName(), "addEdge");
        if (test(x1, y1, x2, y2)) {
            LOGGER.finer(() -> String.format("Edge from [%d,%d] to [%d,%d] is within bounds", x1, y1, x2, y2));
            MazePair<Point, Character> source = new MazePair<>(new Point(x1, y1), grid[y1][x1]);
            if (!graph.containsVertex(source.point())) {
                LOGGER.finer(() -> String.format("added Vertex at [%d,%d]", x1, y1));
                graph.addVertex(source.point());
            }

            MazePair<Point, Character> target = new MazePair<>(new Point(x2, y2), grid[y2][x2]);
            if (predicate.test(source, target)) {
                LOGGER.finer(() -> String.format("edge from [%d,%d] to [%d,%d] passed predicate function", x1, y1, x2, y2));
                if (!graph.containsVertex(target.point())) {
                    LOGGER.finer(() -> String.format("added Vertex at [%d,%d]", x2, y2));
                    graph.addVertex(target.point());
                }
                graph.addEdge(source.point(), target.point());
            }
        } else {
            LOGGER.finer(() -> String.format("Edge from [%d,%d] to [%d,%d] is out of bounds", x1, y1, x2, y2));
        }
        LOGGER.exiting(getClass().getName(), "addEdge");
    }

    private void setupGrid(List<String> lines) {
        LOGGER.entering(getClass().getName(), "setupGrid");
        grid = new char[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            char[] line = lines.get(y).toCharArray();
            for (int x = 0; x < line.length; x++) {
                int finalX = x;
                int finalY = y;
                LOGGER.log(Level.FINEST, () -> String.format("at row,col=[%d,%d]: reading %c and storing %c", finalX, finalY, line[finalX], mapper.apply(line[finalX])));
                grid[y][x] = mapper.apply(line[x]);
            }
        }
        LOGGER.exiting(getClass().getName(), "setupGrid");
    }

    public void parse(List<String> lines) {
        parse(lines, false);
        /*LOGGER.finer(() -> "Entering parse");
        setupGrid(lines);
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                addEdge(col, row, col, row - 1);
                addEdge(col, row, col - 1, row);
                addEdge(col, row, col + 1, row);
                addEdge(col, row, col, row + 1);
            }
        }
        LOGGER.finer(() -> "Leaving parse");*/
    }

    public void parse(List<String> lines, boolean includeDiagonals) {
        LOGGER.entering(getClass().getName(), "parse");
        setupGrid(lines);
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                addEdge(col, row, col, row - 1);
                addEdge(col, row, col - 1, row);
                addEdge(col, row, col + 1, row);
                addEdge(col, row, col, row + 1);
                if (includeDiagonals) {
                    addEdge(col, row, col - 1, row - 1);
                    addEdge(col, row, col - 1, row + 1);
                    addEdge(col, row, col + 1, row - 1);
                    addEdge(col, row, col + 1, row + 1);
                }
            }
        }
        LOGGER.exiting(getClass().getName(), "parse");
    }
}
