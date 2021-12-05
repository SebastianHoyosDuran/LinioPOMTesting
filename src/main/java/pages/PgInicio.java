package pages;

import org.openqa.selenium.By;

public class PgInicio {

    //Pagina 1

    By txtBuscador = By.xpath("//div[@class = 'input-group']//input[@type='search' and @placeholder='Busca productos']");
    By btnBuscador = By.xpath("//div[@class='input-group-btn']//button[@class='btn btn-primary btn-search']//span[@class='icon icon-inverse']");

    //Modal No te lo pierdas
    By btnPopUpCerrarNoTeLoPierdas = By.xpath("//div[@class='dy-lb-close']");

    //Pop up Ultimas novedades
    By btnPorAhoraNo = By.xpath("//button[@id='onesignal-slidedown-cancel-button']");

    public By getBtnPorAhoraNo() {
        return btnPorAhoraNo;
    }

    public By getTxtBuscador() {
        return txtBuscador;
    }

    public By getBtnBuscador() {
        return btnBuscador;
    }

    public By getBtnPopUpCerrarNoTeLoPierdas() {
        return btnPopUpCerrarNoTeLoPierdas;
    }
}
