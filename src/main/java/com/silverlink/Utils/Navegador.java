package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;
import com.silverlink.Entidades.Mes;
import com.silverlink.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.silverlink.Main.tempPath;
import static com.silverlink.Utils.Commander.*;

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
//        String downloadFilepath = "D:\\Servicios ENEL\\002 - Correspondencia digital\\Temp";
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", tempPath);
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
//        System.out.println("Ingresar código de verificación");
//        scanner.nextLine();
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
                Files.walkFileTree(Path.of(tempPath), new SimpleFileVisitor<>(){
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

    public void descargarCasoEnSalesforce(Caso caso) {
        driver.get("https://enelsud.my.salesforce.com/" + caso.getIdActividad());

        boolean ok = false;

        switch(caso.getEstado().getIdEstado()) {
            case 4: ok = descargarCasoNormal(caso); break;
            case 5: ok = descargarCasoRechazado(caso); break;
            case 6: ok = descargarCasoNotificada(caso); break;
        }
        if (ok) {
            updateDescargadoEnSalesforce(caso);
        }

    }

    private boolean descargarCasoNormal(Caso caso) {
        //TODO Correr con el bichito hasta asegurar funcionamiento correcto
        WebElement btnModificar = driver.findElement(By.xpath("//input[@title='Modificar' and @name='edit']"));
        btnModificar.click();

        Select cboBoxEstado = new Select(driver.findElement(By.id("tsk12")));
        cboBoxEstado.selectByValue("Notificada");
        ArrayList<Mes> meses = new ArrayList<>(List.of(Mes.values()));

        String fecEmisionDay = String.valueOf(caso.getFecEmisionDateTime().getDayOfMonth());
        String fecEmisionMonth = String.valueOf(meses.get(caso.getFecEmisionDateTime().getMonth().minus(1).getValue()));
        String fecEmisionYear = String.valueOf(caso.getFecEmisionDateTime().getYear());
        
        String fecDespachoDay = String.valueOf(caso.getFecDespacho().getDayOfMonth());
        String fecDespachoMonth = String.valueOf(meses.get(caso.getFecDespacho().getMonth().minus(1).getValue()));
        String fecDespachoYear = String.valueOf(caso.getFecDespacho().getYear());
        
        String fecNotificacionDay = String.valueOf(caso.getFecNotificacion().getDayOfMonth());
        String fecNotificacionMonth = String.valueOf(meses.get(caso.getFecNotificacion().getMonth().minus(1).getValue()));
        String fecNotificacionYear = String.valueOf(caso.getFecNotificacion().getYear());

//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
//        String fecEmision = dateFormat.format(Timestamp.valueOf(caso.getFecEmisionDateTime()));
//        String fecDespacho = dateFormat.format(Timestamp.valueOf(caso.getFecDespacho()));
//        String fecNotificacion = dateFormat.format(Timestamp.valueOf(caso.getFecNotificacion()));

        WebElement inputfecEmision = driver.findElement(By.id("00N3600000RPuD5"));
        WebElement inputfecDespacho = driver.findElement(By.id("00N1o00000Jw99J"));
        WebElement inputfecNotificacion = driver.findElement(By.id("00N1o00000Jw99P"));

        inputfecEmision.sendKeys("");
        Select calYearPicker = new Select(driver.findElement(By.id("calYearPicker")));
        calYearPicker.selectByVisibleText(fecEmisionYear);
        Select calMonthPicker = new Select(driver.findElement(By.id("calMonthPicker")));
        calMonthPicker.selectByVisibleText(fecEmisionMonth);
        WebElement calDayPicker = driver.findElement(By.xpath("//td[text()=\"" + fecEmisionDay +
                "\" and (@class=\"weekday\" or @class=\"weekday todayDate\" or @class=\"weekday selectedDate\" or @class=\"weekday todayDate selectedDate\")]"));
        calDayPicker.click();

//        inputfecEmision.clear();
//        inputfecEmision.sendKeys(fecEmision);
//        inputfecEmision.sendKeys("");

        inputfecDespacho.sendKeys("");
        calYearPicker = new Select(driver.findElement(By.id("calYearPicker")));
        calYearPicker.selectByVisibleText(fecDespachoYear);
        calMonthPicker = new Select(driver.findElement(By.id("calMonthPicker")));
        calMonthPicker.selectByVisibleText(fecDespachoMonth);
        calDayPicker = driver.findElement(By.xpath("//td[text()=\"" + fecDespachoDay +
                "\" and (@class=\"weekday\" or @class=\"weekday todayDate\" or @class=\"weekday selectedDate\" or @class=\"weekday todayDate selectedDate\")]"));
        calDayPicker.click();

//        inputfecDespacho.clear();
//        inputfecDespacho.sendKeys(fecDespacho);
//        inputfecDespacho.sendKeys("");

        inputfecNotificacion.sendKeys("");
        calYearPicker = new Select(driver.findElement(By.id("calYearPicker")));
        calYearPicker.selectByVisibleText(fecNotificacionYear);
        calMonthPicker = new Select(driver.findElement(By.id("calMonthPicker")));
        calMonthPicker.selectByVisibleText(fecNotificacionMonth);
        calDayPicker = driver.findElement(By.xpath("//td[text()=\"" + fecNotificacionDay +
                "\" and (@class=\"weekday\" or @class=\"weekday todayDate\" or @class=\"weekday selectedDate\" or @class=\"weekday todayDate selectedDate\")]"));
        calDayPicker.click();

//        inputfecNotificacion.clear();
//        inputfecNotificacion.sendKeys(fecNotificacion);
//        inputfecNotificacion.sendKeys("");

        WebElement txtAreaComentarios = driver.findElement(By.id("tsk6"));
        txtAreaComentarios.sendKeys(caso.getCorreosActasString());
        Select cboEjectuadoPor = new Select(driver.findElement(By.id("00N1o00000Jw99M")));
        cboEjectuadoPor.selectByVisibleText("Proveedor de Mensajería");

        //SÓLO TESTEO
//        WebElement btnCancelar = driver.findElement(By.xpath("//input[@title='Cancelar' and @name='cancel']"));
//        btnCancelar.click();
        WebElement btnGuardar = driver.findElement(By.xpath("//input[@title='Guardar' and @name='save']"));
        btnGuardar.click();
        WebElement lblDetalleDeTarea;

        try {
            //No funciona porque el elemento SIEMPRE existe
            //TODO verificar otra manera de capturar el error de caso cerrado
//            WebElement errorCasoCerrado = driver.findElement(By.xpath("//div[@id='errorDiv_ep']"));
            cboBoxEstado = new Select(driver.findElement(By.id("tsk12")));
            System.out.println("Caso cerrado");
            cboBoxEstado.selectByValue("Despachada");
            btnGuardar = driver.findElement(By.xpath("//input[@title='Guardar' and @name='save']"));
            btnGuardar.click();
            //TODO registrar caso como "Caso cerrado" en la BD
            updateCasoEstadoCasoCerrado(caso);
            try {
                lblDetalleDeTarea = driver.findElement(By.xpath("//h2[text()='Detalle de Tarea']"));
            } catch (NoSuchElementException nsee) {
                System.out.println("La tarea no se logró guardar correctamente");
                nsee.printStackTrace(); System.exit(0);
            }
            System.out.println("Se guardó tarea de caso cerrado como \"Despachada\"");
        } catch (NoSuchElementException nsee) {
            btnModificar = driver.findElement(By.xpath("//input[@title='Modificar' and @name='edit']"));
            btnModificar.click();
            cboBoxEstado = new Select(driver.findElement(By.id("tsk12")));
            cboBoxEstado.selectByValue("Descargada");
            btnGuardar = driver.findElement(By.xpath("//input[@title='Guardar' and @name='save']"));
            btnGuardar.click();
            try {
                lblDetalleDeTarea = driver.findElement(By.xpath("//h2[text()='Detalle de Tarea']"));
            } catch (NoSuchElementException nsee2) {
                System.out.println("La tarea no se logró guardar correctamente");
                nsee.printStackTrace(); System.exit(0);
            }
        }

        return true;
    }

    private boolean descargarCasoNotificada(Caso caso) {
        //TODO Correr con el bichito hasta asegurar funcionamiento correcto
        WebElement btnModificar = driver.findElement(By.xpath("//input[@title='Modificar' and @name='edit']"));
        btnModificar.click();

        Select cboBoxEstado = new Select(driver.findElement(By.id("tsk12")));
        cboBoxEstado.selectByVisibleText("Descargada");

        WebElement btnGuardar = driver.findElement(By.xpath("//input[@title='Guardar' and @name='save']"));
        btnGuardar.click();
        WebElement lblDetalleDeTarea;

        try {
            //No funciona porque el elemento SIEMPRE existe
            //TODO verificar otra manera de capturar el error de caso cerrado
//            WebElement errorCasoCerrado = driver.findElement(By.xpath("//div[@id='errorDiv_ep']"));
            cboBoxEstado = new Select(driver.findElement(By.id("tsk12")));
            System.out.println("Caso cerrado");
            cboBoxEstado.selectByValue("Despachada");
            System.out.println("Se guardó tarea de caso cerrado como \"Despachada\"");
            btnGuardar = driver.findElement(By.xpath("//input[@title='Guardar' and @name='save']"));
            btnGuardar.click();
            //TODO registrar caso como "Caso cerrado" en la BD
            try {
                lblDetalleDeTarea = driver.findElement(By.xpath("//h2[text()='Detalle de Tarea']"));
            } catch (NoSuchElementException nsee) {
                System.out.println("La tarea no se logró guardar correctamente");
                nsee.printStackTrace(); System.exit(0);
            }
            System.out.println("Se guardó tarea de caso cerrado como \"Despachada\"");
        } catch (NoSuchElementException nsee) {
            try {
                lblDetalleDeTarea = driver.findElement(By.xpath("//h2[text()='Detalle de Tarea']"));
            } catch (NoSuchElementException nsee2) {
                System.out.println("La tarea no se logró guardar correctamente");
                nsee.printStackTrace(); System.exit(0);
            }
        }
        return true;
    }

    private boolean descargarCasoRechazado(Caso caso) {
        //TODO Correr con el bichito hasta asegurar funcionamiento correcto
        WebElement btnModificar = driver.findElement(By.xpath("//input[@title='Modificar' and @name='edit']"));
        btnModificar.click();

        Select cboBoxEstado = new Select(driver.findElement(By.id("tsk12")));
        cboBoxEstado.selectByVisibleText("Revisión ENEL");

        ArrayList<Mes> meses = new ArrayList<>(List.of(Mes.values()));

        if(!caso.isErrorFaltaCartas() && !caso.isErrorFaltaActas()){
            String fecEmisionDay = String.valueOf(caso.getFecEmisionDateTime().getDayOfMonth());
            String fecEmisionMonth = String.valueOf(meses.get(caso.getFecEmisionDateTime().getMonth().minus(1).getValue()));
            String fecEmisionYear = String.valueOf(caso.getFecEmisionDateTime().getYear());

//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
//        String fecEmision = dateFormat.format(Timestamp.valueOf(caso.getFecEmisionDateTime()));

            WebElement inputfecEmision = driver.findElement(By.id("00N3600000RPuD5"));
//        inputfecEmision.clear(); inputfecEmision.sendKeys(fecEmision);

            inputfecEmision.sendKeys("");
            Select calYearPicker = new Select(driver.findElement(By.id("calYearPicker")));
            calYearPicker.selectByVisibleText(fecEmisionYear);
            Select calMonthPicker = new Select(driver.findElement(By.id("calMonthPicker")));
            calMonthPicker.selectByVisibleText(fecEmisionMonth);
            WebElement calDayPicker = driver.findElement(By.xpath("//td[text()=\"" + fecEmisionDay +
                "\" and (@class=\"weekday\" or @class=\"weekday todayDate\") or @class=\"weekday selectedDate\" or @class=\"weekday todayDate selectedDate\"]"));
            calDayPicker.click();
        }

        WebElement txtAreaComentarios = driver.findElement(By.id("tsk6"));
        txtAreaComentarios.sendKeys(caso.getMensajeError());

        //SÓLO TESTEO
//        WebElement btnCancelar = driver.findElement(By.xpath("//input[@title='Cancelar' and @name='cancel']"));
//        btnCancelar.click();

        WebElement btnGuardar = driver.findElement(By.xpath("//input[@title='Guardar' and @name='save']"));
        btnGuardar.click();
        WebElement lblDetalleDeTarea;

        try {
            //No funciona porque el elemento SIEMPRE existe
            //TODO verificar otra manera de capturar el error de caso cerrado
//            WebElement errorCasoCerrado = driver.findElement(By.xpath("//div[@id='errorDiv_ep']"));
            cboBoxEstado = new Select(driver.findElement(By.id("tsk12")));
            System.out.println("Caso cerrado");
            cboBoxEstado.selectByValue("Despachada");
            System.out.println("Se guardó tarea de caso cerrado como \"Despachada\"");
            btnGuardar = driver.findElement(By.xpath("//input[@title='Guardar' and @name='save']"));
            btnGuardar.click();
            //TODO registrar caso como "Caso cerrado" en la BD
            try {
                lblDetalleDeTarea = driver.findElement(By.xpath("//h2[text()='Detalle de Tarea']"));
            } catch (NoSuchElementException nsee) {
                System.out.println("La tarea no se logró guardar correctamente");
                nsee.printStackTrace(); System.exit(0);
            }
            System.out.println("Se guardó tarea de caso cerrado como \"Despachada\"");
        } catch (NoSuchElementException nsee) {
            try {
                lblDetalleDeTarea = driver.findElement(By.xpath("//h2[text()='Detalle de Tarea']"));
            } catch (NoSuchElementException nsee2) {
                System.out.println("La tarea no se logró guardar correctamente");
                nsee.printStackTrace(); System.exit(0);
            }
        }
        return true;
    }

}
