package rental.controller;

import rental.error.RentalException;
import rental.model.KendaraanModel;
import rental.model.MerkModel;
import rental.view.KendaraanFormView;
import rental.view.KendaraanView;
import rental.view.SpecificKendaraanView;
import rental.view.UpdateKendaraanView;

public class KendaraanController {

    public void createKendaraan(){
        MerkModel merk = new KendaraanModel(); //implementasi polymorphism
        KendaraanFormView form = new KendaraanFormView();
        form.openForm(merk.getMerk());
    }

    public void createKendaraan(String merk, String plat, String warna){
        KendaraanModel kendaraanModel = new KendaraanModel();
        kendaraanModel.createKendaraan(merk,plat,warna);
    }

    public void readKendaraan(String merk){
        KendaraanModel kendaraanModel = new KendaraanModel();
        new KendaraanView(kendaraanModel.readKendaraan(merk));
    }

    public void specificKendaraan(String where, String value){
        KendaraanModel kendaraanModel = new KendaraanModel();
        SpecificKendaraanView view = new SpecificKendaraanView();
        view.openDetail(kendaraanModel.readKendaraan(where, value));
    }

    public void updateKendaraan(String[] data){
        MerkModel merk = new MerkModel();
        UpdateKendaraanView form = new UpdateKendaraanView();
        form.openForm(merk.getMerk(),data);
    }

    public void updateData(String[] data){
        KendaraanModel model = new KendaraanModel();
        model.updateKendaraan(data); //{id, merk, plat, warna, status}
    }

    public void deleteData(String[] data) throws RentalException {
        String status = data[4]; //{id, merk, plat, warna, status}
        KendaraanModel model = new KendaraanModel();
        if(status.equals("1")){
            model.deleteData(data[0]);
        }
        else{
            throw new RentalException("Masih disewakan");
        }
    }
}
