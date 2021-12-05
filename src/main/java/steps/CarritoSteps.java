package steps;

import drivers.GoogleChromeDriver;
import org.junit.Assert;
import pages.PgCarrito;

public class CarritoSteps {

    PgCarrito pgCarrito = new PgCarrito();

    public void abrirCarrito() {
        GoogleChromeDriver.driver.findElement(pgCarrito.getBtnAbrirCarrito()).click();
    }

    public void   validarValorTotal(String valor) {
        Assert.assertEquals("El valor no hace coincidencia",valor,GoogleChromeDriver.driver.findElement(pgCarrito.getTxtTotal()).getText());
    }

}
