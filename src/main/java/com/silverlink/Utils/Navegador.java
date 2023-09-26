package com.silverlink.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class Navegador {

    public static ChromeDriver driver;
    private int nroDoc = 1;

    static {
        System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--window-size=1920,1080","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage", "--remote-allow-origins=*");
        options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage", "--remote-allow-origins=*");

        String downloadFilepath = "D:\\Temp";
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
    }

    public void abrirSesionSalesforce() {
        driver.get("https://enelsud.my.salesforce.com/?ec=302&startURL=%2Fconsole");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("Login"));

        usernameField.sendKeys("mensajeria@enel.com");
        passwordField.sendKeys("25*Jul*2023*");
        loginButton.click();
    }

    public void descargarReporte() throws InterruptedException {

//        //TODO: Si no hay ninguna pestaña abierta, no ejecutar este código
        WebElement ddMenu = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]"));
        ddMenu.click();
        WebElement menuCerrarFichas = driver.findElement(By.xpath("//span[text()='Cerrar todas las fichas principales']"));
        menuCerrarFichas.click();
        Thread.sleep(1000); //NO esperar después de cerrar fichas (para ese momento ya está cargado el menú principal)

        //div="ext-comp-1004"
        //iframe id="ext-comp-1005"
        WebElement mainIFrame = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div/div/iframe"));
        driver.switchTo().frame(mainIFrame);
        WebElement infCorrespondenciaDigital = driver.findElement(By.xpath("//*[@id=\"00O1o000005aVRP_NAME\"]/div[2]/a"));
        infCorrespondenciaDigital.click();
        Thread.sleep(18000);

        driver.switchTo().defaultContent();
        WebElement corDigIFrame = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div/iframe"));
        driver.switchTo().frame(corDigIFrame);

        WebElement btnExportar = driver.findElement(By.name("csvsetup"));
        btnExportar.click();
        WebElement btnExportar2 = driver.findElement(By.name("export"));
        btnExportar2.click();
        WebElement btnListo = driver.findElement(By.name("cancel"));
        btnListo.click();
    }

    public void rechazarLightningExp() {
        driver.get("https://enelsud.my.salesforce.com/home/home.jsp");

        try {
            WebElement noGraciasJoven = driver.findElement(By.id("lexNoThanks"));
            noGraciasJoven.click();
            WebElement noGraciasJoven2 = driver.findElement(By.id("lexSubmit"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("Too fast to click the button");
            }
            noGraciasJoven2.click();
        } catch (NoSuchElementException nsee) {
            System.out.println("No gracias Lightning Exp.");
        }
    }

    public int descargarArchivos(String idActividad) {
        int cantArchivos;

        driver.get("https://enelsud.my.salesforce.com/" + idActividad);

        WebElement archivoLink;
        for (int i = 2; true; i++) {
            try {
            archivoLink = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/a[text()='Descargar']"));
            archivoLink.click();
            } catch (NoSuchElementException nsee) {
                cantArchivos = i-2;
                System.out.println(nroDoc + ". Nro. de archivos en " + idActividad + " : " + cantArchivos);
                break;
            }
        }
        nroDoc++;
        return cantArchivos;
    }

}
