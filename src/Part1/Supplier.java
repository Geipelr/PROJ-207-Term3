//Author: Gustavo Lourenco Moises
//Date 9/20/2020
//Thread Project
//Workshop 6
package Part1;

public class Supplier {
    private int SupplierId;
    private String SupplierName;

    public Supplier() {
    }

    public Supplier(int supplierId, String supplierName) {
        SupplierId = supplierId;
        SupplierName = supplierName;
    }

    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        SupplierId = supplierId;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    @Override
    public String toString() {
        return  SupplierName;
    }
}
