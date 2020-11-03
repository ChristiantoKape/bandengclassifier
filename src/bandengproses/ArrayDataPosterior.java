/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bandengproses;

import java.util.ArrayList;

/**
 *
 * @author XTech
 */
public class ArrayDataPosterior {
    private String nameClass;
    private double ProbPosterior;
//    private double sum;
        
    public ArrayList<Double> arraySegar = new ArrayList<>();
    public ArrayList<Double> arrayTidakSegar = new ArrayList<>();

    
    public ArrayDataPosterior(String class_, ArrayDataLikelihood likelihood, double probClass) {
        double tempProbPosterior = probClass;
        
        //Kalikan semua probabilitas fitur untuk mendapatkan posterior
        for(int i = 0; i < likelihood.getPFeatureClass().length; i++) {
            tempProbPosterior *= likelihood.getPFeatureClass()[i];
        }
        this.nameClass = class_;
        this.ProbPosterior = tempProbPosterior;
//        dataq.add(tempProbPosterior);
        
        //Untuk log
        this.printProbability();
    }
    
    /**
     * Return value of probability
     * @return 
     */
    public double getProbability() {
        return this.ProbPosterior;
    }
    
    /**
     * Return name class_
     * @return 
     */
    public String getNameClass() {
        return this.nameClass;
    }
    
    /**
     * Print value probability posterior
     */
    public void printProbability() {
        System.out.println("Probability Posterior " + nameClass + " : " + this.ProbPosterior);
    }   
}

