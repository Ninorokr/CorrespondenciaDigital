package com.silverlink;

import com.silverlink.Entidades.*;
import com.silverlink.Utils.Datasource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.HashMap;

import static com.silverlink.Querier.*;

public class Main {

    static {
//        Datasource.open();
    }

    public static ArrayList<CanalNotificacion> canalesNotificacion = queryCanalesNotificacion();
    public static ArrayList<CanalRegistro> canalesRegistro = queryCanalesRegistro();
    public static ArrayList<Estado> estados = queryEstados();
    public static ArrayList<EstadoCaso> estadosCaso = queryEstadosCaso();
    public static ArrayList<Prioridad> prioridades = queryPrioridades();
    public static ArrayList<Provincia> provincias = queryProvincias();
    public static ArrayList<TipoAtencion> tiposAtencion = queryTiposAtencion();
    public static ArrayList<TipoCarta> tiposCarta = queryTiposCarta();
    public static ArrayList<TipoRegCaso> tiposRegCaso = queryTiposRegCaso();
    public static ArrayList<Usuario> usuarios = queryUsuarios();

    public static void main(String[] args) throws InterruptedException {

//        System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage", "--remote-allow-origins=*");
////        options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage", "--remote-allow-origins=*");
//        String downloadFilepath = "Z:\\Servicios ENEL\\002 - Correspondencia digital\\23\\temp";
//        HashMap<String, Object> chromePrefs = new HashMap<>();
//        chromePrefs.put("profile.default_content_settings.popups", 0);
//        chromePrefs.put("download.default_directory", downloadFilepath);
//        options.setExperimentalOption("prefs", chromePrefs);
//        ChromeDriver driver = new ChromeDriver(options);
//        driver.get("https://enelsud.my.salesforce.com/?ec=302&startURL=%2Fconsole");
//
//        WebElement usernameField = driver.findElement(By.id("username"));
//        WebElement passwordField = driver.findElement(By.id("password"));
//        WebElement loginButton = driver.findElement(By.id("Login"));
//
//        usernameField.sendKeys("mensajeria@enel.com");
//        passwordField.sendKeys("25*Jul*2023*");
//        loginButton.click();
//        Thread.sleep(5000);
//
//        //TODO: Si no hay ninguna pestaña abierta, no ejecutar este código
//        WebElement ddMenu = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]"));
//        ddMenu.click();
//        WebElement menuCerrarFichas = driver.findElement(By.xpath("//span[text()='Cerrar todas las fichas principales']"));
//        menuCerrarFichas.click();
//        Thread.sleep(1000); //NO esperar después de cerrar fichas (para ese momento ya está cargado el menú principal)
//
//        //div="ext-comp-1004"
//        //iframe id="ext-comp-1005"
//        WebElement mainIFrame = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div/div/iframe"));
//        driver.switchTo().frame(mainIFrame);
//        WebElement infCorrespondenciaDigital = driver.findElement(By.xpath("//*[@id=\"00O1o000005aVRP_NAME\"]/div[2]/a"));
//        infCorrespondenciaDigital.click();
//        Thread.sleep(18000);
//
//        driver.switchTo().defaultContent();
//        WebElement corDigIFrame = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div/iframe"));
////        WebElement corDigIFrame = driver.findElement(By.id("ext-comp-1036"));
//        driver.switchTo().frame(corDigIFrame);
//        // No es necesario ordenar las filas (eso solo es para humanos)
////        WebElement thfecCreacion = driver.findElement(By.xpath("//*[@title=\"Fecha de creación - Haga clic para clasificar en orden ascendente\"]"));
////        thfecCreacion.click();
////        Thread.sleep(18000);
//        WebElement btnExportar = driver.findElement(By.name("csvsetup"));
//        btnExportar.click();
//        WebElement btnExportar2 = driver.findElement(By.name("export"));
//        btnExportar2.click();
////        Thread.sleep(18000);
//        WebElement btnListo = driver.findElement(By.name("cancel"));
//        btnListo.click();
////        Thread.sleep(18000);

        RegistradorDeCasos.registrarCasos();
    }
}
