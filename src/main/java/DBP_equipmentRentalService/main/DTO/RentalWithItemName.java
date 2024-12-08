package DBP_equipmentRentalService.main.DTO;

import DBP_equipmentRentalService.main.domain.Rental;

public class RentalWithItemName {
    private final Rental rental;
    private final String itemName;
    private final int overdue;

    public RentalWithItemName(Rental rental, String itemName, int overdue) {
        this.rental = rental;
        this.itemName = itemName;
        this.overdue = overdue;
    }

    public Rental getRental() {
        return rental;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemId() {
        return rental.getItemId();
    }

    public String getRentalId() {
        return rental.getRentalId();
    }

    public int getOverdue() {
        return overdue > 0 ? overdue : 0;
    }
}