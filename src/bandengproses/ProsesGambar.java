package bandengproses;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ProsesGambar {

    private final ArrayList ArrayObjek;
    private BufferedImage realImage;
    private BufferedImage grayImage;
    private BufferedImage binaryImage;
    private int tinggiCitra;
    private int lebarCitra;
    private double meanR;
    private double meanG;
    private double meanB;
    
    public ProsesGambar() {
        this.realImage = null;
        this.grayImage = null;
        this.binaryImage = null;
        this.meanR = 0;
        this.meanG = 0;
        this.meanB = 0;
        this.ArrayObjek = new ArrayList();
        this.lebarCitra = 0;
        this.tinggiCitra = 0;
    }
    
    /**
     * Tentukan image yg akan di proses. 
     * @param input 
     */
    public void setImage(BufferedImage input) {
        realImage = input;
        setSize();
        imageToGray();
        grayToBinary();
        hitungMeanR_G_B(); // hitung mean_R, mean_G, dan mean_B
    }
    
    /**
     * Dapatkan image yg asli.
     * @return 
     */
    public BufferedImage getImage() {
        return this.realImage;
    }
    
    /**
     * Pemrosesan image menjadi grayscale image.
     */
    private void imageToGray() {
        // Init variable
        double red, green, blue;
        int gray;
        Color before, after;

        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {

                before = new Color(realImage.getRGB(x, y));

                // Calculate RGB to gray
                // with lumonisity algorithm
                red = (double) (before.getRed() * 0.2989);
                green = (double) (before.getGreen() * 0.5870);
                blue = (double) (before.getBlue() * 0.1140);
                gray = (int) (red + green + blue);

                after = new Color(gray, gray, gray);

                output.setRGB(x, y, after.getRGB());
            }
        }
        this.grayImage = output;
    }
    
    /**
     * Dapatkan image yg sudah diproses menjadi grayscale.
     * @return 
     */
    public BufferedImage getGrayImage() {
        return this.grayImage;
    }
    
    /**
     * Pemrosesan image menjadi binary image.
     */
    private void grayToBinary() {
        Color before, after;
        ArrayObjek.clear();

        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {

                before = new Color(this.grayImage.getRGB(x, y));

                if (before.getBlue() < 251) {
                    after = new Color(255, 255, 255);
                    ArrayObjek.add(String.valueOf(x) + "," + String.valueOf(y));
                } else {
                    after = new Color(0, 0, 0);
                }
                output.setRGB(x, y, after.getRGB());
            }
        }
        this.binaryImage = output;
    }
    
    /**
     * Dapatkan image yg sudah diproses menjadi binary image.
     * @return 
     */
    public BufferedImage getBinaryImage() {
        return this.binaryImage;
    }
    
    /**
     * Mendapatkan mean RGB dari objek gambar.
     *
     * @param input
     * @return color
     */
    private void hitungMeanR_G_B() {
        int length = ArrayObjek.size();
        double red = 0;
        double green = 0;
        double blue = 0;
        int x, y;
        Color before;
        String xy[];
        for (int i = 0; i < length; i++) {
            xy = ArrayObjek.get(i).toString().split(",");

            x = Integer.parseInt(xy[0]);
            y = Integer.parseInt(xy[1]);
            
            before = new Color(this.realImage.getRGB(x, y));
            
            red += before.getRed();
            blue += before.getBlue();
            green += before.getGreen();

        }
        red /= length;
        blue /= length;
        green /= length;

        meanR = red;
        meanG = green;
        meanB = blue;
    }
    
    /**
     * Dapatkan mean red value dari image.
     * @return 
     */
    public double getR() {
        return this.meanR;
    }

    /**
     * Dapatkan mean green value dari image.
     * @return 
     */
    public double getG() {
        return this.meanG;
    }

    /**
     * Dapatkan mean blue value dari image.
     * @return 
     */
    public double getB() {
        return this.meanB;
    }
        
    /**
     * Dapatkan ukuran image, dari image asli.
     */
    private void setSize() {
        tinggiCitra = realImage.getHeight();
        lebarCitra = realImage.getWidth();
    }

    /**
     * Mendapatkan dimensi lebar dari image.
     * @return 
     */
    public int getLebar() {
        return this.lebarCitra;
    }
    
    /**
     * Mendapatkan dimensi tinggi dari image.
     * @return 
     */
    public int getTinggi() {
        return this.tinggiCitra;
    } 
}
