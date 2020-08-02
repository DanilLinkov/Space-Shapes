package spaceshapes.forms;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import spaceshapes.ImageRectangleShape;
import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.forms.util.Form;
import spaceshapes.forms.util.FormHandler;

public class ImageShapeFormHandler implements FormHandler {

    private ShapeModel _shapeModel;
    private CarrierShape _parentOfNewShape;

    /**
     * Creates a SimpleImageShapeFormHandler.
     *
     * @param shapeModel the ShapeModel to which the handler should add a newly
     *        constructed ImageRectangleShape object.
     * @param parentOfNewShapea the CarrierShape object that will serve as the parent for
     *        a new ImageRectangleShape instance.
     */
    public ImageShapeFormHandler(ShapeModel shapeModel,CarrierShape parentOfNewShapea) {
        _shapeModel = shapeModel;
        _parentOfNewShape = parentOfNewShapea;
    }

    /**
     * Reads form data that describes an ImageRectangleShape. Based on the
     * data, this SimpleImageShapeFormHandler creates a new ImageRectangleShape
     * object, adds it to a ShapeModel and to a CarrierShape within the model.
     *
     * @param form the Form that contains the ImageRectangleShape data.
     */
    @Override
    public void processForm(final Form form) {

        SwingWorker<ImageRectangleShape,Void> imageLoaderWorker = new SwingWorker<ImageRectangleShape, Void>() {
            long startTime = System.currentTimeMillis();

            // Read field values from the form.
            File imageFile = (File) form.getFieldValue(File.class, ImageFormElement.IMAGE);
            int width = form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
            int deltaX = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
            int deltaY = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);

            @Override
            protected ImageRectangleShape doInBackground() throws Exception {

                // Load the original image (ImageIO.read() is a blocking call).
                BufferedImage fullImage = null;
                try {
                    fullImage = ImageIO.read(imageFile);
                } catch(IOException e) {
                    System.out.println("Error loading image.");
                }

                int fullImageWidth = fullImage.getWidth();
                int fullImageHeight = fullImage.getHeight();

                BufferedImage scaledImage = fullImage;

                // Scale the image if necessary.
                if(fullImageWidth > width) {
                    double scaleFactor = (double)width / (double)fullImageWidth;
                    int height = (int)((double)fullImageHeight * scaleFactor);

                    scaledImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = scaledImage.createGraphics();

                    // Method drawImage() scales an already loaded image. The
                    // ImageObserver argument is null because we don't need to monitor
                    // the scaling operation.
                    g.drawImage(fullImage, 0, 0, width, height, null);
                }

                // Create the new Shape and add it to the model.
                ImageRectangleShape imageShape = new ImageRectangleShape(deltaX, deltaY, scaledImage);

                return imageShape;
            }

            @Override
            protected void done()
            {
                try {
                    // Add the imageShape from doInBackground to the parent of the new shape
                    _shapeModel.add(get(),_parentOfNewShape);
                } catch (InterruptedException e) {
                    System.out.println("Loading the image was interrupted by another source");
                } catch (ExecutionException e) {
                    System.out.println("Error getting the image into the animation viewer");
                }
                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("Image loading and scaling took " + elapsedTime + "ms.");
            }
        };

        imageLoaderWorker.execute();
    }
}
