package antColony.algorithm;

import antColony.subjects.Subject;

import java.util.*;
import java.util.stream.IntStream;

public class AntColony {

    private double c = 1.0;
    private double alpha = 1;
    private double beta = 5;
    private double evaporation = 0.5;
    private double Q = 500;
    private double antFactor = 0.8;
    private double randomFactor = 0.01;

    private int maxIterations = 1000;

    private int numberOfSubjects;
    private int numberOfAnts;
    private double graph[][];
    private double trails[][];
    private List<Ant> ants = new ArrayList<>();
    private Random random = new Random();
    private double probabilities[];

    private int currentIndex;

    private int[] bestTourOrder;
    private double bestTourLength;

    public AntColony(Subject[][] noOfSubject) {
        graph = generateInitialTimetable(noOfSubject);
        numberOfSubjects = graph.length;
        numberOfAnts = (int) (numberOfSubjects * antFactor);

        trails = new double[numberOfSubjects][numberOfSubjects];
        probabilities = new double[numberOfSubjects];
        IntStream.range(0, numberOfAnts)
                .forEach(i -> ants.add(new Ant(numberOfSubjects)));
    }

    /**
     * Generate initial solution
     */
    public double[][] generateInitialTimetable(Subject[][] subjects) {
//        Subject[][] initialMatrix = subjects;
//        System.out.println(initialMatrix[0][1]);
        double[][] initalResult = new double[subjects[0].length][subjects[0].length];
        int max = 0;
//        initalResult = new double[subjects.length][max];
        for (int i = 0; i < subjects[0].length; i++) {
            for (int x = 0; x < subjects[0].length; x++){
//                Double.valueOf(String.valueOf(subjects[0][x].getEnd()).split(":")[0]);
                for (int j = 0; j < subjects[0].length; j++){
                    System.out.println(subjects[0][x].getStart() + " S " + subjects[0][j].getStart());
                    System.out.println(subjects[0][x].getEnd() + " E " + subjects[0][j].getEnd());
//                    if (subjects[])
                    initalResult[i][x] =  Double.valueOf(String.valueOf(subjects[0][x].getEnd()).split(":")[0]);
//                            - Double.valueOf(String.valueOf(subjects[0][j].getEnd()).split(":")[0]);

                }

            }
        }
        System.out.println(Arrays.deepToString(initalResult));
        double[][] initialMatrix = new double[10][10];
        double[][] randomMatrix = new double[10][10];
        IntStream.range(0, 10)
                .forEach(i -> IntStream.range(0, 10)
                        .forEach(j -> randomMatrix[i][j] = Math.abs(random.nextInt(100) + 1)));
        return randomMatrix;
    }

    /**
     * Perform ant optimization
     */
    public void startAntOptimization() {
        IntStream.rangeClosed(1, 3)
                .forEach(i -> {
                    System.out.println("Attempt #" + i);
                    solve();
                });
    }

    /**
     * Use this method to run the main logic
     */
    public int[] solve() {
        setupAnts();
        clearTrails();
        IntStream.range(0, maxIterations)
                .forEach(i -> {
                    moveAnts();
                    updateTrails();
                    updateBest();
                });
        System.out.println("Best tour length: " + (bestTourLength - numberOfSubjects));
        System.out.println("Best tour order: " + Arrays.toString(bestTourOrder));
        return bestTourOrder.clone();
    }

    /**
     * Prepare ants for the simulation
     */
    private void setupAnts() {
        IntStream.range(0, numberOfAnts)
                .forEach(i -> {
                    ants.forEach(ant -> {
                        ant.clear();
                        ant.visitSubject(-1, random.nextInt(numberOfSubjects));
                    });
                });
        currentIndex = 0;
    }

    /**
     * At each iteration, move ants
     */
    private void moveAnts() {
        IntStream.range(currentIndex, numberOfSubjects - 1)
                .forEach(i -> {
                    ants.forEach(ant -> ant.visitSubject(currentIndex, selectNextSubject(ant)));
                    currentIndex++;
                });
    }

