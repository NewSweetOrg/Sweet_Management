package services;

import models.Supplier;
import java.util.LinkedList;
import java.util.List;

public class SupplierService {

    private List<Supplier> suppliers = new LinkedList<>();

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public Supplier getSupplierById(int id) {
        for (Supplier supplier : suppliers) {
            if (supplier.getId() == id) {
                return supplier;
            }
        }
        return null;
    }

    public void updateSupplier(int id, String name, String number, String address) {
        Supplier supplier = getSupplierById(id);
        if (supplier != null) {
            supplier.setName(name);
            supplier.setPhone(Integer.parseInt(number)); // Assuming phone number is stored as an int
            supplier.setAddress(address);
        }
    }
}