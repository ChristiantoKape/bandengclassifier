/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bandengproses;

/**
 *
 * @author XTech
 */
public class ArrayDataTiapFitur {
    public double red;
    public double green;
    public double blue;
    
    public ArrayDataTiapFitur(double r_in, double g_in, double b_in) {
        this.red = r_in;
        this.green = g_in;
        this.blue = b_in;
    }
    
    public double[] getMatrix() {
        double[] output = {red, green, blue};
        return output;
    }
    
    public void printMatrix() {
        System.out.println(red + " " + green + " " + blue + " ");
    }
}