    /**
     * Select next subject for each ant
     */
    private int selectNextSubject(Ant ant) {
        int t = random.nextInt(numberOfSubjects - currentIndex);
        if (random.nextDouble() < randomFactor) {
            OptionalInt subjectIndex = IntStream.range(0, numberOfSubjects)
                    .filter(i -> i == t && !ant.visited(i))
                    .findFirst();
            if (subjectIndex.isPresent()) {
                return subjectIndex.getAsInt();
            }
        }
        calculateProbabilities(ant);
        double r = random.nextDouble();
        double total = 0;
        for (int i = 0; i < numberOfSubjects; i++) {
            total += probabilities[i];
            if (total >= r) {
                return i;
            }
        }

        throw new RuntimeException("There are no other cities");
    }

    /**
     * Calculate the next subject picks probabilites
     */
    public void calculateProbabilities(Ant ant) {
        int i = ant.trail[currentIndex];
        double pheromone = 0.0;
        for (int l = 0; l < numberOfSubjects; l++) {
            if (!ant.visited(l)) {
                pheromone += Math.pow(trails[i][l], alpha) * Math.pow(1.0 / graph[i][l], beta);
            }
        }
        for (int j = 0; j < numberOfSubjects; j++) {
            if (ant.visited(j)) {
                probabilities[j] = 0.0;
            } else {
                double numerator = Math.pow(trails[i][j], alpha) * Math.pow(1.0 / graph[i][j], beta);
                probabilities[j] = numerator / pheromone;
            }
        }
    }

    /**
     * Update trails that ants used
     */
    private void updateTrails() {
        for (int i = 0; i < numberOfSubjects; i++) {
            for (int j = 0; j < numberOfSubjects; j++) {
                trails[i][j] *= evaporation;
            }
        }
        for (Ant a : ants) {
            double contribution = Q / a.trailLength(graph);
            for (int i = 0; i < numberOfSubjects - 1; i++) {
                trails[a.trail[i]][a.trail[i + 1]] += contribution;
            }
            trails[a.trail[numberOfSubjects - 1]][a.trail[0]] += contribution;
        }
    }

    /**
     * Update the best solution
     */
    private void updateBest() {
        if (bestTourOrder == null) {
            bestTourOrder = ants.get(0).trail;
            bestTourLength = ants.get(0)
                    .trailLength(graph);
        }
        for (Ant a : ants) {
            if (a.trailLength(graph) < bestTourLength) {
                bestTourLength = a.trailLength(graph);
                bestTourOrder = a.trail.clone();
            }
        }
    }

    /**
     * Clear trails after simulation
     */
    private void clearTrails() {
        IntStream.range(0, numberOfSubjects)
                .forEach(i -> {
                    IntStream.range(0, numberOfSubjects)
                            .forEach(j -> trails[i][j] = c);
                });
    }


    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    private List<Subject> subjects = new ArrayList<>();

    public HashMap<String, Subject> AntColonyOptimization(int cycle, int ants) {
        List<HashMap<String, Subject>> solution = new ArrayList<>();
        int alpha = 2; /* importance of trail */
        int beta = 8; /* importance of heuristic evaluate */
        double rho = 0.05; /* parameter for evaporation */
        double tmin = 0.01; /* maximum pheromone trail in MMAS */
        double tmax = 10; /* minimum pheromone trail in MMAS */
        Integer GBest = Integer.MAX_VALUE;
        for (int i = 0; i < cycle; i++) {
            for (int j = 0; j < ants; j++) {
                solution.add(new Ant(subjects).AntWalk(alpha, beta, 12));
            }
            Integer CBest = 0;
            if (CBest < GBest) {
                GBest = CBest;
            }
            if (GBest == 0) {
            }
        }
        return solution.get(0);
    }
}
