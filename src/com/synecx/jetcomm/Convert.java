package com.synecx.jetcomm;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Convert {
    private BufferedImage m_image;

    public Convert (BufferedImage image) {
        m_image = image;
    }

    public int[][] convertToRGBA() {
        final int height = m_image.getHeight();
        final int width = m_image.getWidth();
        final int pixelLength = 3; // We assume there is no alpha channel
        final byte[] pixelData = ((DataBufferByte) m_image.getRaster().getDataBuffer()).getData();

        int[][] result = new int[height][width];
    
        int row = 0; 
        int col = 0;
        for (int i = 0; i < pixelData.length; i += pixelLength) {
            int argb = 0;
            argb |= -16777216; // Creates integer with binary representation of 
                               // A[11111111] R[00000000] G[00000000] B[00000000]
            
            argb |= ((int) pixelData[i] & 0xFF); // Blue value is set at the front
                                                 // A[11111111] R[00000000] G[00000000] B[BLUE_VALUE]

            argb |= (((int) pixelData[i + 1] & 0xFF) << 8); // Green value is left of Blue; lshift by 8
                                                            // A[11111111] R[00000000] G[GREEN_VALUE] B[BLUE_VALUE]
        
            argb |= (((int) pixelData[i + 2] & 0xFF) << 16); // Red value is set last in between A and G; lshift by 16
                                                             // A[11111111] R[RED_VALUE] G[GREEN_VALUE] B[BLUE_VALUE]
        
            result[row][col] = argb;
            
            col++;
            if (col == width) { // Once we reach the full width of the image
                col = 0;        // Start the next row
                row++;
            }
        }
        return result;
    }
}