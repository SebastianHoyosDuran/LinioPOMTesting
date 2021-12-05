package pages;

import org.openqa.selenium.By;

public class PgBusquedaItem {

    //Pagina 2


    By txtElementoBuscado = By.xpath("//div[@class='image-container']//figure//picture[@class='image-wrapper']//source[@type='image/webp' and @srcset='//i.linio.com/p/83c2973b80211132e46892e5ed1b87ec-catalog.webp']");

    public By getTxtElementoBuscado() {
        return txtElementoBuscado;
    }

    public void setTxtElementoBuscado(String producto) {
        this.txtElementoBuscado = By.xpath("//div[@class='detail-container']//*[text()[contains(.,'"+producto+"')]]");
    }


}
