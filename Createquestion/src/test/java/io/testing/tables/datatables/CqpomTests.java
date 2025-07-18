package io.testing.tables.datatables;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CqpomTests {
	@FindBy(name = "username")
	public static WebElement user;
	@FindBy(name = "password")
	public static WebElement paswd;
	@FindBy(xpath = "//button[contains(text(),'Login')]")
	public static WebElement login;
	@FindBy(xpath = "//button[@class=\"btn btn-primary\"]")
	public static WebElement verify;
	@FindBy(xpath = "/html/body/app/vtwo/html/body/div/aside/section/sidebar-menu-v2/ul/li[3]/a")
	public static WebElement Cxdetails;
	@FindBy(xpath = "//span[contains(text(),'Dictionaries')]")
	public static WebElement dictionaries;
	@FindBy(xpath = "//a[@id='BusinessUnit']")
	public static WebElement businessunit;   
    @FindBy(id = "autcreatebusunit")
	public static WebElement createbu;
    @FindBy(id = "busunitname")//busunitname,busunitdes,busunitstartdate,busunitenddate
	public static WebElement name;
    @FindBy(id = "busunitdes")
	public static WebElement desc;
    @FindBy(id = "busunitstartdate")
	public static WebElement startdate;
    @FindBy(id = "btneditfax")
	public static WebElement save;
    @FindBy(xpath = "(//input[@ref='eColumnFloatingFilter'])[1]")
	public static WebElement searchname;
    @FindBy(id = "busRowEdit")
	public static WebElement edit;
    @FindBy(id = "busunitenddate")
	public static WebElement enddate;
    @FindBy(id = "btneditfax")
	public static WebElement update;
    @FindBy(id = "//input[@id='busunitname']")
   	public static WebElement Name;
       


}
