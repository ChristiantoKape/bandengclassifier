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
public class ArrayDataTiapKelas {
    
    private String nameClass;
    private ArrayList<ArrayDataTiapFitur> data;
    private int sizeData;
    
    public ArrayDataTiapKelas(String name) {
        this.data = new ArrayList<>();
        this.sizeData = 0;
        this.nameClass = name;
    }
    
    public String getNameClass() {
        return this.nameClass;
    }
    
    /**
     * to add new data to dataset this class.
     * @param new data
     */
    public void addData(ArrayDataTiapFitur dataBaru) {
        this.data.add(dataBaru);
        this.sizeData = this.data.size();
    }
    /**
     * find total data from dataset this class.
     * @return 
     */
    public int getSize() {
        return this.sizeData;
    }
    /**
     * find matrix 2 dimension from dataset.
     * @return 
     */
    public double[][] getMatrix() {
        int totalFeature = 3;
        int totalData = data.size();
        //int totalData = 15;
//        System.out.println("totalFeature :"+totalFeature);
//        System.out.println("totalData :"+totalData);
        double[][] matrix = new double[totalData][totalFeature];
        for(int i = 0; i < totalData; i++) {
            matrix[i][0] = data.get(i).red;
            matrix[i][1] = data.get(i).green;
            matrix[i][2] = data.get(i).blue;
        }                    
        return matrix;
    }
    
    /**
     * Change data at dataset entire.
     * @param matrix 
     */
    public void setMatrix(double[][] matrix) {
        data.clear();
        for(int i = 0; i < matrix.length; i++) {
            data.add(new ArrayDataTiapFitur(matrix[i][0], matrix[i][1], matrix[i][2]));
        }
    }
    
    /**
     * find red value from index data.
     * @param index
     * @return 
     */
    public double getRed(int index) {
        return this.data.get(index).red;
    }
    
    /**
     * find green value from index data.
     * @param index
     * @return 
     */
    public double getGreen(int index) {
        return this.data.get(index).green;
    }
    
    /**
     * find blue value from index data.
     * @param index 
     */
    public double getBlue(int index) {
        return this.data.get(index).blue;
    }
}
