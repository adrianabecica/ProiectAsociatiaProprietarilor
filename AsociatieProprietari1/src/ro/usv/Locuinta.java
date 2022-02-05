/**
 * @author Adriana BECICA
 * @grupa 3131b
 * @nr 1
 */


package ro.usv;


public class Locuinta extends Apartament{
    final static String tip ="L";
    private int nrPersoane=1;

    public Locuinta( int id, float suprafata, int anConstructie, String strada, int nr, char scara, int etaj, int nrApt, int nrPersoane) {
        super(tip, id, suprafata, anConstructie, strada, nr, scara, etaj, nrApt);
        this.nrPersoane=nrPersoane;
    }


    @Override
    public String toString() {
        return " {Tip=" + tip +
                ", "+ super.toString() +
                ", nrPersoane=" + nrPersoane +
                '}';
    }



}
