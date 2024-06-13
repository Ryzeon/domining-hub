package me.ryzeon.domininghub.utils;

import com.googlecode.pngtastic.core.PngImage;
import com.googlecode.pngtastic.core.PngOptimizer;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 17:53
 */
@UtilityClass
public class ImageCompressor {

    public byte[] compress(InputStream inputStream) throws IOException {
        PngImage pngImage = new PngImage(inputStream);
        PngOptimizer optimizer = new PngOptimizer();
        PngImage optimizedImage = optimizer.optimize(pngImage);
        return optimizedImage.getImageData();
    }
}
