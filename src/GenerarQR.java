import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Scanner;

public class GenerarQR {

    private static final int qrTamAncho = 400;
    private static final int qrTamAlto = 400;
    private static final String formato = "png";
    private static final String ruta = "nombre-apellido.png";

    public static void main(String[] args) throws Exception {
        
        /* nota: en la terminal no se alcanza a apreciar el qr, por lo cual genero una imagen en base a este de tipo png nombre-apellido.png
        En la carpeta donde se ejecuta el .jar
        */
        
        
        //introducir nombre
        
        String nombre = "jose";
        
        //introducir apellido
        
        String apellido = "perez";
        
        String nombreCompleto = "nombre: " + nombre + " apellido: " + apellido; 
        
        String data = nombreCompleto;
        System.out.println("Cofificando...");
        BitMatrix matriz;
        Writer writer = new QRCodeWriter();
        try {
            matriz = writer.encode(data, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return;
        }
        BufferedImage imagen = new BufferedImage(qrTamAncho,
                qrTamAlto, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < qrTamAlto; y++) {
            for (int x = 0; x < qrTamAncho; x++) {
                int valor = (matriz.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (valor == 0 ? 0 : 0xFFFFFF));
                if (valor == 1) {
                 System.out.print("█");   
                }
                if(valor == 0){
                    System.out.print(" ");   
                }
            
            }
            System.out.println("");
        }
        FileOutputStream qrCode = new FileOutputStream(ruta);
        ImageIO.write(imagen, formato, qrCode);
        System.out.println("Listo!");
        qrCode.close();
    }
}
