/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bandengproses;

/**
 *
 * @author XTech
 */
public class ArrayDataMeanVar {

    private String nameClass;
    private int totalData;
    private int totalFeature;
    private double[] mean;
    private double[] deviation;
    private double[] a;

    

    public ArrayDataMeanVar(String kelas, double[][] data) {
        double[] meanTemp;
        double[] standartTemp;
        int row = 0;
        int column = 0;
        
        //System.out.println("Length of data[0] :"+data[0].length);

        //Find mean
        meanTemp = new double[data[0].length];
        for (row = 0; row < data.length; row++) {
            for (column = 0; column < data[0].length; column++) {
                meanTemp[column] += data[row][column];
            }
        }
        for (int i = 0; i < column; i++) {
            meanTemp[i] /= row;
        }

        //Find standartdeviation
        standartTemp = new double[data[0].length];
        for (row = 0; row < data.length; row++) {
            for (column = 0; column < data[0].length; column++) {
                standartTemp[column] += (Math.pow((data[row][column] - meanTemp[column]),2));
            }
        }
        for (int j = 0; j < column; j++) {
            standartTemp[j] /= (row - 1);
        }
        
        this.totalFeature = column;
        this.totalData = row;
        this.nameClass = kelas;
        this.mean = meanTemp;
        this.deviation = standartTemp;
        
        //For log
        this.printClassifier();
//        this.getStdDev()
    }
    
    public double[] getVariance() {
        return this.deviation;
    }
    
    public double[] getStdDev() {
        for(int i = 0; i < deviation.length; i++){
            a[i] = Math.sqrt(deviation[i]);
            System.out.println("a" + a[i]);
        }
        return a;
    }
    
    public double[] getMean() {
        return this.mean;
    }
    
    public int getJumlahFeature() {
        return this.totalFeature;
    }
    
    public int getJumlahData() {
        return this.totalData;
    }
    
    public void printClassifier() {
        System.out.println("Classifier " + this.nameClass + ": ");
        this.printMean();
        this.printVariance();
        System.out.println("----------------------------");
    }
    
    public void printMean() {
        System.out.println(" Mean:");
        for(int i = 0; i < mean.length; i++) {
            System.out.print(mean[i] + " ");
            System.out.println("");
        }
    }
    
    public void printVariance() {
        System.out.println(" Standar Deviasi:");
        for(int i = 0; i < deviation.length; i++) {
            System.out.println(Math.sqrt(deviation[i]));
        }
    }
}
