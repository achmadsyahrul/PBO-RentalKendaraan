package rental.controller;

import rental.model.CustomerModel;
import rental.model.MerkModel;
import rental.view.AddCustomerView;
import rental.view.CustomerFormView;
import rental.view.MenyewaFormView;

public class CustomerController {

    public void searchData(){
        AddCustomerView customerView = new AddCustomerView();
        customerView.openForm();
    }

    public void searchData(String NIK){
        CustomerModel customerModel = new CustomerModel();
        CustomerFormView view = new CustomerFormView();
        view.openForm(customerModel.searchData(NIK));
    }

    public void submitData(String[] data){
        CustomerModel customerModel = new CustomerModel();
        customerModel.submitData(data);
        MerkModel merkModel = new MerkModel();
        MenyewaFormView view = new MenyewaFormView();
        view.openForm(data[0], merkModel.getMerk());
    }
}
