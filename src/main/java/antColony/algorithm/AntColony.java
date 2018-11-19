package antColony.algorithm;

import antColony.subjects.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AntColony {

    private Ant ant;

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
