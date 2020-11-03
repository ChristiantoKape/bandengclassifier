/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bandengproses;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.xml.crypto.Data;

/**
 *
 * @author XTech
 */
public class ProsesData {
    
    /**
     * List Class.
     */
    public String[] class_ = {"Segar", "Tidak Segar"};

     /**
     * Array dataset every class.
     */
    private ArrayList<ArrayDataTiapKelas> dataset;
    

    
    /**
     * Array probability posterior every class.
     */
    private ArrayList<ArrayDataPosterior> allPosterior;
    
    
    /**
     * ArrayDataEachFeature to save rgb value & diameter.
     */
    private ArrayDataTiapFitur valueFeature;
    
    public ProsesData() {
        //create dataset each class
        dataset = new ArrayList<>();
        for (int i = 0; i < class_.length; i++) {
            dataset.add(new ArrayDataTiapKelas(class_[i]));
        }
    }
    
    /**
     * find feature
     * @return 
     */
    public ArrayDataTiapFitur getDataFeature() {
        return this.valueFeature;
    }
    
    public ArrayList<ArrayDataPosterior> getSemuaPosterior() {
        return this.allPosterior;
    }
    
    /**
     * Extraction data from image, & add to dataset
     * @param input
     * @param className
     * @return image
     */
    public ProsesGambar dataTraining(BufferedImage input, String className) {
        ProsesGambar image = new ProsesGambar();

        image.setImage(input);
        double[] fitur = {image.getR(), image.getG(), image.getB()};
        valueFeature = new ArrayDataTiapFitur(fitur[0], fitur[1], fitur[2]);
        for (int i = 0; i < class_.length; i++) {
            if (class_[i].toLowerCase().equals(className.toLowerCase())) {
                //Add ArrayDataEachFeature new to dataset class_ at index i
                this.dataset.get(i).addData(valueFeature);
                break;
            }
        }
        return image;
    }
     
    /**
     * find probability class_
     * @param index
     * @return 
     */
    public double getProbClass(int index) {
        double totalDataset = 0.0;
        double class_Dataset = dataset.get(index).getSize();
        for(int i = 0; i < dataset.size(); i++) {
            totalDataset += dataset.get(i).getSize();
        }
        return (double)(class_Dataset/totalDataset);
    }
    
    /**
     * Extraction data testing, then compute
     * to get decision, output class.
     * @param input
     * @return 
     */
    public String dataTesting(BufferedImage input) {

        //Take data sample test
        ArrayDataTiapFitur dataTesting;
        ProsesGambar imageTesting = new ProsesGambar();
        imageTesting.setImage(input);
//        System.out.println(input);
        double[] fitur = {imageTesting.getR(),imageTesting.getG(),imageTesting.getB()};
        dataTesting = new ArrayDataTiapFitur(fitur[0], fitur[1], fitur[2]);
        double[] sampel = dataTesting.getMatrix();
        
        //Create ArrayDataMeanVar Mean & Variance
        ArrayList<ArrayDataMeanVar> cl = new ArrayList<>();
        
//        System.out.println("banyak class_ :"+class_.length);
//        System.out.println("banyak class_ :"+class_[0]);
        
        for (int i = 0; i < class_.length; i++) {
//            System.out.println("class_ ke-"+i+":"+class_[i]);      
//            System.out.println("dataset :"+dataset.get(1).toString());
            cl.add(new ArrayDataMeanVar(class_[i], dataset.get(i).getMatrix()));
        }
        
        //Compute prob. likelihood
        ArrayList<ArrayDataLikelihood> lk = new ArrayList<>();
        for(int j = 0; j < class_.length; j++) {
            lk.add(new ArrayDataLikelihood(class_[j], cl.get(j), sampel));
        }
        
        //Compute prob. posterior
        ArrayList<ArrayDataPosterior> pt = new ArrayList<>();
        for(int k = 0; k < class_.length; k++) {
            pt.add(new ArrayDataPosterior(class_[k], lk.get(k), getProbClass(k)));
        }
        
        //Final decision class as result
        ArrayDataPosterior chosen = pt.get(0);
        for(int m = 1; m < class_.length; m++) {
            if(chosen.getProbability() < pt.get(m).getProbability()) {
               chosen = pt.get(m);
            }
        }
        
        this.allPosterior = pt;
        
        
        
        System.out.println("Class Final Is " + chosen.getNameClass());
        return chosen.getNameClass();
    }
}