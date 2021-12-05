package stepsDefinitions;

import cucumber.api.java.en.*;
import steps.BusquedaItemSteps;
import steps.CarritoSteps;
import steps.InicioSteps;
import steps.VisualizarItemSteps;

import java.io.IOException;

public class BuscadorStepsDefinitons {

    //Importaciones de los steps
    BusquedaItemSteps busquedaItemSteps = new BusquedaItemSteps();
    CarritoSteps carritoSteps =  new CarritoSteps();
    InicioSteps inicioSteps = new InicioSteps();
    VisualizarItemSteps visualizarItemSteps = new VisualizarItemSteps();

    @Given("^que busco un producto en linio$")
    public void queBuscoUnProductoEnLinio() {
        inicioSteps.abrirPagina();
    }

    @When("^podre anadir el producto al carrito$")
    public void podreAnadirElProductoAlCarrito() throws InterruptedException, IOException {
        inicioSteps.buscarAnadirItem();
    }

    @Then("^Podre ver la pagina del carrito en pantalla$")
    public void podreVerLaPaginaDelCarritoEnPantalla() {
        carritoSteps.abrirCarrito();
        carritoSteps.validarValorTotal("$10.968.456");
        inicioSteps.cerrarPagina();
    }






}
