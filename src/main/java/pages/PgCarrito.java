package pages;

import org.openqa.selenium.By;

public class PgCarrito {

    //Pagina 4

    By btnAbrirCarrito = By.xpath("//span[@class='quantity-icon quantity-icon-notification' and @id='cart-counter']");
    By txtTotal = By.xpath("//li[@class='summary-total']//h3[@class='d-flex']//span[@class='price-main-md float-right ml-auto' and @ng-bind='cart.data.grandTotal|formatMoney']");

    public By getBtnAbrirCarrito() {
        return btnAbrirCarrito;
    }

    public By getTxtTotal() {
        return txtTotal;
    }




}
