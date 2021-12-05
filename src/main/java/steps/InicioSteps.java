package steps;

import Files.LecturaExcel;
import drivers.GoogleChromeDriver;
import pages.PgInicio;

import java.io.IOException;

public class InicioSteps {

    PgInicio pgInicio = new PgInicio();
    BusquedaItemSteps busquedaItemSteps = new BusquedaItemSteps();
    VisualizarItemSteps visualizarItemSteps = new VisualizarItemSteps();
    LecturaExcel lecturaExcel = new LecturaExcel();

    public void  abrirPagina(){
        GoogleChromeDriver.ChromeDriver("https://www.linio.com.co/");
    }

    public void cerrarPagina(){
        GoogleChromeDriver.driver.quit();
    }

    public void cerrarModalInicio(){
        if (GoogleChromeDriver.driver.findElement(pgInicio.getBtnPopUpCerrarNoTeLoPierdas()).isEnabled()){
            GoogleChromeDriver.driver.findElement(pgInicio.getBtnPopUpCerrarNoTeLoPierdas()).click();
        }
    }

    public void buscarItem(String item) {
        GoogleChromeDriver.driver.findElement(pgInicio.getTxtBuscador()).sendKeys(item);
        GoogleChromeDriver.driver.findElement(pgInicio.getBtnBuscador()).click();
        busquedaItemSteps.encontrarItem(item);
    }

    public void cerrarPopUpUltimasNovedades(){
        if (GoogleChromeDriver.driver.findElement(pgInicio.getBtnPorAhoraNo()).isDisplayed())
            GoogleChromeDriver.driver.findElement(pgInicio.getBtnPorAhoraNo()).click();
    }

    public void buscarAnadirItem() throws IOException, InterruptedException {
        for (int i = 0 ; i < lecturaExcel.leerDatosDeHojaDeExcel("Productos.xlsx", "Hoja1").size() ; i++ ){
            buscarItem(lecturaExcel.leerDatosDeHojaDeExcel("Productos.xlsx", "Hoja1").get(i).get("Lista de productos"));
            visualizarItemSteps.validarNombre(lecturaExcel.leerDatosDeHojaDeExcel("Productos.xlsx", "Hoja1").get(i).get("Lista de productos"));
            visualizarItemSteps.anadirAlCarrito();
        }
        //lecturaExcel.leerDatosDeHojaDeExcel("Productos.xlsx", "Hoja1");
    }

}
