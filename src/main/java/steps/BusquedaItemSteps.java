package steps;

import drivers.GoogleChromeDriver;

import pages.PgBusquedaItem;



public class BusquedaItemSteps {

    PgBusquedaItem pgBusquedaItem = new PgBusquedaItem();
    VisualizarItemSteps visualizarItemSteps = new VisualizarItemSteps();

    public void encontrarItem(String item){
        pgBusquedaItem.setTxtElementoBuscado(item);
        GoogleChromeDriver.driver.findElement(pgBusquedaItem.getTxtElementoBuscado()).click();
    }
}
