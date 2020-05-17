package rental.controller;

import rental.error.RentalException;
import rental.model.RentalModel;
import rental.view.*;

public class RentalController {
    private RentalModel model = new RentalModel();

    public void selectKendaraan(String[] data){
        SelectKendaraanView view = new SelectKendaraanView();
        view.openForm(model.getPlat(data[3]), data);
    }

    public void rental(String[] data){
        model.insertRental(data);
        new MenuView();
    }

    public void readRental(){
        new RentalView(model.readRental());
    }

    public void pengembalian(String NIK, String plat){
        PengembalianFormView view = new PengembalianFormView();
        view.openForm(model.readRental(NIK, plat));
    }

    public void pengembalian(String[] data){
        model.updateRental(data);
        MenuView view = new MenuView();
    }

    public void penghasilan(){
        PenghasilanView view = new PenghasilanView();
        view.openForm();
    }

    public String getPenghasilan(String dateStart, String dateStop) throws RentalException{
        RentalModel model = new RentalModel();
        String penghasilan;
        if(model.getDiff(dateStart, dateStop) > 0){
            penghasilan = model.getPenghasilan(dateStart,dateStop);
            return penghasilan;
        }
        else{
            throw new RentalException("Tanggal awal harus lebih kecil");
        }
    }

    public String getWarna(String plat){
        return model.getWarna(plat);
    }

    public void cekLama(int value) throws RentalException {
        if(value <= 0)
            throw new RentalException("Lama harus lebih dari 0");
    }
}
