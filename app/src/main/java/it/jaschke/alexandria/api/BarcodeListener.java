package it.jaschke.alexandria.api;

import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by cognitive on 2/23/16.
 */
public interface BarcodeListener {
    void onBarCodeScanned( Barcode scannedBarCode );
}
