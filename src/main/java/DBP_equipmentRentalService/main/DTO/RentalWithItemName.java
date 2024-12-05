package DBP_equipmentRentalService.main.DTO;

import DBP_equipmentRentalService.main.domain.Rental;

public class RentalWithItemName{
    private final Rental rental;
    private final String itemName;

    public RentalWithItemName(Rental rental, String itemName){
        this.rental = rental;
        this.itemName = itemName;
    }

    public Rental getRental(){
        return rental;
    }

    public String getItemName(){
        return itemName;
    }

    public String getItemId(){
        return rental.getItemId();
    }

    public String getRentalId(){
        return rental.getRentalId();
    }
}