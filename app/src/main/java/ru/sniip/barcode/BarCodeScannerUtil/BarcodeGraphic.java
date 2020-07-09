package ru.sniip.barcode.BarCodeScannerUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import ru.sniip.barcode.BarCodeScannerUtil.common.GraphicOverlay;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;

/**
 * Graphic instance for rendering barcode position, size, and ID within an associated graphic
 * overlay view.
 */
public class BarcodeGraphic extends GraphicOverlay.Graphic {

    private static final int TEXT_COLOR = Color.WHITE;
    private static final float TEXT_SIZE = 54.0f;
    private static final float STROKE_WIDTH = 4.0f;

    private final Paint rectPaint;
    private final Paint barcodePaint;
    private final FirebaseVisionBarcode barcode;

    BarcodeGraphic(GraphicOverlay overlay, FirebaseVisionBarcode barcode) {
        super(overlay);

        this.barcode = barcode;

        rectPaint = new Paint();
        rectPaint.setColor(TEXT_COLOR);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(STROKE_WIDTH);

        barcodePaint = new Paint();
        barcodePaint.setColor(TEXT_COLOR);
        barcodePaint.setTextSize(TEXT_SIZE);
    }

    /**
     * Draws the barcode block annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        if (barcode == null) {
            throw new IllegalStateException("Attempting to draw a null barcode.");
        }

        // Draws the bounding box around the BarcodeBlock.
        RectF rect = new RectF(barcode.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, rectPaint);

        // Renders the barcode at the bottom of the box.
        canvas.drawText(barcode.getRawValue(), rect.left, rect.bottom, barcodePaint);
    }

    public Rect getActualBounds(FirebaseVisionBarcode barcode)
    {
        Rect rect = new Rect(barcode.getBoundingBox());
        rect.left = (int) translateX(rect.left);
        rect.top = (int) translateY(rect.top);
        rect.right = (int) translateX(rect.right);
        rect.bottom = (int) translateY(rect.bottom);

        return rect;
    }
}