/**
 * @author Adriana BECICA
 * @grupa 3131b
 * @nr 1
 */

package ro.usv;

import ro.usv.dao.Dao;
import ro.usv.dao.SerializareDao;

import java.util.ArrayList;
import java.util.List;


public class AsociatieProprietariServ implements IAsociatieProprietariServ1 {

    /**variabilă de instanță privată de tipul (interfetei) Dao, pe care o
     foloseste pntru a accesa datele de tip Apartament memorate in mediul de stocare
     persistenta sau pseudo-persistenta.*/
    Dao<Apartament, Integer> sDao;

    /** constructorul fara argumente – care va utiliza serializarea in memorie a datelor
     (pseudo-persistenta, utilizata in teste)*/
    public AsociatieProprietariServ() {
        this.sDao = new SerializareDao<>();
    }

    /**constructorul cu un singur argment – un nume de fisier in care se vor serializa
     datelor (asigurandu-se persistenta)*/
    public AsociatieProprietariServ(String numeFisSerializare) {
        this.sDao = new SerializareDao<>(numeFisSerializare);
    }

    /**Metoda getApartamentenById(int id) returnează un obiect pe baza id-ului.
     Dacă nu se găsește apartamentul returneaza null. Spre exemplu, aceasta metoda
     returneaza doar rezultatul transmis de metoda get(id) din clasa
     SerializareDaoComplet.*/
    @Override
    public Apartament getApartamentById(int id) {
        return sDao.get(id);
    }

    /**Metoda getApartamentente() returnează toate obiectele-Apartament
     stocate în mod persistent sub forma unei liste java.util.List.*/
    @Override
    public List<Apartament> getApartamentente() {
        return sDao.getAll();
    }

    /**Metoda saveApartament(Apartament ...) are ca scop salvarea persistentă
     a unui obiect Apartament. Asigurați-vă că, atunci când salvați un Apartament
     nou, nu se utilizează ID-ul unui Apartament deja salvat. În acest caz aruncă o
     excepție IllegalArgumentException cu un mesaj de eroare corespunzător.
     IllegalArgumentException este realizata in SerializareDaoComplet*/
    @Override
    public void saveApartament(Apartament ap) {
        sDao.save(ap);
    }


    /**Metoda deleteApartament(int id) are rolul de a șterge un Apartament din
     sursa persistenta de date. În cazul în care apartamentul nu există, se arunca
     exceptia IllegalArgumentException cu un mesaj de eroare corespunzător.
     IllegalArgumentException este realizata in SerializareDaoComplet*/
    @Override
    public void deleteApartment(int id) {
        sDao.delete(id);
    }

    /**metoda setStocare(String nume) poate schimba instanta curenta a clasei SerializareDaoComplet cu una noua in care
     fisierul in care se vor serializa in continuare obiectele de tip Apartament are numele nume.
     Daca nume este null sau este sirul vid, atunci datele vor fi serializate in memorie.*/
    public void setStocare(String nume) {
        if (nume == null || nume.equals("")) {
            this.sDao = new SerializareDao<>();
        } else {
            this.sDao = new SerializareDao<>(nume);
        }
    }

    /**Metoda deleteApartmente() are rolul de a șterge toate Apartamentele din
     sursa persistenta de date.*/
    @Override
    public void deleteApartmente() {
        sDao.deleteAll();
    }

    /**Metoda countApartamente(String tip) -determina numărului total al tuturor apartamentelor locuinta sau sedii de firma
     (conform valorii parametrului tip)*/
    @Override
    public int countApartamente(String tip) {
        List<Apartament> apartamente = getApartamentente();
        int nr = 0;
        if (tip == null) {
            for (Apartament a : apartamente)
                nr++;
        }
        for (Apartament a : apartamente) {
            if (a.getTip().equals(tip))
                nr++;
        }

        return nr;
    }

    /**Metoda findIdsNewerThan(int nrAni)
     -gasirea ID-ul (ID-urile) celui (celor) mai recent construite Apartament(e)*/
    @Override
    public List<Integer> findIdsNewerThan(int nrAni) {
        List<Apartament> apartamente = getApartamentente();
        List<Integer> listaId = new ArrayList();
        for (Apartament a : apartamente) {
            if (a.getVechime() <= nrAni) {
                listaId.add(a.getId());
            }
        }

        return listaId;
    }

    /**Metoda findIdsByStreet(String numeStrada)
     -gasirea ID-ul (ID-urile) Apartamentului(elor) situate pe o anumita strada*/
    @Override
    public List<Integer> findIdsByStreet(String numeStrada) {
        List<Apartament> apartamente = getApartamentente();
        List<Integer> listaId = new ArrayList();
        for (Apartament a : apartamente) {
            if (a.getStrada().equals(numeStrada)) {
                listaId.add(a.getId());
            }
        }
        return listaId;
    }


}

