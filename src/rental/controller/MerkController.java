package rental.controller;

import rental.error.RentalException;
import rental.model.MerkModel;
import rental.view.MerkFormView;
import rental.view.SpecificMerkView;
import rental.view.MerkView;
import rental.view.UpdateMerkView;

public class MerkController {

    public void readMerk(){
        MerkModel model = new MerkModel();
        new MerkView(model.readMerk());
    }

    public void createMerk(){
        MerkFormView form = new MerkFormView();
        form.openForm();
    }

    public void createMerk(String merk, String kategori, int tarif){
        MerkModel model = new MerkModel();
        model.createMerk(merk, kategori, tarif);
    }

    public void specificMerk(String column, String where){
        MerkModel merkModel = new MerkModel();
        SpecificMerkView view = new SpecificMerkView();
        view.openDetail(merkModel.readMerk(column, where));
    }

    public void updateMerk(String column, String where){
        MerkModel merkModel = new MerkModel();
        UpdateMerkView update = new UpdateMerkView();
        update.openForm(merkModel.readMerk(column, where));
    }

    public void updateMerk(String merk, String kategori, int tarif, String id){
        MerkModel merkModel = new MerkModel();
        merkModel.updateMerk(merk, kategori, tarif, id);
    }

    public void deleteMerk(String merk){
        MerkModel merkModel = new MerkModel();
        merkModel.deleteMerk(merk);
    }

    public void cekTarif(int tarif)throws RentalException {
        if(tarif<0){
            throw new RentalException("Tarif tidak boleh negatif");
        }
    }
}
