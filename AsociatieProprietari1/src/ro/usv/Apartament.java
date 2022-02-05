/**
 * @author Adriana BECICA
 * @grupa 3131b
 * @nr 1
 */

package ro.usv;

import ro.usv.dao.Entitate;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

public abstract class Apartament extends Entitate<Integer> {
    private String tip;
    private int id;
    private float suprafata;
    private int anConstructie;
    private String strada;
    private int nr;
    private char scara;
    private int etaj;
    private int nrApt;

    public Apartament(String tip, int id, float suprafata, int anConstructie, String strada, int nr, char scara, int etaj, int nrApt) {
        this.tip = tip;
        this.id = id;
        this.suprafata = suprafata;
        this.anConstructie = anConstructie;
        this.strada = strada;
        this.nr = nr;
        this.scara = scara;
        this.etaj = etaj;
        this.nrApt = nrApt;
    }



    @Override
    public String toString() {
        return
                "id=" + id +
                        ", suprafata=" + suprafata +
                        ", anConstructie=" + anConstructie +
                        ", strada='" + strada + '\'' +
                        ", nr=" + nr +
                        ", scara=" + scara +
                        ", etaj=" + etaj +
                        ", nrApt=" + nrApt;
    }




    @Override
    public Integer getId() {
        return id;
    }



    /**Metoda getVechime() are rolul de a calcula vârsta in ani a unui Apartament.*/
    public int getVechime()
    {
        LocalDate start=LocalDate.of(anConstructie,1,1);
        LocalDate stop= LocalDate.now();
        return (int)java.time.temporal.ChronoUnit.YEARS.between(start,stop);
    }

    public String getTip() {
        return tip;
    }

    public float getSuprafata() {
        return suprafata;
    }

    public int getAnConstructie()
    {return anConstructie;}

   /* public int getAnConstructie() throws IllegalArgumentException {
        {
            int anCurent = Calendar.getInstance().get(Calendar.YEAR);
            if(anConstructie>anCurent)
            { throw new IllegalArgumentException("an de construcție: nu trebuie să fie în viitor");}

                    if(anConstructie<=anCurent)
                    { return anConstructie;}
                    return 1;
        }
    }*/

    //public String getStrada() {
    //    return strada;
    //}

    public  String getStrada() {
        return strada;
    }

    public int getNr() {
        return nr;
    }

    public char getScara() {
        return scara;
    }

    public int getEtaj() {
        return etaj;
    }

    public int getNrApt() {
        return nrApt;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSuprafata(float suprafata) {
        this.suprafata = suprafata;
    }

    public void setAnConstructie(int anConstructie) {
        this.anConstructie = anConstructie;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }



    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setScara(char scara) {
        this.scara = scara;
    }

    public void setEtaj(int etaj) {
        this.etaj = etaj;
    }

    public void setNrApt(int nrApt) {
        this.nrApt = nrApt;
    }


}