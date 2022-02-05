/**
 * @author Adriana BECICA
 * @grupa 3131b
 * @nr 1
 */

package ro.usv;


import java.util.List;

public interface IAsociatieProprietariServ1 {
    public void setStocare(String nume);
    public Apartament getApartamentById(int id);
    public List<Apartament> getApartamentente();
    public void saveApartament(Apartament ap);
    public void deleteApartment(int id);
    public void deleteApartmente();
    public int countApartamente(String tip);
    public List<Integer> findIdsNewerThan(int nrAni);
    public List<Integer> findIdsByStreet(String numeStrada);
}