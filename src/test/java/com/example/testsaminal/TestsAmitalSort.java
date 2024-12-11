package com.example.testsaminal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsAmitalSort {

	private WebDriver driver;
	private WebDriverWait wait;

	private static final String DRIVER_PATH = "C:\\chromedriver-win64\\chromedriver.exe";
	private static final String DRIVER_NAME = "webdriver.chrome.driver";
	private static final String BASE_URL = "https://amital.ru/";
	private static final String CATEGORY_URL_HUD_LIT = "https://amital.ru/Hudozhyestvyennaya-lityeratura-c1.html";
	private static final String CATEGORY_URL_HUD_LIT_DETECTIVE = "https://amital.ru/Hudozhyestvyennaya-lityeratura/Dyetyektivy-Boyeviki-Avantyurnyy-roman-c1c101.html";
	private static final String CATEGORY_URL_HUD_LIT_DETECTIVE_SORTED_BY_PRICE_DESC =
			"https://amital.ru/Hudozhyestvyennaya-lityeratura/Dyetyektivy-Boyeviki-Avantyurnyy-roman-c1c101.html?sort=p.price&order=DESC";
	private static final String CATEGORY_URL_HUD_LIT_SORTED_BY_QUANTITY_ASC =
			"https://amital.ru/Hudozhyestvyennaya-lityeratura-c1.html?sort=p.quantity&order=ASC";
	private static final String CATEGORY_URL_DETI_SORTED_BY_DATE = "https://amital.ru/Dyetyam-i-rodityelyam-c36.html?sort=p.date_available";
	private static final String CATEGORY_URL_DETI = "https://amital.ru/Dyetyam-i-rodityelyam-c36.html";
	private static final String CATEGORY_URL_GAMES = "https://amital.ru/Igry-c236.html";
	private static final String CATEGORY_URL_DETI_PRESCHOOL = "https://amital.ru/Dyetyam-i-rodityelyam/Lityeratura-dlya-doshkol-nikov-c36c107.html";
	private static final String CATEGORY_URL_NEHUD_LIT = "https://amital.ru/Nyehudozhyestvyennaya-lityeratura-c15.html";
	private static final String CATEGORY_URL_ZARUB_CLASS_SORT_BY_PRICE_ASC =
			"https://amital.ru/Hudozhyestvyennaya-lityeratura/Zarubyezhnaya-lityeratura/Zarubyezhnaya-klassika-c1c260c261.html?sort=p.price&order=ASC";
	private static final String CATEGORY_URL_DETI_PRESCHOOL_SORTED_BY_DATE =
			"https://amital.ru/Dyetyam-i-rodityelyam/Lityeratura-dlya-doshkol-nikov-c36c107.html?sort=p.date_available";

	private static final String CATALOGUE_TEXT = "Каталог";
	private static final String HUD_LIT_TEXT = "Художественная литература";
	private static final String NEHUD_LIT_TEXT = "Нехудожественная литература";
	private static final String CHILDREN_TEXT = "Детям и родителям";
	private static final String GAMES_TEXT = "Игры";
	private static final String PRESCHOOL_TEXT = "Литература для дошкольников";
	private static final String ZARUB_LIT_TEXT = "Зарубежная литература";
	private static final String ZARUB_CLASS_TEXT = "Зарубежная классика";
	private static final String DETECTIVE_LIT_TEXT = "Детективы. Боевики. Авантюрный роман";
	private static final String SORT_DEFAULT_TEXT = "Сортировка: По умолчанию";
	private static final String SORT_QUANTITY_ASC_TEXT = "Наличие (по возрастанию)";
	private static final String SORT_PRICE_DESC_TEXT = "Сначала подороже";
	private static final String SORT_PRICE_ACS_TEXT = "Сначала подешевле";
	private static final String SORT_DATE_TEXT = "Сперва новинки";
	private static final String SORT_DISCOUNT_TEXT = "Сперва товары со скидками";

	private static final String INCORRECT_URL = "URL страницы категории некорректен.";
	private static final String INCORRECT_RESULT_PRODUCT_NAME = "Названия продуктов не совпадают";


	@BeforeEach
	public void setUp() {
		System.setProperty(DRIVER_NAME, DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// 1) категория -> без сортировки (дефолтная)
	@Test
	public void testCatalogNavigationAndProductComparison() {
		// Шаг 1: Получить ожидаемое название первого продукта через прямую ссылку
		String expectedFirstProductName = getExpectedFirstItemName(CATEGORY_URL_HUD_LIT);

		// Шаг 2: Выполнить переход на главную страницу
		setUrl(BASE_URL);

		// Клик по "Каталог"
		getWebElementByLinkText(CATALOGUE_TEXT).click();

		// Клик по "Художественная литература"
		getWebElementByLinkText(HUD_LIT_TEXT).click();

		// Проверяем, что перешли на нужный URL
		assertEquals(CATEGORY_URL_HUD_LIT, driver.getCurrentUrl(), INCORRECT_URL);

		// Шаг 3: Получить фактическое название первого продукта после перехода через меню
		String actualFirstProductName = getFirstElemName();

		// Шаг 4: Сравнить ожидаемое и фактическое названия первого продукта
		assertEquals(expectedFirstProductName, actualFirstProductName, INCORRECT_RESULT_PRODUCT_NAME);
	}

	// 2) категория -> сортировка
	@Test
	public void testCatalogSortingCategotyByAvailabilityAsc() {
		// Шаг 1: Получить ожидаемое название первого продукта через прямую ссылку
		String expectedFirstProductName = getExpectedFirstItemName(CATEGORY_URL_HUD_LIT_SORTED_BY_QUANTITY_ASC);

		// Шаг 2: Выполнить переход на главную страницу
		setUrl(BASE_URL);

		// Клик по "Каталог"
		getWebElementByLinkText(CATALOGUE_TEXT).click();

		// Клик по "Художественная литература"
		getWebElementByLinkText(HUD_LIT_TEXT).click();

		// Проверяем, что перешли на нужный URL
		assertEquals(CATEGORY_URL_HUD_LIT, driver.getCurrentUrl(), INCORRECT_URL);

		// Шаг 3: Выбор сортировки "Наличие (по возрастанию)"
		WebElement sortMenu = getWebElementByLinkText(SORT_DEFAULT_TEXT);
		sortMenu.click();
		sortMenu.click();
		getWebElementByLinkText(SORT_QUANTITY_ASC_TEXT).click();

		// Шаг 4: Получить фактическое название первого продукта после сортировки через меню
		String actualFirstProductName = getFirstElemName();

		// Шаг 5: Сравнить ожидаемое и фактическое названия первого продукта
		assertEquals(expectedFirstProductName, actualFirstProductName, INCORRECT_RESULT_PRODUCT_NAME);
	}

	// 3) категория -> подкатегория -> сортировка
	@Test
	public void testCatalogSortingSubcategoryByPriceDesc() {
		// Шаг 1: Получить ожидаемое название первого продукта через прямую ссылку
		String expectedFirstProductName = getExpectedFirstItemName(CATEGORY_URL_HUD_LIT_DETECTIVE_SORTED_BY_PRICE_DESC);

		// Шаг 2: Выполнить переход на главную страницу
		setUrl(BASE_URL);

		// Клик по "Каталог"
		getWebElementByLinkText(CATALOGUE_TEXT).click();

		// Клик по "Художественная литература"
		getWebElementByLinkText(HUD_LIT_TEXT).click();

		// Клик по "Детективы. Боевики. Авантюрный роман"
		getWebElementByLinkText(DETECTIVE_LIT_TEXT).click();

		// Проверяем, что перешли на нужный URL
		assertEquals(CATEGORY_URL_HUD_LIT_DETECTIVE, driver.getCurrentUrl(), INCORRECT_URL);

		// Шаг 3: Выбор сортировки "Сначала подороже"
		WebElement sortMenu = getWebElementByLinkText(SORT_DEFAULT_TEXT);
		sortMenu.click();
		sortMenu.click();
		getWebElementByLinkText(SORT_PRICE_DESC_TEXT).click();

		// Шаг 4: Получить фактическое название первого продукта после сортировки через меню
		String actualFirstProductName = getFirstElemName();

		// Шаг 5: Сравнить ожидаемое и фактическое названия первого продукта
		assertEquals(expectedFirstProductName, actualFirstProductName, INCORRECT_RESULT_PRODUCT_NAME);
	}

	// 4) категория -> подкатегория -> сортировка -> категория
	@Test
	public void testCatalogSortingSubcategoryBackToCategoryByDate() {
		// Шаг 1: Получить ожидаемое название первого продукта через прямую ссылку
		String expectedFirstProductName = getExpectedFirstItemName(CATEGORY_URL_DETI_SORTED_BY_DATE);

		// Шаг 2: Выполнить переход на главную страницу
		setUrl(BASE_URL);

		// Клик по "Каталог"
		getWebElementByLinkText(CATALOGUE_TEXT).click();

		// Клик по "Детям и родителям"
		getWebElementByLinkText(CHILDREN_TEXT).click();

		// Клик по "Литература для дошкольников"
		getWebElementByLinkText(PRESCHOOL_TEXT).click();

		// Проверяем, что перешли на нужный URL
		assertEquals(CATEGORY_URL_DETI_PRESCHOOL, driver.getCurrentUrl(), INCORRECT_URL);

		// Шаг 3: Выбор сортировки "Сперва новинки"
		WebElement sortMenu = getWebElementByLinkText(SORT_DEFAULT_TEXT);
		sortMenu.click();
		sortMenu.click();
		getWebElementByLinkText(SORT_DATE_TEXT).click();

		// Клик по "Детям и родителям"
		getWebElementByLinkText(CHILDREN_TEXT).click();

		// Шаг 4: Получить фактическое название первого продукта после сортировки через меню
		String actualFirstProductName = getFirstElemName();

		// Шаг 5: Сравнить ожидаемое и фактическое названия первого продукта
		assertEquals(expectedFirstProductName, actualFirstProductName, INCORRECT_RESULT_PRODUCT_NAME);
	}

	// 5) категория -> сортировка -> подкатегория
	@Test
	public void testCatalogSortingCategoryToSubcategoryByDate() {
		// Шаг 1: Получить ожидаемое название первого продукта через прямую ссылку
		String expectedFirstProductName = getExpectedFirstItemName(CATEGORY_URL_DETI_PRESCHOOL_SORTED_BY_DATE);

		// Шаг 2: Выполнить переход на главную страницу
		setUrl(BASE_URL);

		// Клик по "Каталог"
		getWebElementByLinkText(CATALOGUE_TEXT).click();

		// Клик по "Детям и родителям"
		getWebElementByLinkText(CHILDREN_TEXT).click();

		assertEquals(CATEGORY_URL_DETI, driver.getCurrentUrl(), INCORRECT_URL);

		// Шаг 3: Выбор сортировки "Сперва новинки"
		WebElement sortMenu = getWebElementByLinkText(SORT_DEFAULT_TEXT);
		sortMenu.click();
		sortMenu.click();
		getWebElementByLinkText(SORT_DATE_TEXT).click();

		// Клик по "Литература для дошкольников"
		getWebElementByLinkText(PRESCHOOL_TEXT).click();

		// Шаг 4: Получить фактическое название первого продукта после сортировки через меню
		String actualFirstProductName = getFirstElemName();

		// Шаг 5: Сравнить ожидаемое и фактическое названия первого продукта
		assertEquals(expectedFirstProductName, actualFirstProductName, INCORRECT_RESULT_PRODUCT_NAME);
	}


	// 6) категория -> сортировка -> другая категория
	@Test
	public void testCatalogSortingCategoryToAnotherCategoryByDiscountDesc() {
		// Шаг 1: Получить ожидаемое название первого продукта через прямую ссылку
		String expectedFirstProductName = getExpectedFirstItemName(CATEGORY_URL_NEHUD_LIT);

		// Шаг 2: Выполнить переход на главную страницу
		setUrl(BASE_URL);

		// Клик по "Каталог"
		getWebElementByLinkText(CATALOGUE_TEXT).click();

		// Клик по "Игры"
		getWebElementByLinkText(GAMES_TEXT).click();

		// Проверяем, что перешли на сортированную страницу
		assertEquals(CATEGORY_URL_GAMES, driver.getCurrentUrl(), INCORRECT_URL);

		// Шаг 3: Выбор сортировки "Сперва товары со скидками"
		WebElement sortMenu = getWebElementByLinkText(SORT_DEFAULT_TEXT);
		sortMenu.click();
		sortMenu.click();
		getWebElementByLinkText(SORT_DISCOUNT_TEXT).click();

		// Клик по "Каталог"
		getWebElementByLinkText(CATALOGUE_TEXT).click();

		// Клик по "Нехудожественная лиетратура"
		getWebElementByLinkText(NEHUD_LIT_TEXT).click();

		// Шаг 4: Получить фактическое название первого продукта после сортировки через меню
		String actualFirstProductName = getFirstElemName();

		// Шаг 5: Сравнить ожидаемое и фактическое названия первого продукта
		assertEquals(expectedFirstProductName, actualFirstProductName, INCORRECT_RESULT_PRODUCT_NAME);
	}

	// 7) категория -> сортировка -> подкатегория -> подкатегоирия
	@Test
	public void testCatalogSortingCategoryToSubcategoryToSubcategoryByDate() {
		// Шаг 1: Получить ожидаемое название первого продукта через прямую ссылку
		String expectedFirstProductName = getExpectedFirstItemName(CATEGORY_URL_ZARUB_CLASS_SORT_BY_PRICE_ASC);

		// Шаг 2: Выполнить переход на главную страницу
		setUrl(BASE_URL);

		// Клик по "Каталог"
		getWebElementByLinkText(CATALOGUE_TEXT).click();

		// Клик по "Художественная литература"
		getWebElementByLinkText(HUD_LIT_TEXT).click();

		assertEquals(CATEGORY_URL_HUD_LIT, driver.getCurrentUrl(), INCORRECT_URL);

		// Шаг 3: Выбор сортировки "Сперва новинки"
		WebElement sortMenu = getWebElementByLinkText(SORT_DEFAULT_TEXT);
		sortMenu.click();
		sortMenu.click();
		getWebElementByLinkText(SORT_PRICE_ACS_TEXT).click();

		// Клик по "Зарубежная литература"
		getWebElementByLinkText(ZARUB_LIT_TEXT).click();

		// Клик по "Зарубежная классика"
		getWebElementByLinkText(ZARUB_CLASS_TEXT).click();

		// Шаг 4: Получить фактическое название первого продукта после сортировки через меню
		String actualFirstProductName = getFirstElemName();

		// Шаг 5: Сравнить ожидаемое и фактическое названия первого продукта
		assertEquals(expectedFirstProductName, actualFirstProductName, INCORRECT_RESULT_PRODUCT_NAME);
	}

	private String getExpectedFirstItemName(String URL) {
		setUrl(URL);
		return getFirstElemName();
	}

	private void setUrl(String URL) {
		driver.get(URL);
	}

	private String getFirstElemName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div.product-name span[itemprop='name']")
		)).getText();
	}

	private WebElement getWebElementByLinkText(String text) {
		return wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(text)));
	}
}
