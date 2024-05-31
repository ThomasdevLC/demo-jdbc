import fr.diginamic.jdbc.dao.FournisseurDao;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {

	public static void main(String[] args) {

		FournisseurDao fournisseurDao = new FournisseurDaoJdbc();

//		Fournisseur FranceDeMatériaux = new Fournisseur(4, "France de matériaux");
//		fournisseurDao.insert(FranceDeMatériaux);

//        
//        fournisseurDao.extraire();
//        
//		fournisseurDao.update("France de matériaux", "France matériaux");
//        
        Fournisseur fournisseurtoDelete = new Fournisseur(4, "France matériaux");
        fournisseurDao.delete(fournisseurtoDelete);

		fournisseurDao.extraire();

	}

}
