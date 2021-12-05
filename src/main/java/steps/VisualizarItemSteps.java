package steps;

import drivers.GoogleChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.PgVisualizarItem;

public class VisualizarItemSteps {

    GoogleChromeDriver driver = new GoogleChromeDriver();

    PgVisualizarItem pgVisualizarItem = new PgVisualizarItem();

    public void validarNombre(String nombreEsperado){
        pgVisualizarItem.setTxtItemSeleccionado(nombreEsperado);
        Assert.assertEquals("El nombre no es el esperado",nombreEsperado, GoogleChromeDriver.driver.findElement(pgVisualizarItem.getTxtItemSeleccionado()).getText());

    }


    public void anadirAlCarrito() throws InterruptedException {
        Thread.sleep(500);
        GoogleChromeDriver.driver.findElement(pgVisualizarItem.getBtnAnadirAlCarrito()).click();
        Thread.sleep(500);
        GoogleChromeDriver.driver.findElement(pgVisualizarItem.getBtnSeguirComprando()).click();
    }



}
