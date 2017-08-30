package com.example.administrateur.sqlitedepts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Departement dept;
    private EditText txtSearch;
    private EditText txtNoDept;
    private EditText txtNoRegion;
    private EditText txtNom;
    private EditText txtNomStd;
    private EditText txtSurface;
    private EditText txtDateCreation;
    private EditText txtChefLieu;
    private EditText txtUrlWiki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation des variables dans le onCreate
        txtSearch = (EditText) findViewById(R.id.txtSearch);
        txtNoDept = (EditText) findViewById(R.id.txtNoDept);
        txtNoRegion = (EditText) findViewById(R.id.txtNoRegion);
        txtNom = (EditText) findViewById(R.id.txtNom);
        txtNomStd = (EditText) findViewById(R.id.txtNomStd);
        txtSurface = (EditText) findViewById(R.id.txtSurface);
        txtDateCreation = (EditText) findViewById(R.id.txtDateCreation);
        txtChefLieu = (EditText) findViewById(R.id.txtChefLieu);
        txtUrlWiki = (EditText) findViewById(R.id.txtUrlWiki);

    }

    public void btnSearch (View v){

        String search = txtSearch.getText().toString();

        try {
            dept = new Departement(this);
            dept.select(search);

            txtNoDept.setText(dept.getNoDept());
            txtNoRegion.setText(String.valueOf(dept.getNoRegion()));
            txtNom.setText(dept.getNom());
            txtNomStd.setText(dept.getNomStd());
            txtSurface.setText(String.valueOf(dept.getSurface()));
            txtDateCreation.setText(dept.getDateCreation());
            txtChefLieu.setText(dept.getChefLieu());
            txtUrlWiki.setText(dept.getUrlWiki());

            txtNoDept.setEnabled(false);
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnClear (View v){

        txtSearch.setText("");
        txtNoDept.setText("");
        txtNoRegion.setText("");
        txtNom.setText("");
        txtNomStd.setText("");
        txtSurface.setText("");
        txtDateCreation.setText("");
        txtChefLieu.setText("");
        txtUrlWiki.setText("");

        txtNoDept.setEnabled(true);
    }

    public void btnDelete (View v){
        dept = new Departement(this);
        dept.setNoDept(txtNoDept.getText().toString());

        try {
            dept.delete();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        btnClear(v);
    }

    public void btnSave (View v){

        try {
            dept = new Departement(this);
            dept.setNoDept(txtNoDept.getText().toString());
            dept.setNoRegion(Integer.parseInt(txtNoRegion.getText().toString()));
            dept.setNom(txtNom.getText().toString());
            dept.setNomStd(txtNomStd.getText().toString());
            dept.setSurface(Integer.parseInt(txtSurface.getText().toString()));
            dept.setDateCreation(txtDateCreation.getText().toString());
            dept.setChefLieu(txtChefLieu.getText().toString());
            dept.setUrlWiki(txtUrlWiki.getText().toString());

            String insert = txtNoDept.getText().toString();
            String update = txtNoDept.getText().toString();

            if (!txtNoDept.equals("")) {

                Toast.makeText(this, "insertion", Toast.LENGTH_LONG).show();
                dept.insert();
            }
            else {
                Toast.makeText(this, "udpate", Toast.LENGTH_LONG).show();
                dept.update();
            }
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnTest (View v){
// Test de la méthode insert();
         dept = new Departement(this);
        dept.setNoDept("101");
        dept.setNoRegion(93);
        dept.setNom("Nouveau dept - test");
        dept.setNomStd("NOUVEAU DEPT");
        dept.setSurface(5000);
        dept.setDateCreation("2017-01-01");
        dept.setChefLieu("Ville");
        dept.setUrlWiki("http://");
        try {
            dept.insert();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
