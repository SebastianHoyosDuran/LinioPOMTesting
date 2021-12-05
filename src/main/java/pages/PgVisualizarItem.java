package pages;

import org.openqa.selenium.By;

public class PgVisualizarItem {

    //Pagina 3

    By txtItemEncontrado = By.xpath("//div[contains(@class,product-title)]//h1[@class='col-12 product-title-box']//span[@itemprop='name' and @class='product-name']");

    By btnAnadirAlCarrito = By.xpath("//button[@id='buy-now']");

    By btnSeguirComprando = By.xpath(//"//button[text()[contains(.,'Seguir comprando')]]"
    "//div[@class='modal-dialog modal-dialog-fluid success']//div[@class='alert alert-body']//a[@class='header-icon-close-container btn-link']//span[@class='icon header-icon-close']");

    By txtItemSeleccionado = By.xpath("//h1[@class='col-12 product-title-box']//span[contains(text(),'producto')]");

    public By getTxtItemEncontrado() {
        return txtItemEncontrado;
    }

    public By getTxtItemSeleccionado() {
        return txtItemSeleccionado;
    }

    public void setTxtItemSeleccionado(String nombreProducto) {
        this.txtItemSeleccionado = By.xpath("//h1[@class='col-12 product-title-box']//span[contains(text(),'"+nombreProducto+"')]");

    }

    public void setTxtElementoEncontrado(String producto) {
        this.txtItemEncontrado = By.xpath("//h1//*[text()[contains(.,'"+producto+"')]]");
    }

    public By getBtnAnadirAlCarrito() {
        return btnAnadirAlCarrito;
    }

    public By getBtnSeguirComprando() {
        return btnSeguirComprando;
    }
}
