/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bandengproses;

/**
 *
 * @author XTech
 */
public class ArrayDataLikelihood {
    private String nameClass;
    private ArrayDataMeanVar classifier;
    private double[] dataTesting;
    private double[] pFeatureClass; //P. ArrayDataLikelihood
    
    public ArrayDataLikelihood(String class_, ArrayDataMeanVar cl, double[] dataInput) {
        //Assign field
        this.classifier = cl;
        this.dataTesting = dataInput;
        this.nameClass = class_;
        
        //Kalkulasi Probability likelihood tiap fitur terhadap class_
        double[] pFeatureClassTemp;
        pFeatureClassTemp = new double[classifier.getJumlahFeature()];
        for(int i = 0; i < classifier.getJumlahFeature(); i++) {
            double variance = classifier.getVariance()[i];
            double mean = classifier.getMean()[i];
            double value_ = dataTesting[i];
            
            double a = Math.sqrt(variance);            
//            System.out.println("hh"+a);
            //Rumus likelihood
            pFeatureClassTemp[i] = (1 / (Math.sqrt(2 * Math.PI) * (a))* (Math.pow(Math.E,(-(Math.pow((value_ - mean),2))/(2 * Math.pow(a,2))))));
        }
        
        //set field pFeatureClass
        this.pFeatureClass = pFeatureClassTemp;
        
        //Untuk log
//        this.cetakProbability();
    }
    
    /**
     * Dapatkan array dari likelihood tiap fitur terhadap class_.
     * @return 
     */
    public double[] getPFeatureClass() {
        return this.pFeatureClass;
    }
    
    /**
     * Cetak semua likelihood
     */
    public void cetakProbability() {
        System.out.println("Prob. Likelihood " + this.nameClass + " :");
        for(int i = 0; i < pFeatureClass.length; i++) {
            System.out.print(pFeatureClass[i] + " ");
            System.out.println("");
        }
        System.out.println("----------------------------");
    }
}
