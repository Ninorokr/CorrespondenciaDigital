package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;
import com.silverlink.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;

public class Navegador {

    public static ChromeDriver driver;
    private int nroDoc = 1;
    static boolean isArchivoDescargado = false;

    static {
        System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage", "--remote-allow-origins=*");
//        options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage", "--remote-allow-origins=*");

        String downloadFilepath = "Z:\\Servicios ENEL\\002 - Correspondencia digital\\Temp";
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
    }

    public void abrirSesionSalesforce() {
        System.out.println("Abriendo Salesforce.com");
        driver.get("https://enelsud.my.salesforce.com/console");
//        driver.get("https://enelsud.my.salesforce.com/?ec=302&startURL=%2Fconsole");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("Login"));

        usernameField.sendKeys("mensajeria@enel.com");
        passwordField.sendKeys("25*Jul*2023*");
        System.out.println("Logueandose a Salesforce.com como mensajeria@enel.com");
        loginButton.click();
        Main.isDriverOpen = true;
    }

    public void descargarReporte() {

        isArchivoDescargado = false;
        driver.get("https://enelsud.my.salesforce.com/console");

        try {
//            List<WebElement> anyOpenTab = driver.findElements(By.xpath("/html/body/div[4]/div/div[2]/div[2]/ul/li"));
//
//            if(anyOpenTab.size() > 0) {
//                if(!(anyOpenTab.get(0).getText().equals("Correspondencia Digital (pendiente)"))){
                //Si no hay ninguna pestaña abierta, no ejecutar este código
//                WebElement ddMenu = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]"));
                WebElement ddMenu = driver.findElement(By.xpath("//div[@class='x-tab-tabmenu-right']"));
                ddMenu.click();

//                List<WebElement> openTabs = driver.findElements(By.xpath("/html/body/div[4]/div/div[2]"));
//                List<WebElement> openTabs = driver.findElements(By.xpath("/html/body/div[4]/div/div[2]"));
//                openTabs.get(openTabs.size()-1).click();
                WebElement menuCerrarFichas = driver.findElement(By.xpath("//span[text()='Cerrar todas las fichas principales']"));
                menuCerrarFichas.click();
                System.out.println("Cerrando fichas abiertas");
                try { Thread.sleep(1000); } catch (InterruptedException ie) {} //Tiempo de espera a que reaccione la página
//                }
//            }

            WebElement mainIFrame = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div/div/iframe"));
            driver.switchTo().frame(mainIFrame);
            try { WebElement infCorrespondenciaDigital = driver.findElement(By.xpath("//*[@id=\"00O1o000005aVRP_NAME\"]/div[2]/a"));
            infCorrespondenciaDigital.click();
            System.out.println("Abriendo informe de Correspondencia Digital (pendiente)");
            } catch (NoSuchElementException nsee) { System.out.println("No se ubicó el enlace al Inf. de Correspondencia Digital (pendiente)"); }
//            Thread.sleep(18000);

            driver.switchTo().defaultContent();
            try { WebElement corDigIFrame = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div/iframe"));
            driver.switchTo().frame(corDigIFrame); } catch (NoSuchElementException nsee) { System.out.println("No se ubicó el iframe de Correspondencia Digital (pendiente)"); }

//            new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.
//                    presenceOfElementLocated(By.name("csvsetup")));
            List<WebElement> btnExportarList = driver.findElements(By.name("csvsetup"));
            while (!(btnExportarList.size() > 0)) {
                btnExportarList = driver.findElements(By.name("csvsetup"));
            }
            WebElement btnExportar = driver.findElement(By.name("csvsetup"));
            btnExportar.click();

            List<WebElement> btnExportar2List = driver.findElements(By.name("export"));
            while (!(btnExportar2List.size() > 0)) {
                btnExportar2List = driver.findElements(By.name("export"));
            }
            WebElement btnExportar2 = driver.findElement(By.name("export"));
            btnExportar2.click();
            System.out.println("Exportando listado de casos en .xls");

            while(!isArchivoDescargado) {
                Files.walkFileTree(Path.of(Main.tempPath), new SimpleFileVisitor<>(){
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        isArchivoDescargado = true;
                        return super.visitFile(file, attrs);
                    }
                });
            }

        } catch (Exception e) {
            System.out.println("No se pudo descargar el reporte de casos :'(");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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

    public int descargarArchivosCaso(Caso caso) {
        int cantArchivos;

        driver.get("https://enelsud.my.salesforce.com/" + caso.getIdActividad());

        WebElement archivoLink;
        for (int i = 2; true; i++) {
            try {
            archivoLink = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/a[text()='Descargar']"));
            archivoLink.click();
            } catch (NoSuchElementException nsee) {
                cantArchivos = i-2;
                System.out.println(nroDoc + ". Nro. de archivos en " + caso.getIdActividad() + " : " + cantArchivos);
                break;
            }
        }
        nroDoc++;
        return cantArchivos;
    }

}
