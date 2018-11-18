package antColony.algorithm;

import antColony.subjects.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AntColony {

    private Ant ant;

    private List<Subject> subjects;

    public void AntColonyOptimization(int cycle, int ants) {
        List<HashMap<String, Subject>> solution = new ArrayList<>();
        int alpha = 2;
        int beta = 8;
        double rho = 0.05;
        double tmin = 0.01;
        double tmax = 10;
        Integer GBest = Integer.MAX_VALUE;
        for (int i = 0; i < cycle; i++) {
            for (int j =0; j < ants; j++) {
                solution.add(new Ant(subjects).AntWalk(alpha, beta,12));
            }
            Integer CBest = 0;
            if (CBest < GBest){
                GBest = CBest;
            } if (GBest == 0){
                return;
            }
        }
    }

}
