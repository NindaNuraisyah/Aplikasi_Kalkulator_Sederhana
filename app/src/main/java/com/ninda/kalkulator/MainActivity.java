package com.ninda.kalkulator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText angkaPertamaEditText;
    EditText angkaKeduaEditText;
    TextView hasilTextView;
    TextView labelhasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        angkaPertamaEditText = findViewById(R.id.angka1);
        angkaKeduaEditText = findViewById(R.id.angka2);
        hasilTextView = findViewById(R.id.hasil);
        labelhasil = findViewById(R.id.labelhasil);
    }

    public void tambahAngka(View view) {
        if (isInputValid()) {
            hitung("+");
        }
    }

    public void kurangAngka(View view) {
        if (isInputValid()) {
            hitung("-");
        }
    }

    public void kaliAngka(View view) {
        if (isInputValid()) {
            hitung("*");
        }
    }

    public void bagiAngka(View view) {
        if (isInputValid()) {
            hitung("/");
        }
    }

    private boolean isInputValid() {
        String angkaPertamaStr = angkaPertamaEditText.getText().toString().trim();
        String angkaKeduaStr = angkaKeduaEditText.getText().toString().trim();

        if (angkaPertamaStr.isEmpty() || angkaKeduaStr.isEmpty()) {
            Toast.makeText(this, "Harap isi kedua angka terlebih dahulu!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void reset(View view) {
        angkaPertamaEditText.setText("");
        angkaKeduaEditText.setText("");
        hasilTextView.setText("");
        labelhasil.setVisibility(View.GONE);
    }

    private void hitung(String operator) {
        double angkaPertama = Double.parseDouble(angkaPertamaEditText.getText().toString());
        double angkaKedua = Double.parseDouble(angkaKeduaEditText.getText().toString());
        double hasil = 0;
        String keteranganOperasi = "";

        switch (operator) {
            case "+":
                hasil = angkaPertama + angkaKedua;
                keteranganOperasi = "Hasil penjumlahan";
                break;
            case "-":
                hasil = angkaPertama - angkaKedua;
                keteranganOperasi = "Hasil pengurangan";
                break;
            case "*":
                hasil = angkaPertama * angkaKedua;
                keteranganOperasi = "Hasil perkalian";
                break;
            case "/":
                if (angkaKedua != 0) {
                    hasil = angkaPertama / angkaKedua;
                    keteranganOperasi = "Hasil pembagian";
                } else {
                    hasilTextView.setText("Tidak bisa dibagi dengan 0");
                    return;
                }
                break;
        }

        labelhasil.setVisibility(View.VISIBLE);
        if (hasil % 1 == 0) {
            int hasilBulat = (int) hasil;
            hasilTextView.setText(keteranganOperasi + " : " + hasilBulat);
        } else {
            hasilTextView.setText(keteranganOperasi + " : " + hasil);
        }
    }
}
