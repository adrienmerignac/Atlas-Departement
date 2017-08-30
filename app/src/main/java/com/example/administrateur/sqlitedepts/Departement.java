package com.example.administrateur.sqlitedepts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Departement {

    // Déclarations
    private String noDept, nom, nomStd, dateCreation, chefLieu, urlWiki;
    private int noRegion, surface;

    private SQLiteDatabase db;
    private Context ctxt;

    private final String TAB_NAME = "departements";


    // Constructeur1

    public Departement(Context ctxt){

        noDept =""; nom = ""; nomStd = ""; dateCreation = ""; chefLieu = ""; urlWiki = "";
        noRegion = 0; surface = 0;

        this.ctxt = ctxt;

        DbInit dbInit = DbInit.getInstance(ctxt);
        db = dbInit.getWritableDatabase();
    }


    // Constructeur2
    public Departement(Context ctxt, String noDept) throws Exception {

        this.ctxt = ctxt;

        Departement dept = new Departement(ctxt, noDept);
        dept.select(noDept);
    }


    // Accesseurs
    public int getNoRegion() {
        return noRegion;
    }

    public void setNoRegion(int noRegion) {
        this.noRegion = noRegion;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getNoDept() {return noDept;}

    public void setNoDept(String noDept) {
        this.noDept = noDept;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomStd() {
        return nomStd;
    }

    public void setNomStd(String nomStd) {
        this.nomStd = nomStd;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }

    public String getUrlWiki() {
        return urlWiki;
    }

    public void setUrlWiki(String urlWiki) {
        this.urlWiki = urlWiki;
    }


    public void select(String name) throws Exception {
        //  Définir le critère

        //  Charger les valeurs depuis la base de données
        String[] colonnes = {"no_dept", "no_region", "nom", "nom_std", "surface", "date_creation", "chef_lieu", "url_wiki"};
        Cursor curs = db.query(false, TAB_NAME, colonnes, "no_dept ='" + name + "'", null, null, null, null, null);

        if (curs.moveToFirst()){

            this.noDept = curs.getString(0);
            this.noRegion = curs.getInt(1);
            this.nom = curs.getString(2);
            this.nomStd = curs.getString(3);
            this.surface = curs.getInt(4);
            this.dateCreation = curs.getString(5);
            this.chefLieu = curs.getString(6);
            this.urlWiki = curs.getString(7);
        }

        else {
            throw new Exception("Département non trouvé");
        }

    }

    public void delete() throws Exception {

        if (this.noDept.equals("")){
            throw new Exception("Département non initialisé");
        }

        String where = "no_Dept = '" + noDept + "'";
        db.delete(TAB_NAME, where, null);
    }


    public void update() throws Exception {

//        if (this.noDept.equals("")) {
//            throw new Exception("Département non initialisé");
//        }

            String where = "no_dept = " + getNoDept();
            ContentValues values = new ContentValues();

            values.put("no_dept", noDept);
            values.put("no_region", noRegion);
            values.put("nom", nom);
            values.put("nom_std", nomStd);
            values.put("surface", surface);
            values.put("date_creation", dateCreation);
            values.put("chef_lieu", chefLieu);
            values.put("url_wiki", urlWiki);

            db.update(TAB_NAME, values, where, null);
        }

    public void  insert() throws Exception {

//        if (this.noDept.equals(noDept)) {
//            throw new Exception("Département déjà crée");
//        }

        ContentValues values = new ContentValues();

        values.put("no_dept", noDept);
        values.put("no_region", noRegion);
        values.put("nom", nom);
        values.put("nom_std", nomStd);
        values.put("surface", surface);
        values.put("date_creation", dateCreation);
        values.put("chef_lieu", chefLieu);
        values.put("url_wiki", urlWiki);

        db.insert(TAB_NAME ,null, values);
    }

}
